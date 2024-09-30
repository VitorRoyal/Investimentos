package br.com.investimentos.controller.dto;

public record CarteiraAcaoResponseDto(String nomeCompleto,
                                      String acaoId,
                                      int quantidade,
                                      double total,
                                      String tipoMoeda) {
}
