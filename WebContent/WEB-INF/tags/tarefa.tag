<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="tarefa" required="true"
	type="br.com.caelum.tarefas.modelo.Tarefa"%>
<%@ attribute name="status" required="true"%>

<td>
    <c:if test="${tarefa.status eq status}">
		<p>Descrição: ${tarefa.descricao}</p>
		<p>Inicio: 
			<fmt:formatDate value="${tarefa.dtInicio.time}" pattern="dd/MM/yyyy" />
		</p>
		<p>Fim:
			<fmt:formatDate value="${tarefa.dtFim.time}" pattern="dd/MM/yyyy" />
		</p>
		<p>Prazo:
			<fmt:formatDate value="${tarefa.dtPrazo.time}" pattern="dd/MM/yyyy" />
		</p>
		<p>Status:
		<form action="alteraStatus">
		    <input type="hidden" name="id" value="${tarefa.id}">
   			<select name=status>
		           <c:forEach items="${todosStatus}" var="status" >
		               <option value="${status.name}" ${tarefa.status.name eq status.name ? 'selected' : ''}>
		                   ${status.name}
		               </option>
		           </c:forEach>
			</select>
			<input type="submit" value="alterar">
		</form>
		<a href="altera?id="></a>
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