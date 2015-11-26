<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<link href="resources/css/jquery.css" rel="stylesheet">
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery-ui.js"></script>
</head>
<body>
	<script type="text/javascript">
		function excluiAgora(id) {
			$.post("excluiTarefa",{'id' : id}, function(){
				$("#tarefa_" + id).hide();
			});
		}
	</script>
	<a href="novaTarefa">Criar nova tarefa</a>
	<br/><br/>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Descricao</th>
				<th>Inicio</th>
				<th>Fim</th>
				<th>Prazo</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tarefas}" var="tarefa">
				<tr id="tarefa_${tarefa.id}">
					<td>${tarefa.id}</td>
					<td>${tarefa.descricao}</td>
					<td>
							<fmt:formatDate value="${tarefa.dtInicio.time}" pattern="dd/MM/yyyy"/>
					</td>
					<td>
							<fmt:formatDate value="${tarefa.dtFim.time}" pattern="dd/MM/yyyy"/>
					</td>
					<td>
							<fmt:formatDate value="${tarefa.dtPrazo.time}" pattern="dd/MM/yyyy"/>
					</td>
					<td>
						<a href="removeTarefa?id=${tarefa.id}">Remover</a>
					</td>
					<td>
						<a href="mostraTarefa?id=${tarefa.id}">Mostra</a>
					</td>
					<td>
						<a href="#" onclick="excluiAgora(${tarefa.id})">
							Exclui agora!
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>	
</body>
</html>