<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="value" required="false" type="java.util.Date"%>

<input type="text" id="${id}" name="${name}" value="<fmt:formatDate value="${valor}" pattern="dd/MM/yyyy"/>"/>
<script type="text/javascript">
	$(function(){
		$("#${id}").datepicker({dateFormat: 'dd/MM/yyyy'});
	});

</script>