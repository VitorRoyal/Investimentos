package br.com.investimentos.repository;

import br.com.investimentos.entity.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarteiraRepository extends JpaRepository<Carteira, UUID> {
}
