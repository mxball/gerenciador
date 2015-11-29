<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum"%>
<html>
<head>
    <link href="../resources/css/jquery.css" rel="stylesheet">
    <script src="../resources/js/jquery.js"></script>
    <script src="../resources/js/jquery-ui.js"></script>
    <link href="../resources/css/lista.css" rel="stylesheet">  
</head>
<body>
    <script type="text/javascript">
        function excluiAgora(id) {
            $.post("excluiTarefa",{'id' : id}, function(){
                $("#tarefa_" + id).hide();
            });
        }
    </script>
    <a href="../novaTarefaProjeto?id=${projeto.id}">Criar nova tarefa</a>
    <br/><br/>
    <table>
        <thead>
            <tr>
                <th>Todo</th>
                <th>Doing</th>
                <th>Unit Test</th>
                <th>Approving</th>
                <th>Done</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tarefas}" var="tarefa">
                <tr id="tarefa_${tarefa.id}">
                    <caelum:tarefa status="ToDo" tarefa="${tarefa}"></caelum:tarefa>
                    <caelum:tarefa status="Doing" tarefa="${tarefa}"></caelum:tarefa>
                    <caelum:tarefa status="Unit Test" tarefa="${tarefa}"></caelum:tarefa>
                    <caelum:tarefa status="Approving" tarefa="${tarefa}"></caelum:tarefa>
                    <caelum:tarefa status="Done" tarefa="${tarefa}"></caelum:tarefa>
                </tr>
            </c:forEach>
        </tbody>
    </table>    
</body>
</html>