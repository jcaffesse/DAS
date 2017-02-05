<%-- 
    Document   : listar-usuarios
    Author     : Jav
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="ar.edu.ubp.das.src.chat.properties.etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>
<h3 class="subtitle">Usuarios</h3>
<ul>
    <c:forEach var="usuario" items="${requestScope.usuarios}" varStatus="status">
        <c:set var="index" value="${status.index}" scope="session"/>
        <li class="clearfix">
            <span class="nombre-usuario">${usuario.getNombre()}</span>
            <span class="email">(${usuario.getEmail()})</span>
            <a class="control-buttons pull-right"
                href onclick="jChat.expulsarUsuario(event, ${usuario.getId()}, ${sala.getId()})">
                <img class="expulsar-btn" src="img/exit.png" />
            </a>
        </li>
    </c:forEach>
</ul>
