<%-- 
    Document   : ejemplo1
    Created on : 09/08/2013, 19:27:40
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="true" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 1</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Expression Language</h1>
        <h3>Operadores Matem&aacute;ticos</h3>
        ${2*4}<br/>
        ${5/2}<br/>
        ${5%2}<br/>
        ${2+2}<br/>
        ${7-2}<br/><br/>
        <h3>Operadores de Comparaci&oacute;n</h3>
        ${2 == '2'} o ${2 eq '2'}<br/>
        ${2 != 2} o ${2 ne 2}<br/>
        ${2 < 2} o ${2 lt 2}<br/>
        ${3 > 2} o ${3 gt 2}<br/>
        ${2 <= 2} o ${2 le 2}<br/>
        ${1 >= 2} o ${1 ge 2}<br/><br/>
        <h3>Operadores L&oacute;gicos</h3>        
        ${!(2 == 2)} o ${not(2 == 2)}<br/>
        ${(2 == 2) && (2 >= 0)} o ${(2 == 2) and (2 >= 0)}<br/>
        ${(2 == 2) || (2 != 2)} o ${(2 == 2) or (2 != 2)}<br/><br/><br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
