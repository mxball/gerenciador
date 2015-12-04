package br.com.caelum.tarefas.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.TarefaDao;
import br.com.caelum.tarefas.modelo.Projeto;
import br.com.caelum.tarefas.modelo.Status;
import br.com.caelum.tarefas.modelo.Tarefa;
import br.com.caelum.tarefas.modelo.Usuario;

@Controller
public class TarefasController {

		private TarefaDao dao;

		private RedisTemplate< String, Object > template;
	
		@Autowired
		public TarefasController(TarefaDao dao) {
			this.dao = dao;
		}
	
		@RequestMapping("novaTarefa")
		public String form() {
			return "tarefa/formulario";
		}
		
		@RequestMapping("novaTarefaProjeto")
		public String formProjeto(Projeto projeto, Model model) {
			model.addAttribute("projeto", projeto);
			return "projeto/form";
		}
		
		@RequestMapping("adicionaTarefaPessoal")
		public String adicionaTPessoal(Tarefa tarefa, HttpSession session) {
			tarefa.setStatus(Status.ToDo);
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			tarefa.setUsuario_id(usuario.getId());
			dao.adiciona(tarefa);
			return "tarefa/adicionada";
		}
		
		@RequestMapping("adicionaTarefaProjeto")
		public String adicionaTProjeto(Tarefa tarefa, HttpSession session) {
			tarefa.setStatus(Status.ToDo);
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			tarefa.setUsuario_id(null);
			dao.adiciona(tarefa);
			return "tarefa/adicionada";
		}
	
	
		@RequestMapping("listaTarefas")
		public String lista(Model model, HttpSession session) {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			model.addAttribute("tarefas", dao.lista(usuario));
			model.addAttribute("todosStatus", Status.values());
			return "tarefa/lista";
		}
		
		@RequestMapping("removeTarefa")
		public String remove(Tarefa tarefa) {
			dao.remove(tarefa);
			return "redirect:listaTarefas";
		}
		
		@RequestMapping("mostraTarefa")
		public String mostra(Long id, Model model) {
			model.addAttribute("tarefa", dao.buscaPorId(id));
			return "tarefa/mostra";
		}
		
		@RequestMapping("excluiTarefa")
		public void exclui(Tarefa tarefa, HttpServletResponse response) {
			dao.remove(tarefa);
			response.setStatus(200);
		}
		
		@RequestMapping("ok")
		public String ok(@ModelAttribute("tarefa") Tarefa tarefa, @ModelAttribute("usuario") Usuario usuario) {
			return "tarefa/adicionada";
		}
		
		@RequestMapping("alteraStatus")
		public String altera(Tarefa tarefa, HttpSession session){
			dao.updateStatus(tarefa);
			return "redirect:listaTarefas";
		}
		
}
