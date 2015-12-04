package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Equipe;
import br.com.caelum.tarefas.modelo.Usuario;

@Repository
public class GerenteDao {
	
	private Connection connection;

	
	@Autowired
	public GerenteDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adicionaGerente(Equipe equipe, Usuario usuario){
		try {
			String sql = "insert into gerente_gerencia_equipe "
					+ "values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, equipe.getId());
			stmt.setLong(2, usuario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public long getGerente(Equipe equipe){
		try {
			String sql = "select * from gerente_gerencia_equipe "
				+ "where equipe_id = ? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, equipe.getId());
			ResultSet set = stmt.executeQuery();
			set.next();
			return set.getLong("usuario_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

}
