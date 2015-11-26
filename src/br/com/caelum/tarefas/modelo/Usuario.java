package br.com.caelum.tarefas.modelo;

public class Usuario {
	private String nome;
	private String senha;
	private String descricao;

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
}
