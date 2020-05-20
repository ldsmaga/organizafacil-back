package com.organizafacil.organizafacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organizafacil.organizafacil.entity.Tarefa;
import com.organizafacil.organizafacil.service.TarefaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TarefaController {
	
	@Autowired
	private TarefaService service;
	
	@PostMapping("/adicionarTarefa")
	public Tarefa addNota(@RequestBody Tarefa tarefa) {
		return service.saveTarefa(tarefa);
	}
	
	@GetMapping("/tarefas")
	public List<Tarefa> findTarefas(){
		return service.getTarefas();
	}

}
