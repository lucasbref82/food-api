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
		return jdbcTemplate.query("SELECT * FROM COZINHA WHERE ID = ?"
				, new Object[] {id}, (rs, rowNum) -> {
					return Cozinha.builder()
							.id(rs.getLong("id"))
							.nome(rs.getString("nome"))
							.build();
				}).get(0);
	}
	
	public void remove(Cozinha cozinha) {
		entityManager.remove(cozinha);
	}

	public Cozinha create(Cozinha cozinha) {
		return entityManager.merge(cozinha);
	}
}
