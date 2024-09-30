package br.com.investimentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carteiras_acoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraAcao {

    @EmbeddedId
    private CarteiraAcaoId id;

    @ManyToOne
    @MapsId("carteiraId")
    @JoinColumn(name = "carteira_id")
    private Carteira carteira;

    @ManyToOne
    @MapsId("acaoId")
    @JoinColumn(name = "acao_id")
    private Acao acao;

    private Integer quantidade;

}
