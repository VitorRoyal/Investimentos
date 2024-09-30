package br.com.investimentos.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CriaCarteiraDto(@NotBlank String descricao,
                              @NotBlank String endereco,
                              @NotNull @Positive Integer numero) {
}
