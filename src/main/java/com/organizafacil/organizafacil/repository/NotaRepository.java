package com.organizafacil.organizafacil.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.organizafacil.organizafacil.entity.Nota;
import com.organizafacil.organizafacil.entity.Usuario;

public interface NotaRepository extends JpaRepository<Nota, Integer>{
	@Transactional(readOnly=true)
	Page<Nota> findByUsuario(Usuario usuario, Pageable pageRequest);
	
}
