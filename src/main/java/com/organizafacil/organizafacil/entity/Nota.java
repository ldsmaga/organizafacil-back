package com.organizafacil.organizafacil.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nota")
public class Nota {	
	@Id
	@GeneratedValue
	private int idNota;
	private String conteudo;
	private String statusNota;
	public int getIdNota() {
		return idNota;
	}
	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getStatusNota() {
		return statusNota;
	}
	public void setStatusNota(String statusNota) {
		this.statusNota = statusNota;
	}

	
	

}
