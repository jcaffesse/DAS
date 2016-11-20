<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="ar.edu.ubp.das.src.feriados.properties.etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><fmt:message key="titulo" bundle="${etq}" /></title>
	<link type="text/css" rel="stylesheet" href="/FeriadosMVC3//util/StyleSheet.do/load=page,messages,jquery-ui,feriados" />
	<script type="text/javascript" src="/FeriadosMVC3/util/Javascript.do/load=jquery,jquery.ui,jquery.datepicker.config,jquery.i18n.properties,utils,feriados"></script>
	<script type="text/javascript">
	jUtils.changeLang("etiquetas_js", "${lang}", "/FeriadosMVC3");
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
	                <fmt:parseDate var="fecha_real" value="${feriado.getItem('feriado')}" pattern="yyyy-MM-dd" />
	                <fmt:formatDate var="fecha" value="${fecha_real}" dateStyle="full" timeStyle="medium" pattern="${formato}"/>
		        	<c:import url="./listado-fila.jsp">
		        		<c:param name="ind_feriado" value="${status.index}"/>
		        		<c:param name="feriado" value="${feriado.getItem('feriado')}"/>
		        		<c:param name="feriado_str" value="${fecha}"/>
		        		<c:param name="desc_feriado" value="${feriado.getItem('desc_feriado')}"/>
		        		<c:param name="tipo_feriado" value="${feriado.getItem('tipo_feriado')}"/>
		        	</c:import>	
		        </c:forEach>
		        </tbody>        
		    </table>
		</form>  
		<br/>
		<div class="width700">
			<span class="fl"><a href="/feriados/Listado.do/lang=es"><fmt:message key="espanol" bundle="${etq}" /></a> <a href="/feriados/Listado.do/lang=en"><fmt:message key="ingles" bundle="${etq}" /></a></span>
			<span class="fr">
                            <a id="agregar" href="#" onclick="jFeriados.agregar();return false;"><fmt:message key="nuevo" bundle="${etq}" /></a>
                            <a id="test" href="#" onclick="jFeriados.test();return false;"><fmt:message key="test" bundle="${etq}" /></a>
                        </span>
		</div>
	</div>	
</body>
</html>