package br.com.investimentos.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriaUsuarioDto(@NotBlank(message = "Nome não pode estar em branco") String nome,
                             @Email String email,
                             @NotBlank(message = "Senha não pode estar em branco") String senha) {
}
