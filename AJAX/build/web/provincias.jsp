<%-- 
    Document   : provincias
    Created on : 02/09/2013, 22:59:40
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${!empty requestScope.provincias}">    
    <p>
        <b><fmt:message key="provincia" bundle="${etq}"/>:</b>
        <select id="cod_provincia" name="cod_provincia">
            <option value=""></option>
            <c:forEach var="item" items="${requestScope.provincias}">
                <option value="${item.codProvincia}">${item.provincia}</option>
            </c:forEach>
            <option value="[New]">[ <fmt:message key="nuevo" bundle="${etq}"/> ]</option>
        </select>
    </p>
</c:if>
