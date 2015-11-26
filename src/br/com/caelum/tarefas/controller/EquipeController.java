package br.com.caelum.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.JdbcEquipeDao;

public class EquipeController {
	private JdbcEquipeDao equipeDao;

	@Autowired
	public EquipeController(JdbcEquipeDao equipeDao) {
		this.equipeDao = equipeDao;
	}
	
	@RequestMapping("equipe")
	public void lista() {
		
	}
}
