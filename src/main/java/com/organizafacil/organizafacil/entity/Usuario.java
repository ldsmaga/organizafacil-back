package com.organizafacil.organizafacil.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.organizafacil.organizafacil.enums.Perfis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	private String nome;
    @Column(unique=true)
	private String email;
	private String statusUsuario = "ativo";
	private String login;
	
	private String senha;

	@OneToMany(mappedBy="idUsuario")
	private List<Tarefa> tarefa = new ArrayList<>();
	
	@OneToMany(mappedBy="idUsuario")
	private List<Nota> nota = new ArrayList<>();
	
	public Usuario() {
		addPerfil(Perfis.USER);
	}
	
	public Usuario(Integer idUsuario, String nome, String email, String login, String senha) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatusUsuario() {
		return statusUsuario;
	}
	
	public void setStatusUsuario(String statusUsuario) {
		this.statusUsuario= statusUsuario;
	}
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@JsonIgnore //anotação para ignorar caso liste um json
	public String getSenha() {
		return senha;
	}
	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

//	public List<Nota> getNota() {
//		return nota;
//	}
//	
	public List<Tarefa> retornaTarefas() {
		return tarefa;
	}
	

	public Set<Perfis> getPerfis() {
		return perfis.stream().map(x -> Perfis.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfis perfil) {
		perfis.add(perfil.getCod());
	}
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();


	
	

}
