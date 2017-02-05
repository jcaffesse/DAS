<%-- 
    Document   : detalle-sala
    Author     : Jav
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="ar.edu.ubp.das.src.chat.properties.etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>
<div class="col-md-2 border-cols"></div>
<div class="col-md-8 panel">
    <c:set var="sala" value="${sessionScope.sala}"/>
    <div class="titulo-sala col-md-12">
        <span><fmt:message key="detalleSala" bundle="${etq}" /> <strong>${sala.getNombre()}</strong></span>
    </div>
    <div class="titulo-sala col-md-12">
        <span><fmt:message key="participantes" bundle="${etq}" /> <strong class="participantes"></strong></span>
    </div>
    <div id="mensajes" class="mensajes list-container col-md-7">
    </div>
    <div id="usuarios" class="usuarios list-container col-md-5">
    </div>
    <div class="control-buttons col-md-9">
        <button type="button" class="btn" onclick="jChat.volverDashboard(); return false;"><fmt:message key="volver" bundle="${etq}" /></button>
    </div>
</div>
<div class="col-md-2 border-cols"></div>