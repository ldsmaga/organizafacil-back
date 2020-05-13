package com.organizafacil.organizafacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organizafacil.organizafacil.entity.Nota;
import com.organizafacil.organizafacil.service.NotaService;

@RestController
public class NotaController {
	
	@Autowired
	private NotaService service;
	
	@PostMapping("/adicionarNota")
	public Nota addNota(@RequestBody Nota nota) {
		return service.saveNota(nota);
	}
	
	@GetMapping("/notas")
	public List<Nota> findNotas(){
		return service.getNotas();
	}
	
	@GetMapping("/nota/{id}")
	public Nota findNotaById(@PathVariable int id) {
		return service.getNotaById(id);
	}
	
	@PutMapping("/editar")
	public Nota updateNota(@RequestBody Nota nota) {
		return service.updateNota(nota);
	}
	
	@DeleteMapping("/apagar/{id}")
	public String deleteNota(@PathVariable int id) {
		return service.deleteNota(id);
	}

}
