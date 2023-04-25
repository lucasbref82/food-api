package br.com.foodapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import br.com.foodapi.domain.entity.Cozinha;
import br.com.foodapi.exceptions.NaoEncontratoException;
import br.com.foodapi.service.CozinhaService;
import br.com.foodapi.wrapper.CozinhasXmlWrapper;

@RestController
@RequestMapping(value = "/v1/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CozinhaController {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Cozinha> findAll(){
		return cozinhaService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cozinha create(@RequestBody Cozinha cozinha) {
		return cozinhaService.create(cozinha);
	}
	
	// Customizando representação XML
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public CozinhasXmlWrapper findAllXml(){
		return new CozinhasXmlWrapper(cozinhaService.findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Cozinha findById(@PathVariable Long id) {
		try {
			return cozinhaService.findById(id);
		}catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Cozinha update(@RequestBody Cozinha cozinha, @PathVariable Long id) {
		try {
			return cozinhaService.update(cozinha, id);
		}catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		try {
			cozinhaService.delete(id);
		}catch (NaoEncontratoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}
}
