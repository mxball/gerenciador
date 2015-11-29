<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
    <a href="javascript:window.history.go(-1)">Voltar</a>
	<h2>Equipe: ${equipe.nome}</h2>
    <a href="../novoProjeto?id=${equipe.id}">Clique aqui</a> para cadastrar um novo projeto
    <ul>
	   <c:forEach items="${projetos}" var="projeto">
	       <p><a href="../projeto/${projeto.id}">${projeto.nome}</a>Clique</p>
	   </c:forEach>
    </ul>
</body>

</body>
</html>