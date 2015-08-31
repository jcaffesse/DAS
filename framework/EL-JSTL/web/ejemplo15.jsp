<%-- 
    Document   : ejemplo15
    Created on : 25/08/2013, 19:32:41
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 15</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Herramienta SQL</h1>
        <form name="sql" action="ejemplo15.jsp" method="post" target="_top">
            <textarea name="query" rows="10" cols="109"></textarea>
            <br/><br/>
            <input type="submit" value="Ejecutar"/>
        </form>
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>
        <c:set var="querys" value="${param.query}" scope="request"/>
        <c:catch var="exception">
            <c:if test="${!empty querys}">
                <hr/>
                <sql:setDataSource var="dataSource" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" url="jdbc:sqlserver://ocrux;databaseName=das;" user="sa" password="pyxis" />
                <c:forTokens delims=";" items="${querys}" var="query">
                    <c:choose>
                        <c:when test="${fn:indexOf(fn:toLowerCase(query), 'select') >= 0}">
                            <sql:query dataSource="${dataSource}" var="result">
                                ${query}
                            </sql:query>

                            <c:choose>
                                <c:when test="${result.rowCount == 0}">
                                    <p>La consulta no retornÃ³ filas</p>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${result.rowsByIndex}" var="row" varStatus="s">
                                        <c:if test="${s.first}">
                                            <table border="1" cellpadding="1" cellspacing="1">
                                                <thead>
                                                    <tr>
                                                        <c:forEach items="${result.columnNames}" var="col">
                                                            <td>${col}</td>
                                                        </c:forEach>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                        </c:if>
                                        <tr>
                                            <c:forEach items="${row}" var="value">
                                                <td>${value}</td>
                                            </c:forEach>
                                        </tr>        
                                        <c:if test="${s.last}">
                                                </tbody>
                                            </table>
                                        </c:if>
                                    </c:forEach>                                   
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <sql:transaction dataSource="${dataSource}" isolation="read_committed">
                                <sql:update var="updateCount">
                                    ${query}
                                </sql:update>
                            </sql:transaction>
                            <p>${updateCount} fila(s) afectada(s)</p>                        
                        </c:otherwise>
                    </c:choose>
                </c:forTokens>
            </c:if>
        </c:catch>
        <c:if test="${!empty exception}">
            <p>Error: ${exception}</p>
        </c:if>
    </body>
</html>
