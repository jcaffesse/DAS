<%-- 
    Document   : ejemplo16
    Created on : 25/08/2013, 20:04:05
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 16</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Contador</h1>
        <jsp:useBean id="cont" class="beans.ContadorBean" scope="session"/>
        <jsp:setProperty name="cont" property="contador" value="1"/>
        <h4><b>Contador</b>: <jsp:getProperty name="cont" property="contador"/></h4>
        <h4><b>Contador</b>: ${cont.contador}</h4>
        <a href="ejemplo16.jsp" target="_self">Volver a cargar</a>&nbsp;&nbsp;&nbsp;<a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
