package br.com.caelum.tarefas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.EquipeDao;
import br.com.caelum.tarefas.dao.EquipeProjetoDao;
import br.com.caelum.tarefas.dao.PertenceADao;
import br.com.caelum.tarefas.dao.ProjetoDao;
import br.com.caelum.tarefas.modelo.Equipe;
import br.com.caelum.tarefas.modelo.Projeto;
import br.com.caelum.tarefas.modelo.Usuario;

@Controller
public class EquipeController {
	
	private EquipeDao equipeDao;
	private PertenceADao pDao;
	private EquipeProjetoDao epDao;
	private ProjetoDao proDao;

	@Autowired
	public EquipeController(EquipeDao equipeDao, PertenceADao pDao, EquipeProjetoDao epDao, ProjetoDao proDao) {
		this.pDao = pDao;
		this.equipeDao = equipeDao;
		this.epDao = epDao;
		this.proDao = proDao;
	}
	
	@RequestMapping("novaEquipe")
	public String form(){
		return "equipe/formulario";
	}
	
	@RequestMapping("equipe")
	public String lista(Model model, HttpSession session) {
		Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
		List<Equipe> list = pDao.getEquipeDoUsuario(logado);
		model.addAttribute("lista", list);
		return "listaEquipe";
	}
	
	@RequestMapping("equipe/{id}")
	public String acessaEquipe(@PathVariable int id, Model model){
		List<Equipe> equipes = equipeDao.buscaPorId(id);
		Equipe equipe = equipes.get(0);
		model.addAttribute("equipe", equipe);
		List<Integer> idProjetos = epDao.getIdProjetos(equipe);
		List<Projeto> projetos = new ArrayList<Projeto>();
		for (Integer pId : idProjetos) {
			projetos.add(proDao.buscaPorId(pId));
		}
		System.out.println("numero de projetos: " + projetos.size());
		model.addAttribute("projetos", projetos);
		return "equipe/mostra";
	}
	
	@RequestMapping("adicionaEquipe")
	public String adiciona(Model model, HttpSession session, Equipe equipe) {
		equipeDao.adiciona(equipe);
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		List<Equipe> equipes = equipeDao.buscaPorNome(equipe);
		equipeDao.adicionaUsuario(equipes.get(0), usuario);
		return "equipe/equipeAdicionada";
	}
	
	public String adicionaUsuario(Model model, HttpSession session, Equipe equipe, Usuario usuario) {
		equipeDao.adicionaUsuario(equipe, usuario);
		model.addAttribute("equipe", equipe);
		return "usuarioAdicionado";
	}
}
