<%-- 
    Document   : ejemplo9
    Created on : 25/08/2013, 18:34:42
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Ejemplo 9</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
       <h1>Cereales</h1>
       <c:import var="cereales" url="/WEB-INF/cereales.xml"/>
       <x:parse var="cer" xml="${cereales}"/>
       <ol>
           <x:forEach var="c" select="$cer/root/cereales/cereal">
               <li><x:out select="$c" escapeXml="false" /></li>
           </x:forEach>
       </ol>
       <br/>
       <h1>Productos faltantes</h1>
       <c:import var="stock" url="/WEB-INF/stock.xml"/>
       <x:parse var="st" xml="${stock}"/>
       <table border="1" cellpadding="0" cellspacing="1" width="50%">
           <colgroup>
               <col width="20%"/>
               <col width="80%"/>
           </colgroup>
           <thead>
                <tr>
                    <td>Id.</td>
                    <td>Nombre</td>
                </tr>
           </thead>
           <tbody
           <x:forEach var="s" select="$st/root/producto">
               <x:if select="$s/cant = 0">
                   <tr>
                       <td><x:out select="$s/id" escapeXml="false" /></td>
                       <td><x:out select="$s/nom" escapeXml="false" /></td>
                   </tr>
               </x:if>
           </x:forEach>
           </tbody>
       </table>      
       <br/><br/>
       <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
