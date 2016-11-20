<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<h3><fmt:message key="tituloIdiomas" bundle="${etq}" /></h3>
<form id="tet" action="javascript:void(null)" method="post">
    <table class="width400">
    	<thead>
            <tr>
                <th align="left"><fmt:message key="nomFeriado" bundle="${etq}" /></th>                    
            </tr>
        </thead>
        <tbody>
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
