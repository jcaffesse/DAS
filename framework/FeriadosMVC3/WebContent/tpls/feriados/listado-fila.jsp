<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<c:set var="feriado"     value="${empty requestScope.feriado ? param.feriado : requestScope.feriado}"/>
<c:set var="feriado_str" value="${!empty param.feriado_str ? param.feriado_str : param.feriado}"/>
<tr id="tr-${param.ind_feriado}">
    <td align="center">
    	<span id="feriado-${param.ind_feriado}">
	        ${feriado_str}
	        <input type="hidden" id="feriado-${param.ind_feriado}" name="feriado" value="${feriado}" />
	        <input type="hidden" id="feriado-str-${param.ind_feriado}" name="feriado_str" value="${feriado_str}" />
        	<input type="hidden" name="ind_feriado" value="${param.ind_feriado}" />
        </span>   
    	<span id="exec-feriado-${param.ind_feriado}"></span>
    </td>
    <td align="left">
    	<span id="desc_feriado-${param.ind_feriado}">
	        ${param.desc_feriado}
	        <input type="hidden" id="desc_feriado-${param.ind_feriado}" name="desc_feriado" value="${param.desc_feriado}" />
	    </span>
    	<span id="exec-desc_feriado-${param.ind_feriado}"></span>
    </td>
    <td align="left">
    	<span id="tipo_feriado-${param.ind_feriado}">
	        <fmt:message key="${param.tipo_feriado}" bundle="${etq}" />
	        <input type="hidden" id="tipo_feriado-${param.ind_feriado}" name="tipo_feriado" value="${param.tipo_feriado}" />
	    </span>    
    	<span id="exec-tipo_feriado-${param.ind_feriado}"></span>
    </td>
    <td align="left">
    	<a id="editar-${param.ind_feriado}" href="#" onclick="jFeriados.editar(${param.ind_feriado});return false;"><fmt:message key="editar" bundle="${etq}" /></a>
    	<a id="eliminar-${param.ind_feriado}" href="#" onclick="jFeriados.eliminar(${param.ind_feriado});return false;"><fmt:message key="eliminar" bundle="${etq}" /></a>
    	<a id="idiomas-${param.ind_feriado}" href="#" onclick="jFeriados.getIdiomas(${param.ind_feriado});return false;"><fmt:message key="idiomas" bundle="${etq}" /></a>
    </td>
</tr>
