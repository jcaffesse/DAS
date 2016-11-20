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
<table class="width700">
    <colgroup>
        <col width="100px"/>
        <col width="200px"/>
        <col width="150px"/>
        <col width="150px"/>
    </colgroup>
    <thead>
        <tr>
            <th align="left"><fmt:message key="idUsuario" bundle="${etq}" /></th>
            <th align="left"><fmt:message key="usuario" bundle="${etq}" /></th>
            <th align="left"><fmt:message key="email" bundle="${etq}" /></th>                    
            <th align="left"></th>  
        </tr>
    </thead>
    <tbody>
        <c:set var="id_sala" value="${empty requestScope.id_sala ? 4 : requestScope.id_sala}"/>
        <c:set var="token" value="${empty requestScope.token ? '' : requestScope.token}"/>        
        <c:forEach var="usuario" items="${requestScope.usuarios}" varStatus="status">
            <c:set var="index" value="${status.index}" scope="session"/>
            <tr>
                <td>
                    <span>${usuario.getId()}</span>
                </td>
                <td>
                    <span>${usuario.getNombre()}</span>
                </td>
                <td>
                    <span>${usuario.getEmail()}</span>
                </td>
                <td>
                    <span>
                        <a id="ingresar" href="#" 
                           onclick="jChat.expulsarUsuario('${usuario.getId()}', '${id_sala}');return false;">
                            <fmt:message key="expulsar" bundle="${etq}" />
                        </a>
                    </span>
                </td>
            </tr> 
        </c:forEach>
    </tbody>        
</table>

