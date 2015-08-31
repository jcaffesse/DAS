<%-- 
    Document   : ejemplo11
    Created on : 25/08/2013, 18:55:43
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<c:import var="stock" url="/WEB-INF/stock.xml"/>
<c:import var="stockHtml" url="/WEB-INF/stock.xsl"/>
<x:transform xml="${stock}" xslt="${stockHtml}"/>
