package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Equipe;
import br.com.caelum.tarefas.modelo.Usuario;

@Repository
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
	
	public void adiciona(Equipe equipe){
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into Equipe "
					+ "(nome) values (?)");
			stmt.setString(1, equipe.getNome());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionaUsuario(Equipe equipe, Usuario usuario) {
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into pertence_a "
					+ "values (?,?)");
			stmt.setLong(1, equipe.getId());
			stmt.setLong(2, usuario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Equipe> lista(Usuario usuario){
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from equipe where id = ?");
			stmt.setLong(1, usuario.getId());
			ResultSet set = stmt.executeQuery();
			List<Equipe> lista = new ArrayList<Equipe>();
			
			stmt.close();
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
