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
    <link type="text/css" rel="stylesheet" href="/ChatMVC3/util/StyleSheet.do/load=page,messages,jquery-ui,chat" />
    <script type="text/javascript" src="/ChatMVC3/util/Javascript.do/load=jquery,jquery.ui,jquery.i18n.properties,utils,chat"></script>
    <script type="text/javascript">
    jUtils.changeLang("etiquetas_js", "${lang}", "/ChatMVC3");
    </script>
</head>
<body>
    <h1><fmt:message key="titulo" bundle="${etq}" /></h1>    
    <div id="executing"></div>    
    <div id="message"></div>
    <div id="dashboard">    
        <form id="login-form">
            <table>
                <tbody>
                    <tr>
                        <td><fmt:message key="usuario" bundle="${etq}" />:</td>
                        <td>
                            <input class="login-input" type="text" id="login-user" name="user" value="" placeholder="<fmt:message key="nombreUsuario" bundle="${etq}" />" maxlength="64"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="password" bundle="${etq}" />:</td>
                        <td>
                            <input class="login-input" type="password" id="login-pw" name="pw" value="" placeholder="<fmt:message key="password" bundle="${etq}" />" maxlength="64"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button class="login-btn" 
                                id="login-submit" 
                                onclick="jChat.login();return false;">
                                <fmt:message key="ingresar" bundle="${etq}" />
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>  
        </form>
    </div>  
    <div id="response"></div>
</body>
</html>
