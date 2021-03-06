
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
public class PertenceADao {

	private Connection connection;

	@Autowired
	public PertenceADao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Equipe> getEquipeDoUsuario(Usuario usuario){
		try {
			String sql = "select e.id,e.nome from pertence_a p right join equipe e"
					+ " on p.equipe_id = e.id "
					+ " where p.usuario_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			ResultSet set = stmt.executeQuery();
			List<Equipe> list = new ArrayList<Equipe>();
			while (set.next()) {
				Equipe equipe = new Equipe();
				equipe.setId(set.getLong(1));
				equipe.setNome(set.getString(2));
				list.add(equipe);
			}
			stmt.close();
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
