<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<a href="javascript:window.history.go(-1)">Voltar</a>
	<form action="cadastraUsuario" method="post">
	    <input type="hidden" name="equipe_id" value="${equipe.id}">
		<select name=usuario_id>
			<c:forEach items="${usuarios}" var="usuario">
				<option value="${usuario.id}">${usuario.login}</option>
			</c:forEach>
		</select>
		<input type="submit" value="adicionar">
	</form>
</body>
</html>