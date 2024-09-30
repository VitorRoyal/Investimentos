package br.com.investimentos.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CriaAcaoDto(@NotBlank String acaoId,
                          @NotBlank String descricao) {
}
