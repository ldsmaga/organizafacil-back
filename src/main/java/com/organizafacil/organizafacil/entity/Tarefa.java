package com.organizafacil.organizafacil.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarefa")
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTarefa;
	private String conteudo_tarefa;
	private String status_tarefa;
	

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario idUsuario;
	
	
	public int getIdTarefa() {
		return idTarefa;
	}
	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}
	public String getConteudo_tarefa() {
		return conteudo_tarefa;
	}
	public void setConteudo_tarefa(String conteudo_tarefa) {
		this.conteudo_tarefa = conteudo_tarefa;
	}
	public String getStatus_tarefa() {
		return status_tarefa;
	}
	public void setStatus_tarefa(String status_tarefa) {
		this.status_tarefa = status_tarefa;
	}
	public Usuario getUsuario() {
		return idUsuario;
	}
	public void setUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}
	@Override
	public String toString() {
		return "Tarefa [idTarefa=" + idTarefa + ", conteudo_tarefa=" + conteudo_tarefa + ", status_tarefa="
				+ status_tarefa + ", idUsuario=" + idUsuario + "]";
	}
	
	
	
}
