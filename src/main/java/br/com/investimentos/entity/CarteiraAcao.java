package br.com.investimentos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carteiras_acoes")
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

    public CarteiraAcao() {
    }

    public CarteiraAcao(CarteiraAcaoId id, Carteira carteira, Acao acao, Integer quantidade) {
        this.id = id;
        this.carteira = carteira;
        this.acao = acao;
        this.quantidade = quantidade;
    }

    public CarteiraAcaoId getId() {
        return id;
    }

    public void setId(CarteiraAcaoId id) {
        this.id = id;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
