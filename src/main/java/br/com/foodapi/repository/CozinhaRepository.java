package br.com.foodapi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.foodapi.domain.entity.Cozinha;

@Repository
public class CozinhaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
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
	
	public void remove(Long id) {
		jdbcTemplate.update("DELETE FROM COZINHA WHERE ID = ?", new Object[] {id});
	}

	public List<Cozinha> findByNome(String nome) {
		return entityManager.createQuery("from Cozinha where nome = :nome", Cozinha.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}

}
