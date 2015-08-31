/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var jListas = {
  
    selPais: function() {
        if($.trim($("#cod_pais").val()) === "") {
            jUtils.hiding("provincias");
        }
        else if($.trim($("#cod_pais").val()) !== "[New]") {
            jUtils.hiding("message");
            jUtils.executing("provincias");
            $.ajax({
                url: "./ProvinciasServlet",
                type: "post",
                dataType: "html",
                data: $.param($("select", $("#form"))),
                error: function(hr){
                    jUtils.hiding("provincias");
                    jUtils.showing("message", hr.responseText);
                },
                success: function(html) {
                    jUtils.showing("provincias", html);
                }
            });
        }
    }
    
};

