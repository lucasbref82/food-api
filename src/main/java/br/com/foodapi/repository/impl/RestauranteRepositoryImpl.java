package br.com.foodapi.repository.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.foodapi.domain.entity.Cozinha;
import br.com.foodapi.domain.entity.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	//Consulta dinamica com criteria API
	public List<Restaurante> findAllByNameAndTaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		// Criando a fábrida de Criteria Builder
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// Para criar uma CriteriaQuery precisa de um builder
		CriteriaQuery<Restaurante> criteria = null;
		if (builder != null) {
			criteria = builder.createQuery(Restaurante.class);
		}

		if (criteria != null) {
			// Raiz onde a consulta irá ser feita
			Root<Cozinha> root = criteria.from(Cozinha.class);
			// Criação do predicato que irá entrar no where.
			List<Predicate> predicados = null;
			if (nome != null && !nome.isEmpty()) {

				predicados.add(builder.like(root.get("nome"), "%" + nome + "%"));
			}
			if (taxaInicial != null) {
				predicados.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
			}

			if (taxaFinal != null) {
				predicados.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
			}
			// Consegue retornar uma instância de um array preenchido.
			if (predicados != null) {
				criteria.where(predicados.toArray(new Predicate[0]));
			}

		}

		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}

	public List<Restaurante> buscarTodosPorNomeETaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("from Restaurante where 1=1 ");
		Map<String, Object> parametros = new HashMap<>();
		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}

		if (taxaInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaInicial);
		}

		if (taxaFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFinal);
		}
		TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
		parametros.forEach(query::setParameter);
		return query.getResultList();
	}
}
