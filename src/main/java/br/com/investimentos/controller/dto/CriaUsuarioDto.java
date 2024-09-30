package br.com.investimentos.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriaUsuarioDto(@NotBlank String nome,
                             @Email String email,
                             @NotBlank String senha) {
}
