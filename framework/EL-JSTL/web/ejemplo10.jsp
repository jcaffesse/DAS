<%-- 
    Document   : ejemplo10
    Created on : 25/08/2013, 18:45:28
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<c:import var="rssFeed" url="http://www.clarin.com/diario/hoy/um/sumariorss.xml"/>
<c:import var="rssFeedHtml" url="/WEB-INF/rss.xsl"/>
<x:transform xml="${rssFeed}" xslt="${rssFeedHtml}"/>
