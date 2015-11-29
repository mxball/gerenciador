package br.com.caelum.tarefas.modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

	private int id;
	private String nome;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
