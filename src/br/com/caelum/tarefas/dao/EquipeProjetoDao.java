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
import br.com.caelum.tarefas.modelo.Projeto;

@Repository
public class EquipeProjetoDao {

	private Connection connection;

	@Autowired
	public EquipeProjetoDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Integer> getIdProjetos(Equipe equipe) {
		try {
			PreparedStatement stmt = connection.prepareStatement("select projeto_id "
					+ "from equipe_desenvolve_projeto where equipe_id = ?");
			stmt.setLong(1, equipe.getId());
			ResultSet set = stmt.executeQuery();
			List<Integer> ids = new ArrayList<Integer>();
			while(set.next()){
				ids.add(set.getInt(1));
			}
			stmt.close();
			return ids;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void adiciona(Projeto projeto, Equipe equipe) {
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into equipe_desenvolve_projeto values (?,?)");
			stmt.setLong(1, equipe.getId());
			stmt.setLong(2, projeto.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
