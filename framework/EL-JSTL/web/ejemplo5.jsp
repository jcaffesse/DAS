<%-- 
    Document   : ejemplo5
    Created on : 16/08/2013, 18:14:53
    Author     : mpgaviotta
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 5</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Validador</h1>
        <form action="ejemplo5.jsp" method="post">
            <table border="0" cellpadding="1" cellspacing="1" width="19%">
                <colgroup>
                    <col width="50%"/>
                    <col width="50%"/>
                </colgroup>
                <tbody>
                    <tr>
                        <th align="right">Ingresa un valor:</th>
                        <td><input type="text" name="nro" maxlength="10" size="7"/></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2" align="right"><input type="submit" value="Procesar"/></td>
                    </tr>
                </tfoot>
            </table>            
        </form>
        <br/>
        <c:set var="i" value="${param.nro}" scope="request" />
        <c:catch var="exception">
            <c:if test="${!empty i}">
                <h3>c:if</h3>
                <c:if test="${i mod 2 eq 0}">
                    <p>El valor ingresado es par</p>
                </c:if>
                <c:if test="${i mod 2 ne 0}">
                    <p>El valor ingresado es impar</p>
                </c:if>
            </c:if>
        </c:catch>
        <c:if test="${!empty exception}">
            <p>El valor ingresado debe ser un número</p>
        </c:if>
        <br/>
         <c:catch var="exception">
            <c:if test="${!empty i}">
                <h3>c:choose</h3>
                <c:choose>
                    <c:when test="${i mod 2 eq 0}">
                        <p>El valor ingresado es par</p>
                    </c:when>
                    <c:otherwise>
                        <p>El valor ingresado es impar</p>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </c:catch>
        <c:if test="${!empty exception}">
            <p>El valor ingresado debe ser un número</p>
        </c:if>
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>        
    </body>
</html>
