package com.organizafacil.organizafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizafacil.organizafacil.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
