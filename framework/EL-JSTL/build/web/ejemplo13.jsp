<%-- 
    Document   : ejemplo13
    Created on : 25/08/2013, 19:10:10
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 13</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Formato de N&uacute;meros</h1>
        <h3>N&uacute;mero 712.03</h3>
        <c:set var="val1" value="712.03" />
        <fmt:setLocale value="en_US"/>
        <p>Formato (en_US): <fmt:formatNumber value="${val1}" /></p>
        <fmt:setLocale value="es_AR"/>
        <p>Formato (es_AR): <fmt:formatNumber value="${val1}" /></p>
        <fmt:setLocale value="de_DE"/>
        <p>Formato (de_DE): <fmt:formatNumber value="${val1}" /></p>
        <h3>Porcentaje 63.5</h3>
        <c:set var="val2" value="63.5" />
        <fmt:setLocale value="en_US"/>
        <p>Porcentaje (en_US): <fmt:formatNumber value="${val2}" type="percent" /></p>
        <fmt:setLocale value="es_AR"/>
        <p>Porcentaje (es_AR): <fmt:formatNumber value="${val2}" type="percent" /></p>
        <fmt:setLocale value="de_DE"/>
        <p>Porcentaje (de_DE): <fmt:formatNumber value="${val2}" type="percent" /></p>
        <h3>Moneda 631.09</h3>
        <c:set var="val3" value="631.09" />
        <fmt:setLocale value="en_US"/>
        <p>Moneda (en_US): <fmt:formatNumber value="${val3}" type="currency" /></p>
        <fmt:setLocale value="es_AR"/>
        <p>Moneda (es_AR): <fmt:formatNumber value="${val3}" type="currency" /></p>
        <fmt:setLocale value="de_DE"/>
        <p>Moneda (de_DE): <fmt:formatNumber value="${val3}" type="currency" /></p>
        <h3>Uso de un patr&oacute;n 123456789</h3>
        <c:set var="val4" value="123456789" />
        <p>###.###E0: <fmt:formatNumber value="${val4}" pattern="###.###E0" /></p>       
        <br/>
        <h1>Parseo de N&uacute;meros</h1>
        <h3>Parseo de moneda</h3>
        <fmt:parseNumber var="val1" type="currency" value="$123456,789" parseLocale="es_AR" />
        <p>${val1}</p>
        <h3>Parseo de enteros</h3>
        <fmt:parseNumber var="val2" value="123456,789" integerOnly="true" parseLocale="es_AR" />
        <p>${val2}</p>
        <h3>Parseo de n&uacute;meros</h3>
        <fmt:parseNumber var="val3" value="123456,789"  parseLocale="es_AR" />
        <p>${val3}</p>
        <h3>Parseo de porcentajes</h3>
        <fmt:parseNumber var="val4" type="percent" value="123456,789%" parseLocale="es_AR" />
        <p>${val4}</p>
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
