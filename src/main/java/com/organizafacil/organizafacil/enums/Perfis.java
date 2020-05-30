package com.organizafacil.organizafacil.enums;

public enum Perfis {
	USER(1, "ROLE_USUARIO"),
	ADM(2, "ROLE_ADMIN");
	
	private int cod;
	private String descricao;
	
	private Perfis(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static Perfis toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Perfis x : Perfis.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
