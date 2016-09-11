<%-- 
    Document   : listar-salas
    Author     : Jav
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="ar.edu.ubp.das.src.chat.properties.etiquetas" var="etq" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:message key="titulo" bundle="${etq}" /></title>
    <link type="text/css" rel="stylesheet" href="/ChatMVC3/util/StyleSheet.do/load=page,messages,chat" />
    <script type="text/javascript" src="/ChatMVC3/util/Javascript.do/load=jquery,utils,chat"></script>
    <script type="text/javascript">
    jUtils.changeLang("etiquetas_js", "${lang}");
    </script>
</head>
<body>
    <h1><fmt:message key="titulo" bundle="${etq}" /></h1>    
    <div id="message"></div>
    <div id="executing"></div>    
    <div id="response"></div>
    <div id="main">
        <table class="width700">
            <colgroup>
                <col width="100px"/>
                <col width="300px"/>
                <col width="120px"/>
                <col width="180px"/>
            </colgroup>
            <thead>
                <tr>
                    <th align="left"><fmt:message key="idUsuario" bundle="${etq}" /></th>
                    <th align="left"><fmt:message key="usuario" bundle="${etq}" /></th>
                    <th align="left"><fmt:message key="email" bundle="${etq}" /></th>                    
                    <th align="left"><fmt:message key="rol" bundle="${etq}" /></th>  
                </tr>
            </thead>
            <tbody>
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
                            <span>${usuario.getRol()}</span>
                        </td>
                    </tr> 
                </c:forEach>
            </tbody>        
        </table>
    </div>  
</body>
</html>
