<%-- 
    Document   : listar-mensajes
    Author     : Jav
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="ar.edu.ubp.das.src.chat.properties.etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>
<ul>
    <c:forEach var="mensaje" items="${requestScope.mensajes}" varStatus="status">
        <c:set var="index" value="${status.index}" scope="session"/>
        <li class="clearfix">
            <div class="mensaje" onclick="jChat.mostrarBorrar(event)">
                <span class="nombre-usuario">${mensaje.getUsuario().getNombre()}</span>
                <span class="texto-mensaje">${mensaje.getMensaje()}</span>
                <span class="fecha">${mensaje.getFecha_mensaje()}</span>
            </div>
            <div class="control-buttons">
                <a href onclick="jChat.borrarMensaje(event, ${mensaje.getId_mensaje()}); return false;">
                    <img class="borrar-btn" src="img/cancel-button.png" />
                </a>
            </div>
        </li>
    </c:forEach>
</ul>