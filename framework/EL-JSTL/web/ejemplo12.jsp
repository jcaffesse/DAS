<%-- 
    Document   : ejemplo12
    Created on : 25/08/2013, 18:58:49
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 12</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Formatos de Fechas</h1>
        <jsp:useBean id="hoy" class="java.util.Date" />
        <fmt:setLocale value="en_US" />
        <fmt:setTimeZone value="GMT" var="gmt" />
        <p><b>Fecha completa (en_US)</b>: <fmt:formatDate value="${hoy}" dateStyle="full" /></p>
        <p><b>Fecha con formato MM/d/yyyy hh:mm (en_US)</b>: <fmt:formatDate value="${hoy}" timeZone="${gmt}" dateStyle="full" timeStyle="medium" pattern="MM/d/yyyy hh:mm"/></p>
        <br>
        <fmt:setLocale value="es_AR" />
        <p><b>Fecha completa (es_AR)</b>: <fmt:formatDate value="${hoy}" dateStyle="full" /></p>
        <p><b>Fecha con formato d/MM/yyyy hh:mm (es_AR)</b>: <fmt:formatDate value="${hoy}" dateStyle="full" timeStyle="medium" pattern="d/MM/yyyy hh:mm"/></p>
        <br/>
        <h1>Parseo de Fechas</h1>
        <c:set var="fechaNac" value="14/10/1980 17:12:00" />
        <c:catch var="ex">
            <fmt:parseDate parseLocale="es_AR" type="both" dateStyle="short" timeStyle="short" var="fechaNacP">
                ${fechaNac}
            </fmt:parseDate>
            <p><b>Fecha Informada</b>: ${fechaNac}</p>
            <p><b>Fecha Parseada</b>: ${fechaNacP}</p>
            <p><b>Fecha (es_AR)</b>:
                <fmt:setLocale value="es_AR"/>
                <fmt:formatDate value="${fechaNacP}" dateStyle="short" timeStyle="short" type="both" /></p>
            </p>    
        </c:catch>   
        <c:if test="${!empty ex}">
            <p>La fecha informada no es válida. Error: ${ex}</p>
        </c:if>                
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
