package br.com.caelum.tarefas.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.RedisDao;
import br.com.caelum.tarefas.dao.UsuarioDao;
import br.com.caelum.tarefas.modelo.Tipo;
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
	public String efetuaLogin(Usuario usuario, Model model, HttpSession session) {
		if (usuarioDao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuarioDao.busca(usuario.getLogin()));
			return "redirect:menu";
		}
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
	
	@RequestMapping("menu")
	public String menu(Model model, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		Usuario loaded = usuarioDao.busca(usuario.getLogin());
		RedisDao rDao = new RedisDao();
		ArrayList<String> list = new ArrayList<String>();
		list.add(rDao.getTipoTarefaUsuario(loaded, Tipo.JAVA));
		list.add(rDao.getTipoTarefaUsuario(loaded, Tipo.BD));
		list.add(rDao.getTipoTarefaUsuario(loaded, Tipo.FRONTEND));
		list.add(rDao.getTipoTarefaUsuario(loaded, Tipo.INFRA));
		model.addAttribute("redis", list);
		return "menu";
	}
	
	@RequestMapping("cadastro")
	public String formCadastro(){
		return "cadastro";
	}
	
	@RequestMapping("cadastraUsuario")
	public String cadastroUsuario(Usuario usuario, HttpSession session){
		usuarioDao.cadastra(usuario);
		session.setAttribute("usuarioLogado", usuarioDao.busca(usuario.getLogin()));
		return "redirect:menu";
	}
	
}
