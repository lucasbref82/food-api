package br.com.foodapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foodapi.constantes.Mensagens;
import br.com.foodapi.domain.entity.Cozinha;
import br.com.foodapi.exceptions.NaoEncontratoException;
import br.com.foodapi.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	private static final String COZINHA = "Cozinha";
	private static final String ENCONTRADA = "encontrada";
	
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
			throw new NaoEncontratoException(String.format(Mensagens.NOT_FOUND_CUSTOM, COZINHA, id, ENCONTRADA));
		}
		return cozinha;
	}
	
	@Transactional
	public void delete(Long id) throws NaoEncontratoException {
		this.findById(id);
		repository.remove(id);
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Cozinha create(Cozinha cozinha) {
		return repository.saveOrUpdate(cozinha);
	}

	@Transactional
	public Cozinha update(Cozinha cozinha, Long id) throws NaoEncontratoException {
		Cozinha cozinhaAtual = repository.findById(id);
		if(cozinhaAtual == null) {
			throw new NaoEncontratoException(String.format(Mensagens.NOT_FOUND_CUSTOM, COZINHA, id, ENCONTRADA));
		}
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return repository.saveOrUpdate(cozinhaAtual);
		
	}
	
	public List<Cozinha> findByNome(String nome){
		return repository.findByNome(nome);
	}
}
