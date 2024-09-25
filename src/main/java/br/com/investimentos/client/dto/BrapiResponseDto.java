package br.com.investimentos.client.dto;

import java.util.List;

public record BrapiResponseDto(List<AcaoDto> results) {
}
