<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="tarefa" required="true"
	type="br.com.caelum.tarefas.modelo.Tarefa"%>
<%@ attribute name="status" required="true"%>

<td>
    <c:if test="${tarefa.status eq status}">
		<p>${tarefa.id}</p>
		<p>${tarefa.descricao}</p>
		<p>
			<fmt:formatDate value="${tarefa.dtInicio.time}" pattern="dd/MM/yyyy" />
		</p>
		<p>
			<fmt:formatDate value="${tarefa.dtFim.time}" pattern="dd/MM/yyyy" />
		</p>
		<p>
			<fmt:formatDate value="${tarefa.dtPrazo.time}" pattern="dd/MM/yyyy" />
		</p>
		<p>
			<a href="removeTarefa?id=${tarefa.id}">Remover</a>
		</p>
		<p>
			<a href="mostraTarefa?id=${tarefa.id}">Mostra</a>
		</p>
		<p>
			<a href="#" onclick="excluiAgora(${tarefa.id})"> Exclui agora! </a>
		</p>
	</c:if>
</td>