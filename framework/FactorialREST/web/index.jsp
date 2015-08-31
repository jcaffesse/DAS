<%-- 
    Document   : index
    Created on : 14/10/2013, 00:15:58
    Author     : mpgaviotta
--%>

<%@page import="rest.client.FactorialClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorial</title>
    </head>
    <body>
        <h1>Calculando factorial</h1>
        <!--
        Ejemplo 1
        -->
        <form action="webresources/factorial" method="get">
            N&uacute;mero: <input name="nro" type="text" /><br/><br/>
            <input type="submit" value="Calcular"/>
        </form>
        <hr/>
        <br/><br/>
        <!--
        Ejemplo 2
        -->
        <form action="index.jsp" method="post">
            N&uacute;mero: <input name="nro" type="text" /><br/><br/>
            <input type="submit" value="Calcular"/>
        </form>
        <%
        if(request.getParameter("nro") != null && !request.getParameter("nro").equals("")) {
            FactorialClient client = new FactorialClient();
            out.println(client.factorialP(request.getParameter("nro")));
            client.close();
        }
        %>
    </body>
</html>
