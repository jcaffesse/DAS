<%-- 
    Document   : ejemplo2
    Created on : 25/08/2013, 15:59:39
    Author     : mpgaviotta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo 2</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
    </head>
    <body>
        <h1>Formulario de Carga</h1>
        <form action="ejemplo2.jsp" method="post">
            <table border="0" cellpadding="1" cellspacing="1">
                <thead>
                    <tr>
                        <td colspan="4">Datos Personales</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th align="right">Apellido:</th>
                        <td colspan="3"><input type="text" name="apellido" value="${param.apellido}" maxlength="40" size="60"/></td>
                    </tr>
                    <tr>
                        <th align="right">Nombre:</th>
                        <td colspan="3"><input type="text" name="nombre" value="${param["nombre"]}" maxlength="40" size="60"/></td>
                    </tr>
                    <tr>
                        <th align="right">Edad:</th>
                        <td><input type="text" name="edad" value="${param.edad}" maxlength="3" size="10"/></td>
                        <th align="right">Sexo:</th>
                        <td>
                            <select name="sexo">
                                <option value="F" ${param.sexo eq "F" || empty param.sexo ? "selected" : ""}>Femenino</option>
                                <option value="M" ${param.sexo eq "M" ? "selected" : ""}>Masculino</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4" align="right"><input type="submit" name="boton" value="Recargar"/></td>
                    </tr>
                </tfoot>
            </table>            
        </form>        
        <br/>
        <a href="index.jsp" target="_self">Volver al index principal</a>
    </body>
</html>
