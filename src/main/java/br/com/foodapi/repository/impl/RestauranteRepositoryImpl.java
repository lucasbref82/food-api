package br.com.foodapi.repository.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.foodapi.domain.entity.Restaurante;

@Repository
public class RestauranteRepositoryImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Restaurante> buscarTodosPorNomeETaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
		StringBuilder jpql = new StringBuilder();
		jpql.append("from Restaurante where 1=1 ");
		Map<String, Object> parametros = new HashMap<>();
		if(StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome  + "%");
		}
		
		if(taxaInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaInicial);
		}
		
		if(taxaFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFinal);
		}
		TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
		parametros.forEach(query::setParameter);
		return query.getResultList();
	}
}
