package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Projeto;

@Repository
public class ProjetoDao {
	
	private Connection connection;

	@Autowired
	public ProjetoDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Projeto buscaPorId(int id){
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from projeto where id = ?");
			stmt.setInt(1, id);
			ResultSet set = stmt.executeQuery();
			set.next();
			Projeto projeto = new Projeto();
			projeto.setId(set.getLong(1));
			projeto.setNome(set.getString(2));
			
			stmt.close();
			return projeto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void adiciona(Projeto projeto) {
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into projeto (nome) values (?)");
			stmt.setString(1, projeto.getNome());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Projeto buscaPorNome(Projeto projeto) {
		try {
			PreparedStatement stmt = connection.prepareStatement("select id from projeto where nome like ?");
			stmt.setString(1, projeto.getNome());
			ResultSet set = stmt.executeQuery();
			set.next();
			projeto.setId(set.getLong(1));
			projeto.setNome(set.getString(1));
			stmt.close();
			return projeto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
}
