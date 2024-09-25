package br.com.investimentos.repository;

import br.com.investimentos.entity.Acao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcaoRepository extends JpaRepository<Acao, String> {
}
