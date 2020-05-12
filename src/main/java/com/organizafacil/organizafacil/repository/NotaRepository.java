package com.organizafacil.organizafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizafacil.organizafacil.entity.Nota;

public interface NotaRepository extends JpaRepository<Nota, Integer>{

	Nota findByName(String name);

}
