package br.com.investimentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "endereco_cobranca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
