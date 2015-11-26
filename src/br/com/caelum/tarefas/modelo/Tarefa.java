package br.com.caelum.tarefas.modelo;

import java.util.Calendar;

public class Tarefa {
	
	private Long id;
	private String descricao;
	private Calendar dtInicio;
	private Calendar dtFim;
	private Calendar dtPrazo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Calendar dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Calendar getDtFim() {
		return dtFim;
	}
	public void setDtFim(Calendar dtFim) {
		this.dtFim = dtFim;
	}
	public Calendar getDtPrazo() {
		return dtPrazo;
	}
	public void setDtPrazo(Calendar dtPrazo) {
		this.dtPrazo = dtPrazo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
