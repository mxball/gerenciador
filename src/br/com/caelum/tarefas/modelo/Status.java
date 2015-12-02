package br.com.caelum.tarefas.modelo;

public enum Status {
	ToDo, 
	Doing, 
	UnitTest, 
	Approving, 
	Done;

	public String getName() {
		return name();
	}
	
	
}
