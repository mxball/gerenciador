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
	private String status;
	
	private Integer usuario_id;
	private Integer projeto_id;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public Integer getProjeto_id() {
		return projeto_id;
	}
	public void setProjeto_id(int projeto_id) {
		this.projeto_id = projeto_id;
	}
	
}
