package com.organizafacil.organizafacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizafacil.organizafacil.entity.Nota;
import com.organizafacil.organizafacil.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	private NotaRepository repository;
	
	public Nota saveNota(Nota nota) {
		return repository.save(nota);
	}
	
	public List<Nota> getNotas(){
		return repository.findAll();
	}
	
	public Nota getNotaById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	
	public String deleteNota(int id) {
		repository.deleteById(id);
		return "Nota removida!";
	}
	
	public Nota updateNota(Nota nota) {
		Nota notaAtual = repository.findById(nota.getIdAnotacao()).orElse(null);
		notaAtual.setConteudo_anotacao(nota.getConteudo_anotacao());
		notaAtual.setStatus_anotacao(nota.getStatus_anotacao());
		return repository.save(notaAtual);
	}
}
