package br.com.foodapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foodapi.constantes.Mensagens;
import br.com.foodapi.domain.entity.Estado;
import br.com.foodapi.exceptions.NaoEncontratoException;
import br.com.foodapi.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> findAll(){
		return estadoRepository.findAll();
	}
	
	public Estado findById(Long id) throws NaoEncontratoException {
		Estado found = estadoRepository.findById(id);
		estadoValido(found, id);
		return estadoRepository.findById(id);
	}
	
	@Transactional
	public Estado create(Estado estado) {
		return estadoRepository.createOrUpdate(estado);
	}
	
	@Transactional
	public Estado update(Estado estado, Long id) throws NaoEncontratoException {
		Estado found = estadoRepository.findById(id);
		estadoValido(found, id);
		BeanUtils.copyProperties(estado, found, "id");
		return estadoRepository.createOrUpdate(found);
	}
	
	@Transactional
	public void delete(Long id) throws NaoEncontratoException {
		Estado found = estadoRepository.findById(id);
		estadoValido(found, id);
		estadoRepository.delete(found);
	}
	
	public void estadoValido(Estado estado, Long id) throws NaoEncontratoException {
		if(estado == null) {
			throw new NaoEncontratoException(String.format(Mensagens.NOT_FOUND_CUSTOM, "Estado", id, "encotrado"));
		}
	}
}
