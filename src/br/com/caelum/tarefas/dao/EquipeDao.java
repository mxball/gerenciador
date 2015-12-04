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

@Repository
public class EquipeDao {

	private Connection connection;

	@Autowired
	public EquipeDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Equipe equipe) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("insert into Equipe "
							+ "(nome) values (?)");
			stmt.setString(1, equipe.getNome());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adicionaUsuario(Long equipeID, Long usuarioId) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("insert into pertence_a "
							+ "values (?,?)");
			stmt.setLong(1, equipeID);
			stmt.setLong(2, usuarioId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Equipe buscaPorId(Long id) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("select * from Equipe where id = ?");
			stmt.setLong(1, id);
			ResultSet set = stmt.executeQuery();

			while (set.next()) {
				Equipe equipe = new Equipe();
				equipe.setId(set.getLong(1));
				equipe.setNome(set.getString(2));
				return equipe;
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Equipe> buscaPorNome(Equipe equipe) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("select * from Equipe where nome like ?");
			stmt.setString(1, equipe.getNome());
			ResultSet set = stmt.executeQuery();
			List<Equipe> lista = new ArrayList<Equipe>();

			while (set.next()) {
				Equipe equipeLoaded = new Equipe();
				equipeLoaded.setId(set.getLong(1));
				equipeLoaded.setNome(set.getString(2));
				lista.add(equipeLoaded);
			}
			stmt.close();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
