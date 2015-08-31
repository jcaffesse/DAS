<%-- 
    Document   : ejemplo8
    Created on : 16/08/2013, 19:41:40
    Author     : mpgaviotta
--%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 8</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
       <c:import var="rssFeed" url="http://www.clarin.com/diario/hoy/um/sumariorss.xml"/>
       <x:parse var="rss" xml="${rssFeed}"/>
       <h1>
           <a href="<x:out select="$rss//*[name()='channel']/*[name()='link']" escapeXml="false"/>" target="_blank">
               <x:out select="$rss//*[name()='channel']/*[name()='title']" escapeXml="false"/>
           </a>
       </h1>
       <ul>
           <x:forEach select="$rss//*[name()='item']">
              <li>
                  <a href="<x:out select="./*[name()='link']"/>" target="_blank">
                      <x:out select="./*[name()='title']" escapeXml="false"/>
                  </a>
              </li>
           </x:forEach>
       </ul>
       <br/>
       <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
