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
<h2><fmt:message key="detalleSala" bundle="${etq}" /> <c:out value="${sessionScope.id_sala}"/></h2>
<table class="width700">
    <colgroup>
        <col width="200px"/>
        <col width="200px"/>
        <col width="150px"/>
        <col width="150px"/>
    </colgroup>
    <thead>
        <tr>
            <th align="left"><fmt:message key="usuario" bundle="${etq}" /></th>
            <th align="left"><fmt:message key="mensaje" bundle="${etq}" /></th>
            <th align="left"><fmt:message key="fechaMensaje" bundle="${etq}" /></th>                    
            <th align="left" colspan="2"></th>
        </tr>
    </thead>
    <tbody>
        <c:set var="user" value="${empty sessionScope.user ? requestScope.user : sessionScope.user}"/>
        <c:forEach var="mensaje" items="${requestScope.mensajes}" varStatus="status">
            <c:set var="index" value="${status.index}" scope="session"/>
            <tr id="msg-${index}">
                <td>
                    <span>${mensaje.getUsuario().getNombre()}</span>
                </td>
                <td>
                    <span>${mensaje.getMensaje()}</span>
                </td>
                <td>
                    <span>${mensaje.getFecha_mensaje()}</span>                            
                </td>
                <td>
                    <span>
                        <a id="ingresar" href="#" 
                           onclick="jChat.borrarMensaje('${mensaje.getId_mensaje()}');return false;">
                            <fmt:message key="borrarMensaje" bundle="${etq}" />
                        </a>
                    </span>
                </td>
                <td>
                    <span>
                        <a id="ingresar" href="#" 
                           onclick="jChat.expulsarUsuario('${mensaje.getUsuario().getId()}', '${sessionScope.id_sala}');return false;">
                            <fmt:message key="expulsarUsuario" bundle="${etq}" />
                        </a>
                    </span>
                </td>                
            </tr>
        </c:forEach>
    </tbody>
</table>
<br>
<br>
<div class="control-buttons">
    <button type="button" onclick="jChat.volverDashboard(); return false;"><fmt:message key="volver" bundle="${etq}" /></button>
    <button type="button" onclick="jChat.listarUsuarios(); return false;"><fmt:message key="usuarios" bundle="${etq}" /></button>
</div>