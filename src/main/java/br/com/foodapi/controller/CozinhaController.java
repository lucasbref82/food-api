package br.com.foodapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.foodapi.domain.entity.Cozinha;
import br.com.foodapi.service.CozinhaService;

@RestController
@RequestMapping("/v1/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public List<Cozinha> findAll(){
		return cozinhaService.findAll();
	}
	
	@GetMapping("{id}")
	public Cozinha findById(@PathVariable Long id) {
		return cozinhaService.findById(id);
	}
}
