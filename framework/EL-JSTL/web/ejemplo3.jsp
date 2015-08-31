<%-- 
    Document   : ejemplo3
    Created on : 25/08/2013, 16:14:11
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="/WEB-INF/tlds/myfunctions.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 3</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>${fn:toLower("HOLA MUNDO!!! :)")}</h1>
        <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
