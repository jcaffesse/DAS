<%-- 
    Document   : index
    Created on : 25/08/2013, 15:55:23
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EL / JSTL</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h2>Ejemplos de EL</h2>
        <ul>
            <li><a href="./ejemplo1.jsp" target="_self">Ejemplo 1: Expresiones</a></li>
            <li><a href="./ejemplo2.jsp" target="_self">Ejemplo 2: Obtener par&aacute;metros de un formulario</a></li>
            <li><a href="./ejemplo3.jsp" target="_self">Ejemplo 3: Funciones</a></li>
        </ul>
        <h2>Ejemplos de JSTL</h2>
        <h3>Librer&iacute;a CORE</h3>
        <ul>
            <li><a href="./ejemplo4.jsp" target="_self">Ejemplo 4: Variables</a></li>
            <li><a href="./ejemplo5.jsp" target="_self">Ejemplo 5: Condicionales</a></li>
            <li><a href="./ejemplo6.jsp" target="_self">Ejemplo 6: Iteradores</a></li>
            <li><a href="./ejemplo7.jsp" target="_self">Ejemplo 7: Construcci&oacute;n de URL</a></li>
        </ul>
        <h3>Librer&iacute;a XML (*)</h3>
        <ul>
            <li><a href="./ejemplo8.jsp" target="_self">Ejemplo 8: Lectura de RSS</a></li>
            <li><a href="./ejemplo9.jsp" target="_self">Ejemplo 9: Lectura de XML</a></li>
            <li><a href="./ejemplo10.jsp" target="_self">Ejemplo 10: Transformando RSS</a></li>
            <li><a href="./ejemplo11.jsp" target="_self">Ejemplo 11: Transformando XML</a></li>
        </ul>
        <h3>Librer&iacute;a FMT</h3>
        <ul>
            <li><a href="./ejemplo12.jsp" target="_self">Ejemplo 12: Manejo de Fechas</a></li>
            <li><a href="./ejemplo13.jsp" target="_self">Ejemplo 13: Manejo de N&uacute;meros</a></li>
            <li><a href="./ejemplo14.jsp" target="_self">Ejemplo 14: Manejo de Texto</a></li>
        </ul>
        <h3>Librer&iacute;a SQL (**)</h3>
        <ul>
            <li><a href="./ejemplo15.jsp" target="_self">Ejemplo 15: Herramientas SQL</a></li>
        </ul>
        <h2>Ejemplos de JavaBeans</h2>
        <ul>
            <li><a href="./ejemplo16.jsp" target="_self">Ejemplo 16: Contador</a></li>
            <li><a href="./ejemplo17.jsp" target="_self">Ejemplo 17: Datos Personales 1</a></li>
            <li><a href="./ejemplo18.jsp" target="_self">Ejemplo 18: Datos Personales 2</a></li>
            <li><a href="./ejemplo19.jsp" target="_self">Ejemplo 19: Categor&iacute;as</a></li>
        </ul>
        <div class="aclaracion">
            <p>(*) Si el servidor Web que utilizan es <b>Tomcat</b> requieren agregar el jar <b>&quot;<u>xalan-2.7.1.jar</u>&quot;</b>.<br/>
                Lo encontrar&aacute;n dentro de los fuentes del proyecto en el directorio <b>jars</b>.</p>
            <p>(**) Debe agregarse el jar <b>&quot;<u>sqljdbc4.jar</u>&quot;</b>.</p>
        </div>
    </body>
</html>
