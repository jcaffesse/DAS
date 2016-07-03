<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${idioma}" />
<fmt:parseDate var="fecha_real" value="${idiomas[0].feriado}" pattern="yyyy-MM-dd" />
<fmt:formatDate var="feriado" value="${fecha_real}" dateStyle="full" timeStyle="medium" pattern="${formato}"/>
<h3><fmt:message key="tituloIdiomas" bundle="${etq}" /> ${feriado}</h3>
<form id="idferiados" action="javascript:void(null)" method="post">
	<input type="hidden" name="feriado" value="${feriado}"/>
    <table class="width400">
    	<thead>
	        <tr>
	            <th align="left"><fmt:message key="nomFeriado" bundle="${etq}" /></th>                    
	        </tr>
	    </thead>
        <tbody>
        <c:forEach var="idferiado" items="${requestScope.idiomas}">
        	<tr>
        		<td>
        			<nobr>
	        			<img src="./img/${idferiado.idioma}.gif" width="16px" height="11px"/>
	        			<input type="hidden" name="idioma" value="${idferiado.idioma}"/>
	        			<input type="text" name="nom_feriado" value="${idferiado.nomFeriado}"  maxlength="255" size="55"/>
					</nobr>
        		</td>
        	</tr>
        </c:forEach>
        </tbody>        
    </table>
</form>  
<br/>
<div class="width400">
	<span class="fr">
		<a id="guardar" href="#" onclick="jFeriados.guardarIdiomas();return false;"><fmt:message key="guardar" bundle="${etq}" /></a>
		<a id="volver" href="#" onclick="jFeriados.volver();return false;"><fmt:message key="volver" bundle="${etq}" /></a>
	</span>
</div>
