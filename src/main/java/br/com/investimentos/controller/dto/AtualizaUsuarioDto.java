package br.com.investimentos.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AtualizaUsuarioDto(@NotBlank String nome,
                                 @NotBlank String senha) {
}
