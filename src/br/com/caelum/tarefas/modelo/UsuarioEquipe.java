package br.com.caelum.tarefas.modelo;

public class UsuarioEquipe {

	private Long equipe_id;
	private Long usuario_id;
	private String usuario_nome;
	
	public Long getEquipe_id() {
		return equipe_id;
	}
	public void setEquipe_id(Long equipe_id) {
		this.equipe_id = equipe_id;
	}
	public Long getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getUsuario_nome() {
		return usuario_nome;
	}
	public void setUsuario_nome(String usuario_nome) {
		this.usuario_nome = usuario_nome;
	}
}
