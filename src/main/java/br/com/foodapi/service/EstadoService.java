package br.com.foodapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foodapi.domain.entity.Estado;
import br.com.foodapi.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> findAll(){
		return estadoRepository.findAll();
	}
	
	public Estado findById(Long id) {
		return estadoRepository.findById(id);
	}
}
