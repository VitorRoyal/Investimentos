package br.com.investimentos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carteiras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
