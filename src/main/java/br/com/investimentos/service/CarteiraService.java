package br.com.investimentos.service;

import br.com.investimentos.client.BrapiClient;
import br.com.investimentos.controller.dto.AssociaCarteiraAcaoDto;
import br.com.investimentos.controller.dto.CarteiraAcaoResponseDto;
import br.com.investimentos.entity.AcaoInfo;
import br.com.investimentos.entity.CarteiraAcao;
import br.com.investimentos.entity.CarteiraAcaoId;
import br.com.investimentos.exceptions.AcaoInfoException;
import br.com.investimentos.exceptions.AcaoNotFoundException;
import br.com.investimentos.exceptions.CarteiraNotFoundException;
import br.com.investimentos.repository.AcaoRepository;
import br.com.investimentos.repository.CarteiraAcaoRepository;
import br.com.investimentos.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CarteiraService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;

    private CarteiraRepository carteiraRepository;

    private AcaoRepository acaoRepository;

    private CarteiraAcaoRepository carteiraAcaoRepository;

    private BrapiClient brapiClient;

    public CarteiraService(CarteiraRepository carteiraRepository, AcaoRepository acaoRepository, CarteiraAcaoRepository carteiraAcaoRepository, BrapiClient brapiClient) {
        this.carteiraRepository = carteiraRepository;
        this.acaoRepository = acaoRepository;
        this.carteiraAcaoRepository = carteiraAcaoRepository;
        this.brapiClient = brapiClient;
    }


    public void associaAcao(String carteiraId, AssociaCarteiraAcaoDto dto) {
        var carteira = carteiraRepository.findById(UUID.fromString(carteiraId))
                .orElseThrow(() -> new CarteiraNotFoundException("Carteira não encontrada"));
        var acao = acaoRepository.findById(dto.acaoId())
                .orElseThrow(() -> new AcaoNotFoundException("Ação não encontrada"));

        //DTO -> Entity
        var id = new CarteiraAcaoId(carteira.getCarteiraId(), acao.getAcaoId());
        var entity = new CarteiraAcao(
                id,
                carteira,
                acao,
                dto.quantidade()
        );

        carteiraAcaoRepository.save(entity);

    }

    public List<CarteiraAcaoResponseDto> listaAcao(String carteiraId) {
        var carteira = carteiraRepository.findById(UUID.fromString(carteiraId))
                .orElseThrow(() -> new CarteiraNotFoundException("Carteira não encontrada"));

        return carteira.getCarteirasAcoes()
                .stream()
                .map(ca -> {
                    var acaoInfo = getAcaoInfo(ca.getAcao().getAcaoId());
                    return new CarteiraAcaoResponseDto(
                            acaoInfo.getLongName(),
                            ca.getAcao().getAcaoId(),
                            ca.getQuantidade(),
                            ca.getQuantidade() * acaoInfo.getRegularMarketPrice(),
                            acaoInfo.getCurrency()
                    );
                })
                .toList();
    }

    private AcaoInfo getAcaoInfo(String acaoId) {
        System.out.println("Buscando ação: " + acaoId + " com o token");
        var response = brapiClient.getAcao(TOKEN, acaoId);
        System.out.println("Resposta da API: " + response);

        var resultados = response.results();

        if (resultados == null || resultados.isEmpty()) {
            throw new AcaoInfoException("Nenhum resultado encontrado para a ação: " + acaoId);
        }

        var resultado = resultados.get(0);
        return new AcaoInfo(resultado.regularMarketPrice(), resultado.longName(), resultado.currency());
    }
}
