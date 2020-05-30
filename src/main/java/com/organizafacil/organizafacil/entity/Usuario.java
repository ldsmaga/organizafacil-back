package com.organizafacil.organizafacil.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private int idUsuario;
	private String nome;
	private String email;
	private String status;
	private String login;
	
	//@JsonIgnore //anotação para ignorar caso liste um json
	private String senha;
	
	@OneToMany(mappedBy="usuario")
	private List<Nota> nota = new ArrayList<>();
	
	public Usuario() {
		addPerfil(Perfis.USER);
	}
	
	public Usuario(Integer idUsuario, String nome, String email, String login, String senha) {
		super();
		this.status = "ativo";
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Nota> getNota() {
		return nota;
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
