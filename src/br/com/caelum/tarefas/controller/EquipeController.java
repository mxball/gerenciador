package br.com.caelum.tarefas.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.JdbcEquipeDao;
import br.com.caelum.tarefas.dao.JdbcPertenceADao;
import br.com.caelum.tarefas.modelo.Usuario;

@Controller
public class EquipeController {
	
	private JdbcEquipeDao equipeDao;
	private JdbcPertenceADao pDao;

	@Autowired
	public EquipeController(JdbcEquipeDao equipeDaom, JdbcPertenceADao pDao) {
		this.pDao = pDao;
		this.equipeDao = equipeDao;
	}
	
	@RequestMapping("equipe")
	public String lista(Model model, HttpSession session) {
		Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
		List<String> list = pDao.getEquipeDoUsuario(logado);
		model.addAttribute("lista", list);
		return "listaEquipe";
	}
}
