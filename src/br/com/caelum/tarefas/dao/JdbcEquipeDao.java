package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.tarefas.modelo.Equipe;
import br.com.caelum.tarefas.modelo.Usuario;

public class JdbcEquipeDao {

	private Connection connection;

	@Autowired
	public JdbcEquipeDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Equipe> lista(Usuario usuario){
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from equipe where id = ?");
			stmt.setLong(1, usuario.getId());
			ResultSet set = stmt.executeQuery();
			List<Equipe> lista = new ArrayList<Equipe>();
			while (set.next()) {
				lista.add((Equipe) set);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
