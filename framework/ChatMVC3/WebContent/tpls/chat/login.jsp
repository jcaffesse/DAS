<%-- 
    Document   : listar-salas
    Author     : Jav
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="ar.edu.ubp.das.src.chat.properties.etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><fmt:message key="titulo" bundle="${etq}" /></title>
	<link type="text/css" rel="stylesheet" href="/ChatMVC3//util/StyleSheet.do/load=page,chat" />
	<script type="text/javascript" src="/ChatMVC3//util/Javascript.do/load=jquery,utils,chat"></script>
	<script type="text/javascript">
	jUtils.changeLang("etiquetas_js", "${lang}");
	</script>
</head>
<body>
	<h1><fmt:message key="titulo" bundle="${etq}" /></h1>    
	<div id="message"></div>
	<div id="executing"></div>    
	<div id="response"></div>
	<div id="main">
            <div id="test"><p>Login</p></div>
	</div>	
</body>
</html>
