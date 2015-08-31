<%-- 
    Document   : ejemplo19
    Created on : 25/08/2013, 20:28:33
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="/WEB-INF/tlds/myfunctions.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 19</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Categor&iacute;as</h1>
        <form  action="ejemplo19.jsp" method="post">
            <c:forEach var="i" begin="0" end="7">
                <input type="checkbox" id="categoria${i}" name="categoria" value="${fn:toAsciiToChar(i + 65)}"/><label for="categoria${i}">Categor&iacute;a ${fn:toAsciiToChar(i + 65)}</label><br/>
            </c:forEach>
            <br/>
            <input type="submit" value="Mostrar selecci&oacute;n"/>
        </form>
        <c:if test="${!empty param}">
            <h3>Categor&iacute;as seleccionadas:</h3>
            <jsp:useBean id="cat1" class="beans.CategoriasBean" scope="request">
                <%
                String[] c = request.getParameterValues("categoria");

                for(int i = 0; i < c.length; i++) {
                    cat1.setCategoria(c[i], i);
                }
                %>
            </jsp:useBean>
            <p><b>Primera Categor&iacute;a Elegida (Scriptlet)</b>: <%= cat1.getCategoria(0)%> </p>
            <jsp:useBean id="cat2" class="beans.CategoriasBean" scope="request">
                <jsp:setProperty name="cat2" property="categorias" value="${paramValues['categoria']}" />
            </jsp:useBean>
            <p><b>Primera Categor&iacute;a Elegida (EL)</b>: ${cat2.categorias[0]}</p>
        </c:if>     
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
