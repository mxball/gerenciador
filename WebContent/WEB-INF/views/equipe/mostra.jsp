<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
    <a href="javascript:window.history.go(-1)">Voltar</a>
	<h2>Equipe: ${equipe.nome}</h2>
	Usuario:<ul>
	   <c:forEach items="${usuarios}" var="usuario">
	       <p>${usuario.login}
	   </c:forEach>
	</ul>
	<a href="adicionaMembro?id=${equipe.id}">Clique aqui</a> para adicionar um mebmro a equipe<br>
    <a href="../novoProjeto?id=${equipe.id}">Clique aqui</a> para cadastrar um novo projeto><br><br>
    Projetos:<ul>
	   <c:forEach items="${projetos}" var="projeto">
	       <p><a href="../projeto/${projeto.id}">${projeto.nome}</a>Clique</p>
	   </c:forEach>
    </ul>
</body>

</body>
</html>