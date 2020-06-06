package com.organizafacil.organizafacil.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.organizafacil.organizafacil.security.JWTUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "anotacao")	
public class Nota {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAnotacao;
	private String conteudo_anotacao;
	private String status_anotacao;
	

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario idUsuario;
	
	public int getIdAnotacao() {
		return idAnotacao;
	}
	public void setIdAnotacao(int idAnotacao) {
		this.idAnotacao = idAnotacao;
	}
	public String getConteudo_anotacao() {
		return conteudo_anotacao;
	}
	public void setConteudo_anotacao(String conteudo_anotacao) {
		this.conteudo_anotacao = conteudo_anotacao;
	}
	public String getStatus_anotacao() {
		return status_anotacao;
	}
	public void setStatus_anotacao(String status_anotacao) {
		this.status_anotacao = status_anotacao;
	}
	public Usuario getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Override
	public String toString() {
		return "Nota [idAnotacao=" + idAnotacao + ", conteudo_anotacao=" + conteudo_anotacao + ", status_anotacao="
				+ status_anotacao + ", idUsuario=" + idUsuario + "]";
	}
	
	
	
}
