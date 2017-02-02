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
    <link rel="stylesheet" href="/ChatMVC3/util/StyleSheet.do/load=page,messages,jquery-ui,bootstrap,chat" />

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
        <div class="control-buttons" id="logout">
            <button type="button" onclick="jChat.logout(); return false;"><fmt:message key="logout" bundle="${etq}" /></button>
        </div>
    </div>
    <div id="executing"></div>    
    <div id="message" class="pull-right"></div>
    <div id="dashboard" class="row">
        <div class="col-md-4 border-cols"></div>
        <div class="login-container col-md-4">
            <div class="greeting"><fmt:message key="greeting" bundle="${etq}" /></div>
            <form id="login-form">
                <div class="input-group">
                    <div class="input-group-addon"><img src="img/user.png" class="icon-user"/></div>
                    <input class="login-input" type="text" id="login-user" name="user" value="" placeholder="<fmt:message key="nombreUsuario" bundle="${etq}" />" maxlength="64"/>
                </div>
                <div class="input-group">
                    <div class="input-group-addon"><img src="img/key32.png" class="icon-user"/></div>
                    <input class="login-input" type="password" id="login-pw" name="pw" value="" placeholder="<fmt:message key="password" bundle="${etq}" />" maxlength="64"/>
                </div>
                <button class="login-btn" 
                    id="login-submit" 
                    onclick="jChat.login();return false;">
                    <fmt:message key="ingresar" bundle="${etq}" />
                </button>
            </form>
        </div>
        <div class="col-md-4 border-cols"></div>
    </div>  
    <div id="response"></div>
</body>
</html>
