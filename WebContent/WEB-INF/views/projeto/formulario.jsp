<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
    <h3>Adicionar Projeto</h3>
    <form action="adicionaProjeto" method="post">
        <input type="hidden" name="id" value="${equipe.id}">
        Nome: <textarea rows="1" cols="20" name="nome">${projeto.nome}</textarea><br/>
        <input type="submit" value="Adicionar">
    </form>
</body>
</html>