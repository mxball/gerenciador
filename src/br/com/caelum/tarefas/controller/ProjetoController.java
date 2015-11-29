package br.com.caelum.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.EquipeProjetoDao;
import br.com.caelum.tarefas.dao.ProjetoDao;
import br.com.caelum.tarefas.dao.TarefaDao;
import br.com.caelum.tarefas.modelo.Equipe;
import br.com.caelum.tarefas.modelo.Projeto;

@Controller
public class ProjetoController {

	private ProjetoDao pDao;
	private TarefaDao tDao;
	private EquipeProjetoDao epDao;

	@Autowired
	public ProjetoController(ProjetoDao pDao, EquipeProjetoDao epDao, TarefaDao tDao) {
		this.pDao = pDao;
		this.epDao = epDao;
		this.tDao = tDao;
	}
	
	@RequestMapping("novoProjeto")
	public String form(Equipe equipe, Model model){
		model.addAttribute("equipe", equipe);
		return "projeto/formulario";
	}
	
	@RequestMapping("adicionaProjeto")
	public String adiciona(Projeto projeto, Equipe equipe){
		System.out.println(projeto.getNome());
		System.out.println(equipe.getId());
		pDao.adiciona(projeto);
		Projeto projetoLoaded = pDao.buscaPorNome(projeto);
		System.out.println("EquipeId " + equipe.getId() + "ProjetoID: " + projetoLoaded.getId());
		epDao.adiciona(projetoLoaded, equipe);
		return "projeto/adicionado";
	}
	
	@RequestMapping("projeto/{id}")
	public String mostra(@PathVariable int id, Model model){
		Projeto projeto = pDao.buscaPorId(id);
		model.addAttribute("tarefas", tDao.lista(projeto));
		model.addAttribute("projeto", projeto);
		return "projeto/mostra";
	}
	
}
