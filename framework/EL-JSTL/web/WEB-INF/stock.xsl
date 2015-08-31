<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : stock.xsl
    Created on : 25 de agosto de 2013, 18:50
    Author     : mpgaviotta
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Ejemplo 11</title>
                <link rel="stylesheet" type="text/css" href="./css/style.css"/>
            </head>
            <body>
                <h1>Productos faltantes</h1>
                <table border="1" cellpadding="0" cellspacing="1" width="50%">
                    <colgroup>
                        <col width="20%"/>
                        <col width="80%"/>
                    </colgroup>
                    <thead>
                         <tr>
                             <td>Id.</td>
                             <td>Nombre</td>
                         </tr>
                    </thead>
                    <tbody>
                    <xsl:for-each select="root/producto">
                        <xsl:if test="./cant = 0">
                            <tr>
                                <td><xsl:value-of select="./id"/></td>
                                <td><xsl:value-of select="./nom"/></td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                    </tbody>
                </table>      
                <br/><br/>
                <a href="index.jsp" target="_self">Volver al index principal</a>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
