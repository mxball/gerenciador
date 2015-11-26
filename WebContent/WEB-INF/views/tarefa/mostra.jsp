<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum"%>

<html>
<head>
	<link href="resources/css/jquery.css" rel="stylesheet">
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery-ui.js"></script>
</head>
<body>
	<h3>Alterar tarefa - %{tarefa.id}</h3>
	<form action="alteraTarefa" method="post">
		<input type="hidden" name="id" value="${tarefa.id}">
		Descrição: <br/>
		<textarea rows="5" cols="100" name="descricao">
			${tarefa.descricao}
		</textarea><br/>
        Inicio: <caelum:campoData name="dtInicio" id="dtInicio" value="${tarefa.dtInicio.time}"/>
        Fim: <caelum:campoData name="dtFim" id="dtFim" value="${tarefa.dtFim.time}"/>
        Prazo: <caelum:campoData name="dtPrazo" id="dtPrazo" value="${tarefa.dtPrazo.time}"/>
        <br/>
		<input type="submit" value="Alterar">		
	</form>
</body>
</html>