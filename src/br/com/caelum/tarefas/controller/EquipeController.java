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
import br.com.caelum.tarefas.dao.GerenteDao;
import br.com.caelum.tarefas.dao.PertenceADao;
import br.com.caelum.tarefas.dao.ProjetoDao;
import br.com.caelum.tarefas.dao.UsuarioDao;
import br.com.caelum.tarefas.modelo.Equipe;
import br.com.caelum.tarefas.modelo.Projeto;
import br.com.caelum.tarefas.modelo.Usuario;
import br.com.caelum.tarefas.modelo.UsuarioEquipe;

@Controller
public class EquipeController {
	
	private EquipeDao equipeDao;
	private PertenceADao pDao;
	private EquipeProjetoDao epDao;
	private ProjetoDao proDao;
	private GerenteDao gDao;
	private UsuarioDao uDao;

	@Autowired
	public EquipeController(EquipeDao equipeDao, PertenceADao pDao, EquipeProjetoDao epDao, 
							ProjetoDao proDao, GerenteDao gDao, UsuarioDao uDao) {
		this.pDao = pDao;
		this.equipeDao = equipeDao;
		this.epDao = epDao;
		this.proDao = proDao;
		this.gDao = gDao;
		this.uDao = uDao;
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
	public String acessaEquipe(@PathVariable Long id, Model model){
		Equipe equipe = equipeDao.buscaPorId(id);
		model.addAttribute("usuarios", uDao.listaUsuarioDaEquipe(equipe));
		model.addAttribute("equipe", equipe);
		List<Integer> idProjetos = epDao.getIdProjetos(equipe);
		List<Projeto> projetos = new ArrayList<Projeto>();
		for (Integer pId : idProjetos) {
			projetos.add(proDao.buscaPorId(pId));
		}
		model.addAttribute("projetos", projetos);
		return "equipe/mostra";
	}
	
	@RequestMapping("adicionaEquipe")
	public String adiciona(Model model, HttpSession session, Equipe equipe) {
		equipeDao.adiciona(equipe);
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		List<Equipe> equipes = equipeDao.buscaPorNome(equipe);
		gDao.adicionaGerente(equipes.get(0), usuario);
		equipeDao.adicionaUsuario(equipes.get(0).getId(), usuario.getId());
		return "equipe/equipeAdicionada";
	}
	
	@RequestMapping("equipe/adicionaMembro")
	public String membroForm(Equipe equipe, Model model, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		long gerente = gDao.getGerente(equipe);
		if(usuario.getId() == gerente){
			model.addAttribute("usuarios", uDao.lista(equipe));
			model.addAttribute("equipe", equipe);
			return "equipe/formMembro";
		}
		return "equipe/naoAutorizado";
	}
	
	@RequestMapping("equipe/cadastraUsuario")
	public String adicionaUsuario(Model model, HttpSession session, UsuarioEquipe usuarioEquipe) {
		equipeDao.adicionaUsuario(usuarioEquipe.getEquipe_id(), usuarioEquipe.getUsuario_id());
		return "equipe/usuarioAdicionado";
	}
	
}
