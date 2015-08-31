<%-- 
    Document   : init
    Created on : 01/11/2013, 17:59:03
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.: Personas :.</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css" />
        <script lang="javascript" type="text/javascript" src="./js/jquery.js"></script>
        <script lang="javascript" type="text/javascript" src="./js/utils.js"></script>
        <script lang="javascript" type="text/javascript" src="./js/personas.js"></script>        
    </head>
    <body>
        <fmt:setLocale value="es_AR"/>
        <fmt:bundle basename="ar.edu.ubp.das.properties.etiquetas">
            <h1><fmt:message key="title"/></h1>
            <fmt:message key="criteria_button" var="cb" scope="page"/>
            <fmt:message key="new_button" var="nb" scope="page"/>
            <div id="message"></div>
            <div id="main">
                <form id="criteria" method="post" action="javascript:void(null)">
                    <table border="0">
                        <tr>
                            <th class="al paddingtop5 paddingbottom5"><fmt:message key="criteria_label"/></th>
                        </tr>
                        <tr>
                            <td class="al"><input type="text" name="string_busqueda" id="string_busqueda" value="" maxlength="255" size="109" /></td>
                        </tr>
                        <tr>
                            <th class="ar"><input type="button" value="${pageScope.cb}" onclick="jPersona.getListaPersona()"/></th>
                        </tr>
                    </table>
                    <div id="result"></div>
                    <br/><br/>
                    <input type="button" value="${pageScope.nb}" onclick="jPersona.nuevaPersona()"/>
                </form>
            </div>
        </fmt:bundle>
        <div id="executing"></div>
        <div id="response"></div>
    </body>
</html>
