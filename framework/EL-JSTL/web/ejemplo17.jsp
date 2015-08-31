<%-- 
    Document   : ejemplo17
    Created on : 25/08/2013, 20:09:03
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 17</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Formulario de Carga</h1>
        <form action="ejemplo17.jsp" method="post">
            <table border="0" cellpadding="1" cellspacing="1">
                <thead>
                    <tr>
                        <td colspan="4">Datos Personales</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th align="right">Apellido:</th>
                        <td colspan="3"><input type="text" name="ape" value="" maxlength="40" size="60"/></td>
                    </tr>
                    <tr>
                        <th align="right">Nombre:</th>
                        <td colspan="3"><input type="text" name="nom" value="" maxlength="40" size="60"/></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4" align="right"><input type="submit" name="boton" value="Mostrar Datos Ingresados"/></td>
                    </tr>
                </tfoot>
            </table>            
        </form>        
        <c:if test="${!empty param}">
            <jsp:useBean id="per" class="beans.PersonaBean" scope="request">
                <jsp:setProperty name="per" property="apellido" param="ape"/>
                <jsp:setProperty name="per" property="nombre" param="nom"/>
            </jsp:useBean>
            <h3>Par&aacute;metros Recibidos</h3>
            <p><b>Apellido</b>: ${param["ape"]}</p>
            <p><b>Nombre</b>: ${param["nom"]}</p>
            <h3>Directivas JSP</h3>
            <p><b>Apellido</b>: <jsp:getProperty name="per" property="apellido"/></p>
            <p><b>Nombre</b>: <jsp:getProperty name="per" property="nombre"/></p>
            <p><b>Nombre Completo</b>: <jsp:getProperty name="per" property="nombreCompleto" /></p>
            <h3>Scriptlets</h3>
            <p><b>Apellido</b>: <%= per.getApellido() %></p>
            <p><b>Nombre</b>: <%= per.getNombre() %></p>
            <p><b>Nombre Completo</b>: <%= per.getNombreCompleto() %></p>
            <h3>EL</h3>
            <p><b>Apellido</b>: ${per.apellido}</p>
            <p><b>Nombre</b>: ${per.nombre}</p>
            <p><b>Nombre Completo</b>: ${per.nombreCompleto}</p>
        </c:if>     
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
