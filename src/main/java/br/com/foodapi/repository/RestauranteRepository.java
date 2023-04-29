package br.com.foodapi.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foodapi.domain.entity.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> consultarPorNome(String nome);
	List<Restaurante> buscarTodosPorNomeETaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}
