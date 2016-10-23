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
    <link type="text/css" rel="stylesheet" href="/ChatMVC3/util/StyleSheet.do/load=page,chat" />
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
                <col width="200px"/>
                <col width="200px"/>
                <col width="150px"/>
                <col width="150px"/>
            </colgroup>
            <thead>
                <tr>
                    <th align="left"><fmt:message key="sala" bundle="${etq}" /></th>
                    <th align="left"><fmt:message key="desc" bundle="${etq}" /></th>
                    <th align="left"><fmt:message key="tipo" bundle="${etq}" /></th>                    
                    <th align="left"></th>
                </tr>
            </thead>
            <tbody>
                <c:set var="id_usuario" value="${empty requestScope.id_usuario ? 4 : requestScope.id_usuario}"/>
                <c:set var="token" value="${empty requestScope.token ? '' : requestScope.token}"/>
                <c:forEach var="sala" items="${requestScope.salas}" varStatus="status">
                    <c:set var="index" value="${status.index}" scope="session"/>
                    <tr>
                        <td>
                            <span>${sala.getNombre()}</span>
                        </td>
                        <td>
                            <span>${sala.getDesc()}</span>
                        </td>
                        <td>
                            <span>${sala.getTipo()}</span>                            
                        </td>
                        <td>
                            <span>
                                <a id="ingresar" href="#" 
                                   onclick="jChat.ingresarSala('${id_usuario}', '${sala.getId()}', '${token}');return false;">
                                    <fmt:message key="ingresar" bundle="${etq}" />
                                </a>
                            </span>
                        </td>
                    </tr> 
                </c:forEach>
            </tbody>        
        </table>
    </div>  
</body>
</html>
