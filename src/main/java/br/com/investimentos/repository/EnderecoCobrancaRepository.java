package br.com.investimentos.repository;

import br.com.investimentos.entity.EnderecoCobranca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoCobrancaRepository extends JpaRepository<EnderecoCobranca, UUID> {
}
