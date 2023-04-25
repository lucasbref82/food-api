package br.com.foodapi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.foodapi.domain.entity.Estado;

@Repository
public class EstadoRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Estado> findAll(){
		return entityManager.createQuery("from Estado", Estado.class).getResultList();
	}
	
	public Estado findById(Long id) {
		Object[] params = new Object[] {id};
		return jdbcTemplate.queryForObject("SELECT * FROM ESTADO WHERE ID = ?", params, (rs, rowNum) -> {
			return new Estado(rs.getLong("id"), rs.getString("nome"));
		});
	}
	
	public Estado createOrUpdate(Estado estado) {
		return entityManager.merge(estado);
	}
	
	public void delete(Estado estado) {
		jdbcTemplate.update("DELETE FROM ESTADO WHERE ID = ?", new Object[] {estado.getId()});
	}

}
