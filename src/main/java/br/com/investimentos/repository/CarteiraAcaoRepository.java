package br.com.investimentos.repository;

import br.com.investimentos.entity.CarteiraAcao;
import br.com.investimentos.entity.CarteiraAcaoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarteiraAcaoRepository extends JpaRepository<CarteiraAcao, CarteiraAcaoId> {
}
