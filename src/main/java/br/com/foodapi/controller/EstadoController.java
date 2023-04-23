package br.com.foodapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.foodapi.domain.entity.Estado;
import br.com.foodapi.service.EstadoService;

@RestController
@RequestMapping("/v1/estados")
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
