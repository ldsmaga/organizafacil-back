package com.organizafacil.organizafacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizafacil.organizafacil.entity.Tarefa;
import com.organizafacil.organizafacil.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository repository;
	
	public Tarefa saveTarefa(Tarefa tarefa) {
		return repository.save(tarefa);
	}
	
	public List<Tarefa> getTarefas(){
		return repository.findAll();
	}
}
