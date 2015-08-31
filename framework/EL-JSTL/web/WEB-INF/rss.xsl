<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : clarin.xsl
    Created on : 25 de agosto de 2013, 18:43
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
                <title>Ejemplo 10</title>
                <link rel="stylesheet" type="text/css" href="./css/style.css"/>
            </head>
            <body>
               <h1>
                   <a target="_blank">
                      <xsl:attribute name="href">
                         <xsl:value-of select="//*[name()='channel']/*[name()='link']"/>
                      </xsl:attribute>
                      <xsl:value-of select="//*[name()='channel']/*[name()='title']" />
                   </a>
               </h1>
               <ul>
               <xsl:for-each select="//*[name()='item']">
                    <li>
                        <a target="_blank">
                            <xsl:attribute name="href">
                                <xsl:value-of select="./*[name()='link']"/>
                            </xsl:attribute>
                            <xsl:value-of select="./*[name()='title']" />
                        </a>
                    </li>
               </xsl:for-each>
               </ul>
               <br/>
               <a href="index.jsp" target="_self">Volver al index principal</a>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
