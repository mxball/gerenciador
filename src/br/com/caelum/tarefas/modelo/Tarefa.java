package br.com.caelum.tarefas.modelo;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Tarefa {
	
	private Long id;
	private String descricao;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtInicio;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtFim;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtPrazo;
	private Status status;
	private Tipo tipo;
	
	private Long usuario_id;
	private Long projeto_id;
	
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
	public Long getProjeto_id() {
		return projeto_id;
	}
	public void setProjeto_id(Long projeto_id) {
		this.projeto_id = projeto_id;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
