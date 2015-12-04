package br.com.caelum.tarefas.modelo;

public enum Tipo {
	JAVA,
	FRONTEND,
	BD,
	INFRA;
	
	public String getName() {
		return name();
	}
}
