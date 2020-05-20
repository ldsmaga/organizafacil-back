package com.organizafacil.organizafacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizafacil.organizafacil.entity.Usuario;
import com.organizafacil.organizafacil.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario saveUsuario(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public List<Usuario> getUsuarios(){
		return repository.findAll();
	}
	

}
