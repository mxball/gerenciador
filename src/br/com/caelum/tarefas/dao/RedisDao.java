package br.com.caelum.tarefas.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Jedis;
import br.com.caelum.tarefas.modelo.Tipo;
import br.com.caelum.tarefas.modelo.Usuario;

public class RedisDao {
	
	@Autowired
    private RedisTemplate<String, String> template;
	private Jedis jedis;

	public RedisDao() {
		jedis = new Jedis("localhost");
	}

	public void setUsuarioPerfil(Usuario usuario){
		String u = "usuario_" + usuario.getId();
		jedis.set(u, "3");
	}
	
	public void setTipoTarefaUsuario(Usuario usuario, Tipo tipo){
		String u = "usuario_" + usuario.getId() + ":tarefa_" + tipo.getName();
		String valor = jedis.get(u);
		if(valor != null){
			Integer v = Integer.parseInt(valor);
			v++;
			jedis.set(u, v.toString());
		}
		else
		jedis.set(u, "1");
	}
	
	public void getTipoTarefaUsuario(Usuario usuario, Tipo tipo){
		String u = "usuario_" + usuario.getId() + ":tarefa_" + tipo.getName();
		String valor = jedis.get(u);
		System.out.println(valor);
	}
	
	
	public void getUsuario(Usuario usuario){
		String u = "usuario_" + usuario.getId();
		String string = jedis.get(u);
		System.out.println(string);
	}
	
	public static void main(String[] args) {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// check whether server is running or not
		System.out.println("Server is running: " + jedis.ping());
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		RedisDao dao = new RedisDao();
		dao.setUsuarioPerfil(usuario);
		dao.getUsuario(usuario);
		dao.setTipoTarefaUsuario(usuario, Tipo.BD);
		dao.getTipoTarefaUsuario(usuario, Tipo.BD);
	}
	
}
