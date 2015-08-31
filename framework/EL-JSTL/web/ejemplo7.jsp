<%-- 
    Document   : ejemplo7
    Created on : 16/08/2013, 19:12:02
    Author     : mpgaviotta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="/WEB-INF/tlds/myfunctions.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 7</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>URL</h1>
        <c:url var="url" value="ejemplo2.jsp">
            <c:param name="apellido">PASTARINI</c:param>
            <c:param name="nombre">MARIELA</c:param>
            <c:param name="edad">32</c:param>
            <c:param name="sexo">F</c:param>
        </c:url>            
        <a href="${url}" target="_blank">Ir al ejemplo 2</a>
        <br/>
        <%--Al descomentar redireccionará a la página informada--%>
        <%--<c:redirect url="http://mi.ubp.edu.ar"/>--%>
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>        
   </body>
</html>
