package br.com.caelum.tarefas.modelo;

public enum Tipo {
	JAVA{
		
	}, 
	FRONTEND{
		
	}
	, 
	BD{
		
	},
	INFRa{
		
	};
	public String getName() {
		return name();
	}
}
