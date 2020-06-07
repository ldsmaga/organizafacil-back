package com.organizafacil.organizafacil.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.organizafacil.organizafacil.entity.Tarefa;
import com.organizafacil.organizafacil.entity.Usuario;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
	@Transactional(readOnly=true)
	Page<Tarefa> findByIdUsuario(Usuario idUsuario, Pageable pageRequest);

}
