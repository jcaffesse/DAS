<%-- 
    Document   : index
    Created on : 25/08/2013, 22:58:00
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="es_AR" />
<fmt:setBundle basename="properties.listas" var="etq" scope="session"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title><fmt:message key="titulo" bundle="${etq}"/></title>
        <link type="text/css" rel="stylesheet" href="./css/style.css" />
        <script type="text/javascript" src="./js/jquery.js"></script>
        <script type="text/javascript" src="./js/utils.js"></script>
        <script type="text/javascript" src="./js/listas.js"></script>
    </head>
    <body>
        <h3><fmt:message key="titulo" bundle="${etq}"/></h3>
        <div id="message">
            <c:if test="${!empty requestScope.error}">
                <p class="error">${requestScope.error}</p>
            </c:if>
        </div>
        <form id="form" action="javascript:void(null)" method="post">
        <c:if test="${!empty requestScope.paises}">    
            <p>
                <b><fmt:message key="pais" bundle="${etq}"/>:</b>
                <select id="cod_pais" name="cod_pais" onchange="jListas.selPais();">
                    <option value=""></option>
                    <c:forEach var="item" items="${requestScope.paises}">
                        <option value="${item.codPais}">${item.pais}</option>
                    </c:forEach>
                    <option value="[New]">[ <fmt:message key="nuevo" bundle="${etq}"/> ]</option>
                </select>
            </p>
            <div id="provincias"></div>
        </c:if>
        </form>
    </body>
</html>
