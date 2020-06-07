package com.organizafacil.organizafacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organizafacil.organizafacil.entity.Usuario;
import com.organizafacil.organizafacil.service.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	

	@GetMapping("/")
	public String login(){
		return "Login ok";
	}
	
	@PostMapping("/cadastro")
	public Usuario addUsuario(@RequestBody Usuario usuario) {
		return service.saveUsuario(usuario);
	}
	
	@GetMapping("/usuarios")
	public List<Usuario> findUsuarios(){
		return service.getUsuarios();
	}
}
