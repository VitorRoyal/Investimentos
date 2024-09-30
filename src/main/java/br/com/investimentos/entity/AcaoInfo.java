package br.com.investimentos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcaoInfo {
    private double regularMarketPrice;
    private String longName;
    private String currency;
}
