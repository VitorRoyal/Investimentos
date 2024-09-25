package br.com.investimentos.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "endereco_cobranca")
public class EnderecoCobranca {

    @Id
    @Column(name = "carteira_id")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "carteira_id")

    private Carteira carteira;

    private String endereco;

    private Integer numero;

    public EnderecoCobranca() {
    }

    public EnderecoCobranca(UUID id, Carteira carteira, String endereco, Integer numero) {
        this.id = id;
        this.carteira = carteira;
        this.endereco = endereco;
        this.numero = numero;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
