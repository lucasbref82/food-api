package br.com.foodapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.foodapi.domain.entity.Estado;
import br.com.foodapi.service.EstadoService;

@RestController
@RequestMapping(value = "/v1/estados", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Estado findById(@PathVariable Long id) {
		return estadoService.findById(id);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Estado> findAll(){
		return estadoService.findAll();
	}
}
