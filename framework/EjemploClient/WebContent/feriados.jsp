<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="idioma" value="${empty param.idioma ? 'es' : param.idioma}" scope="session"/>
<fmt:setLocale value="${idioma}" />
<fmt:setBundle basename="ar.edu.ubp.das.properties.Etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><fmt:message key="titulo" bundle="${etq}" /></title>
	<link type="text/css" rel="stylesheet" href="./css/style.css" />
	<link type="text/css" rel="stylesheet" href="./css/jquery-ui.css" />
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/jquery.ui.js"></script>
	<script type="text/javascript" src="./js/jquery.datepicker.config.js"></script>
	<script type="text/javascript" src="./js/jquery.i18n.properties.js"></script>
	<script type="text/javascript" src="./js/utils.js"></script>
	<script type="text/javascript" src="./js/feriados.js"></script>
	<script type="text/javascript">
	jUtils.changeLang("Etiquetas_js", "${idioma}");
	</script>
</head>
<body>
	<h1><fmt:message key="titulo" bundle="${etq}" /></h1>    
	<div id="message"></div>
	<div id="executing"></div>    
	<div id="response"></div>
	<div id="main">    
		<form id="feriados" action="javascript:void(null)" method="post">
		    <table class="width700">
		    	<colgroup>
		    		<col width="100px"/>
		    		<col width="300px"/>
		    		<col width="120px"/>
		    		<col width="180px"/>
		    	</colgroup>
		    	<thead>
			        <tr>
			            <th align="center">
			                <fmt:message key="feriado" bundle="${etq}" />
			                <fmt:message key="formato" bundle="${etq}" var="formato" scope="session" />
			            </th>
			            <th align="left"><fmt:message key="descFeriado" bundle="${etq}" /></th>                    
			            <th align="left"><fmt:message key="tipoFeriado" bundle="${etq}" /></th>  
			            <th align="center"></th>                  
			        </tr>
			    </thead>
		        <tbody>
		        <c:forEach var="feriado" items="${requestScope.feriados}" varStatus="status">
			        <c:set var="index" value="${status.index}" scope="session"/>
	                <fmt:parseDate var="fecha_real" value="${feriado.feriado}" pattern="yyyy-MM-dd" />
	                <fmt:formatDate var="fecha" value="${fecha_real}" dateStyle="full" timeStyle="medium" pattern="${formato}"/>
		        	<c:import url="./feriados-fila.jsp">
		        		<c:param name="ind_feriado" value="${status.index}"/>
		        		<c:param name="feriado" value="${fecha}"/>
		        		<c:param name="desc_feriado" value="${feriado.descFeriado}"/>
		        		<c:param name="tipo_feriado" value="${feriado.tipoFeriado}"/>
		        	</c:import>	
		        </c:forEach>
		        </tbody>        
		    </table>
		</form>  
		<br/>
		<div class="width700">
			<span class="fl"><a href="index.jsp?idioma=es"><fmt:message key="espanol" bundle="${etq}" /></a> <a href="index.jsp?idioma=en"><fmt:message key="ingles" bundle="${etq}" /></a></span>
			<span class="fr"><a id="agregar" href="#" onclick="jFeriados.agregar();return false;"><fmt:message key="nuevo" bundle="${etq}" /></a></span>
		</div>
	</div>	
</body>
</html>