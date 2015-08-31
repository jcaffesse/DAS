<%-- 
    Document   : ejemplo6
    Created on : 16/08/2013, 19:01:34
    Author     : mpgaviotta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="/WEB-INF/tlds/myfunctions.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 6</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Libros</h1>
        <h3>c:forEach</h3>
        <form  action="ejemplo6.jsp" method="post">
            <c:forEach var="i" begin="0" end="7">
                <c:set var="lista" value="${lista}${fn:toAsciiToChar(i + 65)};"/>
                <input type="checkbox" id="libro${i}" name="libro" value="Libro ${fn:toAsciiToChar(i + 65)}"/><label for="libro${i}">Libro ${fn:toAsciiToChar(i + 65)}</label><br/>
            </c:forEach>
            <br/>
            <input type="submit" value="Mostrar selecci&oacute;n"/>
        </form>
        <c:set var="libros" value="${paramValues.libro}"/>
        <c:if test="${!empty libros}">
            <h4>Los libros seleccionados son:</h4>
            <ol>
            <c:forEach var="l" items="${libros}">
                <li>${l}</li>
            </c:forEach>
            </ol>
        </c:if>
        <br/>    
        <h1>Lista de valores: ${lista}</h1>
        <h3>c:forTokens</h3>
        <ul>
        <c:forTokens items="${lista}" delims=";" var="i" begin="2" end="5">
            <li>${i}</li>
        </c:forTokens>
        </ul>
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>        
    </body>
</html>
