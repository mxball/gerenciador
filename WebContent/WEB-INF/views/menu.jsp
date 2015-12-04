<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
		<a href="logout">Sair do sistema</a>
		<h2>Página inicial da Lista Tarefas</h2>
		<p>Bem vindo, ${usuarioLogado.login}</p>
		
		Tarefas mais cadastradas:<br>
		<c:forEach items="${redis}" var="tarefa">
            <p>${tarefa}</p>		
		</c:forEach>
		
		<a href="listaTarefas">Clique aqui</a> para acessar a lista de tarefas<br><br>
		<a href="equipe">Clique aqui</a> para acessar a pagina da sua equipe
	</body>
</html>