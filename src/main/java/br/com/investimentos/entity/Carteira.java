package br.com.investimentos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carteiras")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID carteiraId;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "carteira")
    @PrimaryKeyJoinColumn
    private EnderecoCobranca enderecoCobranca;

    @OneToMany(mappedBy = "carteira")
    @JsonIgnore
    private List<CarteiraAcao> carteirasAcoes;

    public Carteira() {
    }

    public Carteira(UUID carteiraId, String descricao, Usuario usuario, EnderecoCobranca enderecoCobranca, List<CarteiraAcao> carteirasAcoes) {
        this.carteiraId = carteiraId;
        this.descricao = descricao;
        this.usuario = usuario;
        this.enderecoCobranca = enderecoCobranca;
        this.carteirasAcoes = carteirasAcoes;
    }

    public UUID getCarteiraId() {
        return carteiraId;
    }

    public void setCarteiraId(UUID contaId) {
        this.carteiraId = contaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EnderecoCobranca getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(EnderecoCobranca enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public List<CarteiraAcao> getCarteirasAcoes() {
        return carteirasAcoes;
    }

    public void setCarteirasAcoes(List<CarteiraAcao> carteirasAcoes) {
        this.carteirasAcoes = carteirasAcoes;
    }
}
