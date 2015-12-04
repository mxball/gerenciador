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
public class UsuarioDao {
	private Connection connection;

	@Autowired
	public UsuarioDao(DataSource dataSource) {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean existeUsuario(Usuario usuario) {

		if (usuario == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from usuario where nome = ? and senha = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();

			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Usuario busca(String nome) {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from usuario where nome like ?");
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return populaUsuario(rs);
			}

			rs.close();
			stmt.close();

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Usuario populaUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();

		// popula o objeto tarefa
		usuario.setId(rs.getLong("id"));
		usuario.setLogin(rs.getString("nome"));

		return usuario;
	}

	public List<Usuario> lista(Equipe equipe) {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from usuario "
							+ "where id not in (select usuario_id from pertence_a where "
							+ "equipe_id = ?)");
			stmt.setLong(1, equipe.getId());
			ResultSet rs = stmt.executeQuery();
			List<Usuario> lista = new ArrayList<Usuario>();
			while (rs.next()) {
				lista.add(populaUsuario(rs));
			}
			rs.close();
			stmt.close();
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> listaUsuarioDaEquipe(Equipe equipe) {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from usuario "
							+ "where id in (select usuario_id from pertence_a where "
							+ "equipe_id = ?)");
			stmt.setLong(1, equipe.getId());
			ResultSet rs = stmt.executeQuery();
			List<Usuario> lista = new ArrayList<Usuario>();
			while (rs.next()) {
				lista.add(populaUsuario(rs));
			}
			rs.close();
			stmt.close();
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
