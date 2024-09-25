package br.com.investimentos.service;

import br.com.investimentos.client.BrapiClient;
import br.com.investimentos.controller.dto.AssociaCarteiraAcaoDto;
import br.com.investimentos.controller.dto.CarteiraAcaoResponseDto;
import br.com.investimentos.entity.CarteiraAcao;
import br.com.investimentos.entity.CarteiraAcaoId;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var acao = acaoRepository.findById(dto.acaoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return carteira.getCarteirasAcoes()
                .stream()
                .map(ca ->
                        new CarteiraAcaoResponseDto(
                                ca.getAcao().getAcaoId(),
                                ca.getQuantidade(),
                                getTotal(ca.getQuantidade(),ca.getAcao().getAcaoId())
                        ))
                .toList();
    }

    private double getTotal(Integer quantidade, String acaoId) {
        try {
            System.out.println("Buscando ação: " + acaoId + " com token: " + TOKEN);
            var response = brapiClient.getAcao(TOKEN, acaoId);
            System.out.println("Resposta da API: " + response);

            var resultados = response.results();

            if (resultados == null || resultados.isEmpty()) {
                // Log the error for debugging purposes
                System.err.println("Nenhum resultado encontrado para a ação: " + acaoId);
                // Return a default value or handle the error as needed
                return 0.0;
            }

            var preco = resultados.get(0).regularMarketPrice();
            return quantidade * preco;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            System.err.println("Erro ao buscar a ação: " + acaoId + ". Detalhes: " + e.getMessage());
            // Return a default value or handle the error as needed
            return 0.0;
        }
    }
}
