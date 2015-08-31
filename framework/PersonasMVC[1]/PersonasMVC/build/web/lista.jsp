<%-- 
    Document   : lista
    Created on : 01/11/2013, 19:23:33
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="es_AR"/>
<fmt:bundle basename="ar.edu.ubp.das.properties.etiquetas">
    <br/>
    <c:if test="${requestScope.form.personas.size() > 0}">
        <table border="0" width="100%">
            <colgroup>
                <col with="20%"/>
                <col with="40%"/>
                <col with="40%"/>
            </colgroup>
            <tr>
                <th class="al"><fmt:message key="result_doc"/></th>
                <th class="al"><fmt:message key="result_ln"/></th>
                <th class="al"><fmt:message key="result_fn"/></th>
            </tr>
            <c:forEach items="${requestScope.form.personas}" var="persona">
                <tr>
                    <td>${persona.getItem('tipo_documento')}-${persona.getItem('nro_documento')}</td>
                    <td>${persona.getItem('apellido')}</td>
                    <td>${persona.getItem('nombre')}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${requestScope.form.personas.size() == 0}">
        <b><fmt:message key="result_empty"/></b>
    </c:if>
</fmt:bundle>
