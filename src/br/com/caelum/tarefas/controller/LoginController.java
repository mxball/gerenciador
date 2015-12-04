package br.com.caelum.tarefas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.UsuarioDao;
import br.com.caelum.tarefas.modelo.Usuario;

@Controller
public class LoginController {

	private UsuarioDao usuarioDao;

	@Autowired
	public LoginController(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	@RequestMapping("loginForm")
	public String loginForm() {
		return "formulario-login";
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		if (usuarioDao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuarioDao.busca(usuario.getLogin()));
			return "menu";
		}
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
	
	@RequestMapping("cadastro")
	public String formCadastro(){
		return "cadastro";
	}
	
	@RequestMapping("cadastraUsuario")
	public String cadastroUsuario(Usuario usuario, HttpSession session){
		usuarioDao.cadastra(usuario);
		session.setAttribute("usuarioLogado", usuarioDao.busca(usuario.getLogin()));
		return "menu";
	}
	
}
