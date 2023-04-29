package br.com.foodapi.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foodapi.constantes.Mensagens;
import br.com.foodapi.domain.entity.Cozinha;
import br.com.foodapi.domain.entity.Restaurante;
import br.com.foodapi.exceptions.NaoEncontratoException;
import br.com.foodapi.repository.CozinhaRepository;
import br.com.foodapi.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Restaurante> findAll(){
		return restauranteRepository.findAll();
	}
	
	public Restaurante findById(Long id) throws NaoEncontratoException {
		Restaurante found = restauranteRepository.findById(id).get();
		if(found == null) {
			throw new NaoEncontratoException(String.format(Mensagens.NOT_FOUND_CUSTOM, "Resutaurante", id, "encontrado"));
		}
		return found;
	}
	
	@Transactional
	public Restaurante create(Restaurante restaurante) throws NaoEncontratoException {
		Long cozinhaId = restaurante.getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId);
		if(cozinha == null) {
			throw new NaoEncontratoException(String.format(Mensagens.NOT_FOUND_CUSTOM, "Cozinha", cozinhaId, "encontrada"));
		}
		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public Restaurante update(Long id, Restaurante restaurante) throws NaoEncontratoException {
		Long cozinhaId = restaurante.getId();
		Restaurante found = this.findById(cozinhaId);
		if(found.getCozinha() == null) {
			throw new NaoEncontratoException(String.format(Mensagens.NOT_FOUND_CUSTOM, "Cozinha", cozinhaId, "encontrada"));
		}
		BeanUtils.copyProperties(restaurante, found, "id");
		return restauranteRepository.save(found);
		
	}
	
	@Transactional
	public void delete(Long id) throws NaoEncontratoException {
		Restaurante found = findById(id);
		restauranteRepository.delete(found);
	}

	public List<Restaurante> buscarTodosPorNomeETaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.buscarTodosPorNomeETaxaFrete(nome, taxaInicial, taxaFinal);
	}
	
}
