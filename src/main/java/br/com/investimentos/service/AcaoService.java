package br.com.investimentos.service;

import br.com.investimentos.controller.dto.CriaAcaoDto;
import br.com.investimentos.entity.Acao;
import br.com.investimentos.repository.AcaoRepository;
import org.springframework.stereotype.Service;

@Service
public class AcaoService {

    private AcaoRepository acaoRepository;

    public AcaoService(AcaoRepository acaoRepository) {
        this.acaoRepository = acaoRepository;
    }

    public void criarAcao(CriaAcaoDto dto) {
        //DTO -> Entity
        var acao = new Acao(
                dto.acaoId(),
                dto.descricao()
        );

        acaoRepository.save(acao);
    }
}
