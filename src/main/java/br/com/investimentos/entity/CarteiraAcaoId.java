package br.com.investimentos.entity;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class CarteiraAcaoId {

    private UUID carteiraId;

    private String acaoId;

    public CarteiraAcaoId() {
    }

    public CarteiraAcaoId(UUID carteiraId, String acaoId) {
        this.carteiraId = carteiraId;
        this.acaoId = acaoId;
    }

    public UUID getCarteiraId() {
        return carteiraId;
    }

    public void setCarteiraId(UUID carteira_id) {
        this.carteiraId = carteira_id;
    }

    public String getAcaoId() {
        return acaoId;
    }

    public void setAcaoId(String acao_id) {
        this.acaoId = acao_id;
    }
}
