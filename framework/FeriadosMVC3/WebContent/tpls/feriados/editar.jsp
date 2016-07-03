<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<fmt:setLocale value="${lang}" />
<tr id="tr-${param.ind_feriado}">
    <td align="center">
    	<span id="feriado-${param.ind_feriado}">
    	    ${param.feriado_str}
        	<input type="hidden" id="data-feriado-${param.ind_feriado}" name="feriado" value="${param.feriado}" />
        	<input type="hidden" id="data-feriado-str-${param.ind_feriado}" name="feriado_str" value="${param.feriado_str}" />
        	<input type="hidden" name="ind_feriado" value="${param.ind_feriado}" />
        </span>
    	<span id="exec-feriado-${param.ind_feriado}"></span>
    </td>
    <td align="left">
    	<span id="desc_feriado-${param.ind_feriado}">
        	<input type="text" id="data-desc_feriado-${param.ind_feriado}" name="desc_feriado" value="${param.desc_feriado}" maxlength="255" size="45" />
        	<input type="hidden" name="desc_feriado_ant" value="${param.desc_feriado}" />
    	</span>
    	<span id="exec-desc_feriado-${param.ind_feriado}"></span>
    </td>
    <td align="left">
    	<span id="tipo_feriado-${param.ind_feriado}">
	    	<select id="data-tipo_feriado-${param.ind_feriado}" name="tipo_feriado">
	    		<option value=""></option>
		 		<c:catch var="exception">
		    		<c:import var="xml" url="/WEB-INF/tipos_feriados.xml"/>
					<x:parse var="txml" xml="${xml}" />
					<x:forEach var="tipo" select="$txml/tipos/tipo">
						<c:set var="t">
							<x:out select="$tipo"/>
						</c:set>
			        	<option ${t eq param.tipo_feriado ? "selected" : ""} value="${t}"><fmt:message key="${t}" bundle="${etq}" /></option>
			       	</x:forEach>
			    </c:catch>
		    </select>
		    <fmt:message var="tf" key="${param.tipo_feriado}" bundle="${etq}" />
        	<input type="hidden" name="tipo_feriado_ant" value="${param.tipo_feriado}" />
		</span>
    	<span id="exec-tipo_feriado-${param.ind_feriado}"></span>
    </td>
    <td align="left">
    	<a id="guardar-${param.ind_feriado}" href="#" onclick="jFeriados.guardar(${param.ind_feriado});return false;"><fmt:message key="guardar" bundle="${etq}" /></a>
    	<a id="cancelar-${param.ind_feriado}" href="#" onclick="jFeriados.cancelar(${param.ind_feriado});return false;"><fmt:message key="cancelar" bundle="${etq}" /></a>
    </td>
</tr>
