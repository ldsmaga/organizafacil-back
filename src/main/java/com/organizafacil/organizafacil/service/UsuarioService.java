package com.organizafacil.organizafacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	@Autowired
	private UserDetailsServiceImpl userDetails;
	
	public Usuario saveUsuario(Usuario usuario) {
		if(repository.findByEmail(usuario.getEmail())!= null){
			throw new AuthorizationException("Usuário já cadastrado");
		}
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


	public UserDetails dados(String email) {
		return userDetails.loadUserByUsername(email);
	}
	
	public Usuario updateUsuario(Usuario usuario) {
		

		UserSS user = UserService.authenticated();
		
		
		if(repository.findById(usuario.getIdUsuario()).get().getIdUsuario() != user.getId()) {
			throw new AuthorizationException("Acesso negado");			
		}
		
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Usuario usuarioAtual = repository.findById(usuario.getIdUsuario()).orElse(null);
		usuarioAtual.setNome(usuario.getNome());
		usuarioAtual.setEmail(usuario.getEmail());
		usuarioAtual.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return repository.save(usuarioAtual);
	}
	
	
	public Usuario inativarUsuario(Usuario usuario) {	
		UserSS user = UserService.authenticated();
		
		
		if(repository.findById(usuario.getIdUsuario()).get().getIdUsuario() != user.getId()) {
			throw new AuthorizationException("Acesso negado");			
		}	
		Usuario usuarioAtual = repository.findById(usuario.getIdUsuario()).orElse(null);
		usuarioAtual.setStatusUsuario("inativo");
		return repository.save(usuarioAtual);
	}

}
