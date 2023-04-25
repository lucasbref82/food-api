package br.com.foodapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foodapi.constantes.Mensagens;
import br.com.foodapi.domain.entity.Cidade;
import br.com.foodapi.domain.entity.Estado;
import br.com.foodapi.exceptions.NaoEncontratoException;
import br.com.foodapi.repository.CidadeRepository;
import br.com.foodapi.repository.EstadoRepository;

@Service

public class CidadeService {
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> findAll() {
		return cidadeRepository.findAll();
	}

	public Cidade findById(Long id) throws NaoEncontratoException {
		Cidade found = cidadeRepository.findById(id).orElse(null);
		validaCidade(found, id);
		return found;
	}

	@Transactional
	public Cidade create(Cidade cidade) throws NaoEncontratoException {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId);
		if (estado == null) {
			throw new NaoEncontratoException(
					String.format(Mensagens.NOT_FOUND_CUSTOM, "Estado", estadoId, "encontrado"));
		}
		cidade.getEstado().setNome(estado.getNome());
		return cidadeRepository.save(cidade);
	}

	@Transactional
	public Cidade update(Long id, Cidade cidade) throws NaoEncontratoException {
		Cidade found = cidadeRepository.findById(id).get();
		this.validaCidade(cidade, id);
		Long estadoId = cidade.getEstado().getId();
		Estado estadoFound = estadoRepository.findById(estadoId);
		if (estadoFound == null) {
			throw new NaoEncontratoException(
					String.format(Mensagens.NOT_FOUND_CUSTOM, "Estado", estadoId, "encontrado"));
		}
		BeanUtils.copyProperties(cidade, found, "id");
		cidade.getEstado().setNome(estadoFound.getNome());
		return cidadeRepository.save(found);

	}

	@Transactional
	public void delete(Long id) throws NaoEncontratoException {
		Cidade found = findById(id);
		this.validaCidade(found, id);
		cidadeRepository.delete(found);
	}

	public void validaCidade(Cidade cidade, Long id) throws NaoEncontratoException {
		if (cidade == null) {
			throw new NaoEncontratoException(String.format(Mensagens.NOT_FOUND_CUSTOM, "Cidade", id, "encontrada"));
		}
	}
}
