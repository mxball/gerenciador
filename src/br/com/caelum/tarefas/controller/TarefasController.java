package br.com.caelum.tarefas.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.JdbcTarefaDao;
import br.com.caelum.tarefas.modelo.Tarefa;
import br.com.caelum.tarefas.modelo.Usuario;

@Controller
public class TarefasController {

		private JdbcTarefaDao dao;

		@Autowired
		public TarefasController(JdbcTarefaDao dao) {
			this.dao = dao;
		}
	
		@RequestMapping("novaTarefa")
		public String form() {
			return "tarefa/formulario";
		}
		
		@RequestMapping("adicionaTarefa")
		public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
			System.out.println("Aqui");
			if (result.hasFieldErrors()) {
				return "tarefa/formulario";
			}
			dao.adiciona(tarefa);
			return "tarefa/adicionada";
		}
	
		@RequestMapping("listaTarefas")
		public String lista(Model model) {
			model.addAttribute("tarefas", dao.lista());
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
		
}
