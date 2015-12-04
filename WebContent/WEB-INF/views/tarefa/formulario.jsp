<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum"%>

<html>
<body>
	<h3>Adicionar tarefas</h3>
	<form action="adicionaTarefaPessoal" method="post">
	   Descrição: <br/>
        <textarea rows="5" cols="100" name="descricao">${tarefa.descricao}</textarea><br/>
		Data inicio:<input type="date" name="dtInicio" id="dtInicio" value="${tarefa.dtInicio.time}"><br>
		Data fim:<input type="date" name="dtFim" id="dtFim" value="${tarefa.dtFim.time}"><br>
		Data prazo:<input type="date" name="dtPrazo" id="dtPrazo" value="${tarefa.dtPrazo.time}"><br>
        Tipo :
        <select name="tipo">
            <c:forEach items="${todosTipos}" var="tipo" >
                <option value="${tipo.name}">
                    ${tipo.name}
                </option>
            </c:forEach>
        </select> <br>
		<input type="submit" value="Adicionar">
	</form>
</body>
</html>