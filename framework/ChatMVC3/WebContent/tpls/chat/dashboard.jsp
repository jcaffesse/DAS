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
    <link type="text/css" rel="stylesheet" href="/ChatMVC3/util/StyleSheet.do/load=page,messages,jquery-ui,bootstrap,chat" />
    <script type="text/javascript" src="/ChatMVC3/util/Javascript.do/load=jquery,jquery.ui,jquery.i18n.properties,utils,chat"></script>
    <script type="text/javascript">
    jUtils.changeLang("etiquetas_js", "${lang}", "/ChatMVC3");
    </script>
</head>
<body>
    <div id="header" class="row">
        <div class="login-logo">
            <img src="img/logo.png"/>
            <span><fmt:message key="titulo" bundle="${etq}" /></span>
        </div>
        <div class="logout-btn  col-xs-2 pull-right" id="logout">
            <button type="button" class="btn white" onclick="jChat.logout(); return false;"><fmt:message key="logout" bundle="${etq}" /></button>
        </div>
    </div>
    <div id="executing"></div>    
    <div id="message"  class="panel"></div>
    <div id="dashboard" class="row">
        <div class="col-md-3 border-cols"></div>
        <div class="salas list-container col-md-6">
            <ul>
                <c:set var="user" value="${empty sessionScope.user ? requestScope.user : sessionScope.user}"/>
                <c:forEach var="sala" items="${sessionScope.salas}" varStatus="status">
                    <c:set var="index" value="${status.index}" scope="session"/>
                    <li class="clearfix" onclick="jChat.ingresarSala('${sala.getId()}');return false;">
                        <div class="col-xs-10">
                            <span class="titulo"><strong>${sala.getNombre()}</strong></span>
                            <span class="desc">${sala.getDesc()}</span>
                        </div>
                        <div class="col-xs-2">
                            <span class="ingresar-img clearfix">
                                <img id="ingresar" src="img/right-arrow-button.png"/>
                            </span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-3 border-cols"></div>
    </div>  
    <div id="response"></div>
</body>
</html>