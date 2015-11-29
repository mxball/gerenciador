<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
    <h2>Página de equipes</h2>
    <a href="novaEquipe">Clique aqui</a> para adicionar uma nova equipe<br><br>
    <h3>Lista de equipes do usuário</h3>
    <c:forEach var="equipe" items="${lista}">
	   <p><a href="equipe/${equipe.id}">${equipe.nome}</a></p>
    </c:forEach>
</body>

</html>