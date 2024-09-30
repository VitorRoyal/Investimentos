package br.com.investimentos.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AssociaCarteiraAcaoDto(@NotBlank String acaoId,
                                     @NotNull @Positive Integer quantidade) {
}
