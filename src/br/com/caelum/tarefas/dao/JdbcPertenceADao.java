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

import br.com.caelum.tarefas.modelo.Usuario;

@Repository
public class JdbcPertenceADao {

	private Connection connection;

	@Autowired
	public JdbcPertenceADao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<String> getEquipeDoUsuario(Usuario usuario){
		try {
			System.out.println("ID " + usuario.getId());
			String sql = "select e.nome from pertence_a p right join equipe e"
					+ " on p.equipe_id = e.id "
					+ " where p.usuario_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			ResultSet set = stmt.executeQuery();
			List<String> list = new ArrayList<String>();
			while (set.next()) {
				String x = set.getString(1);
				System.out.println(x);
				list.add(x);
			}
			System.out.println("vazio");
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
