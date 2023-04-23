package br.com.foodapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foodapi.constantes.Mensagens;
import br.com.foodapi.domain.entity.Cozinha;
import br.com.foodapi.exceptions.NaoEncontratoException;
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
	
	public Cozinha findById(Long id) throws NaoEncontratoException {
		Cozinha cozinha = repository.findById(id);
		if(cozinha == null) {
			throw new NaoEncontratoException(String.format(Mensagens.NOT_FOUND_CUSTOM, "Cozinha", id, "encontrada"));
		}
		return repository.findById(id);
	}
	
	@Transactional
	public void delete(Cozinha cozinha) {
		repository.remove(cozinha);
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Cozinha create(Cozinha cozinha) {
		return repository.create(cozinha);
	}
}
