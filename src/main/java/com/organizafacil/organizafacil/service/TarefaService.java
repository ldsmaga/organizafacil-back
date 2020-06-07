package com.organizafacil.organizafacil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.organizafacil.organizafacil.entity.Tarefa;
import com.organizafacil.organizafacil.entity.Usuario;
import com.organizafacil.organizafacil.excecoes.AuthorizationException;
import com.organizafacil.organizafacil.repository.TarefaRepository;
import com.organizafacil.organizafacil.security.UserSS;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	public Tarefa saveTarefa(Tarefa tarefa) {

		UserSS user = UserService.authenticated();

		// setar os dados do user para passar para nota:
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(user.getId());
		usuario.setEmail(user.getUsername());
		usuario.setSenha(user.getPassword());
		tarefa.setUsuario(usuario);

		return repository.save(tarefa);
	}
	

	// traz as tarefas apenas do user logado:
	public Page<Tarefa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario usuario = usuarioService.find(user.getId());
		System.out.println(usuario);
		return repository.findByIdUsuario(usuario, pageRequest);
	}
	
	public Tarefa getTarefaById(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Tarefa> obj = repository.findById(id);
		
		System.out.println(repository.findById(id));
		return obj.orElseThrow(() -> new com.organizafacil.organizafacil.excecoes.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tarefa.class.getName()));
	}
	
	public Tarefa inativarTarefa(Tarefa tarefa) {
		Tarefa tarefaAtual = repository.findById(tarefa.getIdTarefa()).orElse(null);
		tarefaAtual.setStatus_tarefa("inativo");
		return repository.save(tarefaAtual);
	}

	public Tarefa updateTarefa(Tarefa tarefa) {
		Tarefa tarefaAtual = repository.findById(tarefa.getIdTarefa()).orElse(null);
		tarefaAtual.setConteudo_tarefa(tarefa.getConteudo_tarefa());
		tarefaAtual.setStatus_tarefa(tarefa.getStatus_tarefa());
		return repository.save(tarefaAtual);
	}
	
	
//	public String deleteTarefa(int id) {
//		repository.deleteById(id);
//		return "Tarefa removida!";
//	}

//	public List<Tarefa> getTarefas(){
//		return repository.findAll();
//	}
}
