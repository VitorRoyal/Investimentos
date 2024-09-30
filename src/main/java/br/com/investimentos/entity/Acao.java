package br.com.investimentos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "acoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Acao {

    @Id
    private String acaoId;

    private String descricao;

}
