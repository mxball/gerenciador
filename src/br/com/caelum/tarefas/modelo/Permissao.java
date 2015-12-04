package br.com.caelum.tarefas.modelo;

public enum Permissao {
	INSERT,
	DELETE;
	
	public String getName(){
		return name();
	}
}
