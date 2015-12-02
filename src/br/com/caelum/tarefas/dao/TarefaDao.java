package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Projeto;
import br.com.caelum.tarefas.modelo.Status;
import br.com.caelum.tarefas.modelo.Tarefa;
import br.com.caelum.tarefas.modelo.Tipo;
import br.com.caelum.tarefas.modelo.Usuario;

@Repository
public class TarefaDao {
	private final Connection connection;

	@Autowired
	public TarefaDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Tarefa tarefa) {
		String sql = "insert into tarefa "
				+ "(descricao, dtInicio, dtFim, dtPrazo, status, tipo, usuario_id, projeto_id) "
				+ "values (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setDate(2, new Date(tarefa.getDtInicio().getTimeInMillis()));
			stmt.setDate(3, new Date(tarefa.getDtFim().getTimeInMillis()));
			stmt.setDate(4, new Date(tarefa.getDtPrazo().getTimeInMillis()));
			stmt.setString(5, tarefa.getStatus().getName());
			stmt.setString(6, tarefa.getTipo().getName());
			if(tarefa.getUsuario_id() != null){
				stmt.setLong(7, tarefa.getUsuario_id());
			}
			else {
				stmt.setNull(7, Types.INTEGER);
			}
			if(tarefa.getProjeto_id() != null){
				stmt.setLong(8, tarefa.getProjeto_id());
			}
			else {
				stmt.setNull(8, Types.INTEGER);
			}
			
		
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void getTarefa(Tarefa tarefa) {
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from tarefa where id = ? ");
			stmt.setLong(1, tarefa.getId());
			ResultSet set = stmt.executeQuery();
			while(set.next()) {
				Date inicio = set.getDate("dtInicio");
				Date fim = set.getDate("dtFim");
				Date prazo = set.getDate("dtPrazo");
				System.out.println("Inicio: " + inicio);
				System.out.println("Fim: " + fim);
				System.out.println("Prazo: " + prazo);
			}
			stmt.setLong(1, tarefa.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Tarefa tarefa) {

		if (tarefa.getId() == null) {
			throw new IllegalStateException("Id da tarefa não deve ser nula.");
		}

		String sql = "delete from tarefa where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tarefa> lista(Usuario usuario) {
		try {
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefa where usuario_id = ? ");
			stmt.setLong(1, usuario.getId());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// adiciona a tarefa na lista
				tarefas.add(populaTarefa(rs));
			}

			rs.close();
			stmt.close();

			return tarefas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Tarefa> lista(Projeto projeto) {
		try {
			List<Tarefa> tarefas = new ArrayList<Tarefa>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefa where projeto_id = ?");
			stmt.setLong(1, projeto.getId());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// adiciona a tarefa na lista
				tarefas.add(populaTarefa(rs));
			}

			rs.close();
			stmt.close();

			return tarefas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Tarefa buscaPorId(Long id) {

		if (id == null) {
			throw new IllegalStateException("Id da tarefa não deve ser nula.");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefa where id = ?");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return populaTarefa(rs);
			}

			rs.close();
			stmt.close();

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Tarefa populaTarefa(ResultSet rs) throws SQLException {
		Tarefa tarefa = new Tarefa();

		// popula o objeto tarefa
		tarefa.setId(rs.getLong("id"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setStatus(Status.valueOf(rs.getString("status")));
		tarefa.setTipo(Tipo.valueOf(rs.getString("tipo")));
		tarefa.setProjeto_id(rs.getLong("projeto_id"));
		tarefa.setUsuario_id(rs.getLong("usuario_id"));

		// popula a data de finalizacao da tarefa, fazendo a conversao
		Date data = rs.getDate("dtInicio");
		if (data != null) {
			Calendar dataInicio = Calendar.getInstance();
			dataInicio.setTime(data);
			tarefa.setDtInicio(dataInicio);
		}
		Date dataFim = rs.getDate("dtFim");
		if (dataFim != null) {
			Calendar dataFinal = Calendar.getInstance();
			dataFinal.setTime(dataFim);
			tarefa.setDtFim(dataFinal);
		}
		Date dataP = rs.getDate("dtInicio");
		if (dataP != null) {
			Calendar dataPrazo = Calendar.getInstance();
			dataPrazo.setTime(dataP);
			tarefa.setDtInicio(dataPrazo);
		}
		
		return tarefa;
	}

	public void updateStatus(Tarefa tarefa) {
		try {
			String sql = "update Tarefa set status = ? where id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			System.out.println(tarefa.getStatus());
			stmt.setString(1, tarefa.getStatus().getName());
			stmt.setLong(2, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
