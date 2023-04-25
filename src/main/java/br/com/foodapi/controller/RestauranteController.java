package br.com.foodapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.foodapi.domain.entity.Restaurante;
import br.com.foodapi.exceptions.NaoEncontratoException;
import br.com.foodapi.service.RestauranteService;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService service;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Restaurante> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Restaurante findAll(@PathVariable Long id){
		try {
			return service.findById(id);
		} catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Restaurante create(@RequestBody Restaurante restaurante) {
		try {
			return service.create(restaurante);
		} catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Restaurante update(@RequestBody Restaurante restaurante, @PathVariable Long id) {
		try {
			return service.update(id, restaurante);
		} catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		try {
			service.delete(id);
		} catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
}
