<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
    <a href="javascript:window.history.go(-1)">Voltar</a>
    <form action="cadastraUsuario" method="post">
        Login: <input type="text" name="login"/>
        Senha: <input type="text" name="senha"/>
        <input type="submit" value="adicionar">
    </form>
</body>
</html>