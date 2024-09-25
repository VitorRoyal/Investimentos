package br.com.investimentos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "acoes")
public class Acao {

    @Id
    private String acaoId;

    private String descricao;

    public Acao() {
    }

    public Acao(String acaoId, String descricao) {
        this.acaoId = acaoId;
        this.descricao = descricao;
    }

    public String getAcaoId() {
        return acaoId;
    }

    public void setAcaoId(String acao_id) {
        this.acaoId = acao_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
