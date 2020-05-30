package com.organizafacil.organizafacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.organizafacil.organizafacil.entity.Usuario;
import com.organizafacil.organizafacil.enums.Perfis;
import com.organizafacil.organizafacil.excecoes.AuthorizationException;
import com.organizafacil.organizafacil.excecoes.ObjectNotFoundException;
import com.organizafacil.organizafacil.repository.UsuarioRepository;
import com.organizafacil.organizafacil.security.UserSS;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario saveUsuario(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return repository.save(usuario);
	}
	
	public List<Usuario> getUsuarios(){
		return repository.findAll();
	}
	
public Usuario find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfis.ADM) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	

}
