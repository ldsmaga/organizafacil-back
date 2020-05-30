package com.organizafacil.organizafacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.organizafacil.organizafacil.entity.Usuario;
import com.organizafacil.organizafacil.repository.UsuarioRepository;
import com.organizafacil.organizafacil.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario user = repo.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(user.getIdUsuario(), user.getEmail(), user.getSenha(), user.getPerfis());
	}
	
	

}
