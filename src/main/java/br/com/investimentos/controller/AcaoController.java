package br.com.investimentos.controller;

import br.com.investimentos.controller.dto.CriaAcaoDto;
import br.com.investimentos.service.AcaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acoes")
public class AcaoController {

    private final AcaoService acaoService;

    public AcaoController(AcaoService acaoService) {
        this.acaoService = acaoService;
    }


    @PostMapping("/criar")
    public ResponseEntity<Void> criarAcao(@RequestBody CriaAcaoDto dto) {
        acaoService.criarAcao(dto);
        return ResponseEntity.ok().build();
    }
}
