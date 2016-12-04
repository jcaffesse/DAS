<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="ar.edu.ubp.das.src.chat.properties.etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>        
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