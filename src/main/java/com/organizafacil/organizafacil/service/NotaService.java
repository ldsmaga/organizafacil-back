package com.organizafacil.organizafacil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.organizafacil.organizafacil.entity.Nota;
import com.organizafacil.organizafacil.entity.Usuario;
import com.organizafacil.organizafacil.excecoes.AuthorizationException;
import com.organizafacil.organizafacil.repository.NotaRepository;
import com.organizafacil.organizafacil.security.UserSS;

@Service
public class NotaService {
	@Autowired
	private NotaRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	public Nota saveNota(Nota nota) {
		UserSS user = UserService.authenticated();

		// setar os dados do user para passar para nota:
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(user.getId());
		usuario.setEmail(user.getUsername());
		usuario.setSenha(user.getPassword());

		// passo para a nota:
		nota.setUsuario(usuario);

		return repository.save(nota);
	}
	
	
	// traz as notas apenas do user logado:
		public Page<Nota> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario usuario = usuarioService.find(user.getId());
		return repository.findByIdUsuario(usuario, pageRequest);
	}
	
	

	public Nota getNotaById(Integer id) {
		UserSS user = UserService.authenticated();

		if (user == null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Nota> obj = repository.findById(id);
		return obj.orElseThrow(() -> new com.organizafacil.organizafacil.excecoes.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Nota.class.getName()));
//		return repository.findById(id).orElse(null);
	}


	public Nota inativarNota(Nota nota) {
		UserSS user = UserService.authenticated();
		
		
		if(repository.findById(nota.getIdAnotacao()).get().getUsuario().getIdUsuario() != user.getId()) {
			throw new AuthorizationException("Acesso negado");			
		}		
		Nota notaAtual = repository.findById(nota.getIdAnotacao()).orElse(null);
		notaAtual.setStatus_anotacao("inativo");
		return repository.save(notaAtual);
	}
	
	public Nota arquivarNota(Nota nota) {	
		UserSS user = UserService.authenticated();
		
		
		if(repository.findById(nota.getIdAnotacao()).get().getUsuario().getIdUsuario() != user.getId()) {
			throw new AuthorizationException("Acesso negado");			
		}	
		Nota notaAtual = repository.findById(nota.getIdAnotacao()).orElse(null);
		notaAtual.setStatus_anotacao("arquivado");
		return repository.save(notaAtual);
	}

	public Nota desarquivarNota(Nota nota) {	
		UserSS user = UserService.authenticated();
		
		
		if(repository.findById(nota.getIdAnotacao()).get().getUsuario().getIdUsuario() != user.getId()) {
			throw new AuthorizationException("Acesso negado");			
		}	
		Nota notaAtual = repository.findById(nota.getIdAnotacao()).orElse(null);
		notaAtual.setStatus_anotacao("ativo");
		return repository.save(notaAtual);
	}

	public Nota updateNota(Nota nota) {
		UserSS user = UserService.authenticated();
		
		
		if(repository.findById(nota.getIdAnotacao()).get().getUsuario().getIdUsuario() != user.getId()) {
			throw new AuthorizationException("Acesso negado");			
		}
		
		Nota notaAtual = repository.findById(nota.getIdAnotacao()).orElse(null);
		notaAtual.setConteudo_anotacao(nota.getConteudo_anotacao());
		notaAtual.setStatus_anotacao(nota.getStatus_anotacao());
		return repository.save(notaAtual);
	}
	
	
//	public String deleteNota(int id) {
//		repository.deleteById(id);
//		return "Nota removida!";
//	}


//	public List<Nota> getNotas(){

//	UserSS user = UserService.authenticated();
//	if (user==null) {
//		throw new AuthorizationException("Acesso negado");
//	}
//	return repository.findAll();
//}

}
