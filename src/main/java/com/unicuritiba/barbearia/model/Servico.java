package com.unicuritiba.barbearia.model;

import javax.persistence.Entity;

@Entity
public class Servico extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private String url;

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
