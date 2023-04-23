package br.com.foodapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.foodapi.domain.entity.Estado;
import br.com.foodapi.service.EstadoService;

@RestController
@RequestMapping(value = "/v1/estados", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/{id}")
	public Estado findById(@PathVariable Long id) {
		return estadoService.findById(id);
	}
	
	@GetMapping
	public List<Estado> findAll(){
		return estadoService.findAll();
	}
}
