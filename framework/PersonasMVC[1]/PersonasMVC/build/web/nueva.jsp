<%-- 
    Document   : nueva
    Created on : 01/11/2013, 20:00:25
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="es_AR"/>
<fmt:bundle basename="ar.edu.ubp.das.properties.etiquetas">
    <fmt:message var="acp" key="acp_button" scope="page"/>
    <fmt:message var="can" key="can_button" scope="page"/>
    <form id="newdata" method="post" action="javascript:void(null)">
        <table border="0">
            <tr>                
                <th class="ar"><fmt:message key="label_ln"/></th>
                <td colspan="5"><input type="text" name="apellido" maxlength="100" size="90"/></td>
            </tr>
            <tr>
                <th class="ar"><fmt:message key="label_fn"/></th>
                <td colspan="5"><input type="text" name="nombre" maxlength="100" size="90"/></td>
            </tr>
            <tr>
                <th class="ar"><fmt:message key="label_doc"/></th>
                <td>
                    <select name="tipo_documento">
                        <option value="DNI">DNI</option>
                        <option value="LC">LC</option>
                        <option value="LE">LE</option>
                        <option value="CI">CI</option>
                        <option value="PAS">PAS</option>
                    </select>
                    <input type="text" name="nro_documento" maxlength="20" size="10"/>
                </td>
                <th class="ar"><fmt:message key="label_s"/></th>
                <td>
                    <select name="sexo">
                        <option value="F"><fmt:message key="label_sf"/></option>
                        <option value="M"><fmt:message key="label_sm"/></option>
                    </select>
                </td>
                <th class="ar"><fmt:message key="label_l"/></th>
                <td>
                    <input type="checkbox" name="vive" checked="true"/>
                </td>                
            </tr>
            <tr>
                <th class="ar" colspan="6">
                    <input type="button" value="${acp}" onclick="jPersona.guardarNuevo()"/>&nbsp;&nbsp;&nbsp;<input type="button" value="${can}" onclick="jPersona.cancelar()"/>
                </th>
            </tr>
        </table>
    </form>
</fmt:bundle>