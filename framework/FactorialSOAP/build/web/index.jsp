<%-- 
    Document   : index
    Created on : 13/10/2013, 23:16:35
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorial</title>
    </head>
    <body>
        <h1>Calculando factorial</h1>
        <form action="index.jsp" method="get">
            N&uacute;mero: <input name="nro" type="text" /><br/><br/>
            <input type="submit" value="Calcular"/>
        </form>        
        <%-- start web service invocation --%>
        <%
        if(request.getParameter("nro") != null && !request.getParameter("nro").equals("")) {
            long nro = new Long(request.getParameter("nro"));

            sw.client.FactorialService_Service service = new sw.client.FactorialService_Service();
            sw.client.FactorialService port = service.getFactorialServicePort();
            java.lang.String result = port.factorial(nro);
            out.println("<p>Factorial: " + result + "</p>");
        }              
        %>
        <%-- end web service invocation --%><hr/>
    </body>
</html>
