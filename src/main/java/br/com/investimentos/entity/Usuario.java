package br.com.investimentos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID usuarioId;

    private String nome;

    private String email;

    private String senha;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Carteira> carteiras;

    @CreationTimestamp
    private Instant createTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public Usuario(UUID usuarioId, String nome, String email, String senha, Instant createTimestamp, Instant updateTimestamp) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
    }
}
