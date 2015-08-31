<%-- 
    Document   : ejemplo14
    Created on : 25/08/2013, 19:27:54
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 14</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Internacionalizaci&oacute;n</h1>
        <fmt:setLocale value="en_US" />
        <fmt:setBundle basename="properties.misMensajes" var="etq" />
        <h4>
            <fmt:message key="saludo" bundle="${etq}">
                <fmt:param value="Mariela" />
            </fmt:message>
            <fmt:message key="mensaje" bundle="${etq}" />
        </h4>
        <fmt:setLocale value="es_AR" />
        <fmt:bundle basename="properties.misMensajes">
            <h4>
                <fmt:message key="saludo">
                    <fmt:param value="Mariela" />
                </fmt:message>
                <fmt:message key="mensaje" />
            </h4>
        </fmt:bundle>
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
