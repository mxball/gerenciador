package br.com.caelum.tarefas.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private Long id;
	private String nome;
	private String senha;
	private String descricao;
	private List<Equipe> equipes = new ArrayList<Equipe>();

	public String getLogin() {
		return nome;
	}

	public void setLogin(String login) {
		this.nome = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
