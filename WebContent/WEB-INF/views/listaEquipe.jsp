<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <c:forEach var="equipe" items="${lista}">
    <p>${equipe}</p>
  </c:forEach>    

</html>