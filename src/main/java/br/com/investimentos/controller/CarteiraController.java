package br.com.investimentos.controller;

import br.com.investimentos.controller.dto.AssociaCarteiraAcaoDto;
import br.com.investimentos.controller.dto.CarteiraAcaoResponseDto;
import br.com.investimentos.service.CarteiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping("/{carteiraId}/acoes")
    public ResponseEntity<Void> associaAcao(@PathVariable String carteiraId,
                                            @RequestBody AssociaCarteiraAcaoDto dto) {
        carteiraService.associaAcao(carteiraId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{carteiraId}/acoes")
    public ResponseEntity<List<CarteiraAcaoResponseDto>> listaAcao(@PathVariable String carteiraId) {

        var acoes = carteiraService.listaAcao(carteiraId);
        return ResponseEntity.ok(acoes);
    }

}
