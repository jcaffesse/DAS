<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:set var="index" value="${index + 1}" scope="session"/>
<fmt:setLocale value="${lang}" />
<fmt:message key="formatoDP" bundle="${etq}" var="formatoDP" />
<tr id="tr-${index}">
    <td align="center">
    	<span id="feriado-${index}">
        	<input type="text" id="data-feriado-${index}" name="feriado" value="" maxlength="10" size="12" />
        	<input type="hidden" name="ind_feriado" value="${index}"/>
        </span>
    	<span id="exec-feriado-${index}"></span>
    	<script type="text/javascript">
    	$.datepicker.setDefaults($.datepicker.regional["${idioma}"]);
    	$("#data-feriado-${index}").datepicker({dateFormat: "${formatoDP}"});
    	$("#data-feriado-${index}").focus();
    	</script>
    </td>
    <td align="left">
    	<span id="desc_feriado-${index}">
        	<input type="text" id="data-desc_feriado-${index}" name="desc_feriado" value="" maxlength="255" size="45" />
    	</span>
    	<span id="exec-desc_feriado-${index}"></span>
    </td>
    <td align="left">
    	<span id="tipo_feriado-${index}">
	    	<select id="data-tipo_feriado-${index}" name="tipo_feriado">
	    		<option value=""></option>
		 		<c:catch var="exception">
		    		<c:import var="xml" url="/WEB-INF/tipos_feriados.xml"/>
					<x:parse var="txml" xml="${xml}" />
					<x:forEach var="tipo" select="$txml/tipos/tipo">
						<c:set var="t">
							<x:out select="$tipo"/>
						</c:set>
			        	<option value="${t}"><fmt:message key="${t}" bundle="${etq}" /></option>
			       	</x:forEach>
			    </c:catch>
		    </select>
		</span>
    	<span id="exec-tipo_feriado-${index}"></span>
    </td>
    <td align="left">
    	<a id="guardar-${index}" href="#" onclick="jFeriados.guardar(${index});return false;"><fmt:message key="guardar" bundle="${etq}" /></a>
    	<a id="cancelar-${index}" href="#" onclick="jFeriados.cancelarNuevo(${index});return false;"><fmt:message key="cancelar" bundle="${etq}" /></a>
    </td>
</tr>
