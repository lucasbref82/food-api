package br.com.foodapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.foodapi.domain.entity.Estado;
import br.com.foodapi.exceptions.NaoEncontratoException;
import br.com.foodapi.service.EstadoService;

@RestController
@RequestMapping(value = "/v1/estados", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Estado findById(@PathVariable Long id) {
		try {
			return estadoService.findById(id);
		} catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Estado> findAll(){
		return estadoService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Estado create(@RequestBody Estado estado) {
		return estadoService.create(estado);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Estado update(@RequestBody Estado estado, @PathVariable Long id) {
		try {
			return estadoService.update(estado, id);
		} catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		try {
			estadoService.delete(id);
		} catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
