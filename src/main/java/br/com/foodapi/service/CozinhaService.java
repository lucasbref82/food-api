package br.com.foodapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foodapi.domain.entity.Cozinha;
import br.com.foodapi.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	@Autowired
	private CozinhaRepository repository;
 
	public List<Cozinha> findAll(){
		return repository.findAll();
	}
	
	@Transactional
	public void save(Cozinha cozinha) {
		repository.saveOrUpdate(cozinha);
	}
	
	public Cozinha findById(Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public void delete(Cozinha cozinha) {
		repository.remove(cozinha);
	}
}
