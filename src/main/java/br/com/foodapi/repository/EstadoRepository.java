package br.com.foodapi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.foodapi.domain.entity.Estado;

@Repository
public class EstadoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Estado> findAll(){
		return entityManager.createQuery("from Estado", Estado.class).getResultList();
	}
	
	public Estado findById(Long id) {
		return entityManager.find(Estado.class, id);
	}

}
