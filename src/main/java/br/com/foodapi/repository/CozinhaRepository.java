package br.com.foodapi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.foodapi.domain.entity.Cozinha;

@Repository
public class CozinhaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Cozinha> findAll(){
		TypedQuery<Cozinha> cozinhas = entityManager.createQuery("from Cozinha", Cozinha.class);
		return cozinhas.getResultList();
	}
	
	public Cozinha saveOrUpdate(Cozinha cozinha) {
		return entityManager.merge(cozinha);
	}
	
	public Cozinha findById(Long id){
		return entityManager.find(Cozinha.class, id);
	}
	
	public void remove(Cozinha cozinha) {
		entityManager.remove(cozinha);
	}
}
