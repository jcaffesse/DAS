/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var jPersona = {
    getListaPersona: function() {
        jUtils.executing("result");
        $.ajax({
            url: "index.jsp?action=PersonasLista",
            type: "post",
            dataType: "html",
            data: $.param($("input[type='text']", $("#criteria"))),
            error: function(hr) {
                jUtils.showing("message", hr.responseText);
                jUtils.hiding("result");
            },
            success: function(html) {
                jUtils.showing("result", html);
            }
        });
    },
    
    nuevaPersona: function() {
        jUtils.hiding("main", false);
        jUtils.executing("response");
        $.ajax({
            url: "index.jsp?action=PersonasNueva",
            type: "post",
            dataType: "html",
            error: function(hr) {
                jUtils.showing("message", hr.responseText);
                jUtils.showing("main");
                jUtils.hiding("response");
            },
            success: function(html) {
                jUtils.showing("response", html);
            }
        });
    },
    
    cancelar: function() {
        jUtils.hiding("message");
        jUtils.hiding("response");
        jUtils.showing("main");
    },
            
    guardarNuevo: function() {
        jUtils.hiding("message");

        if($.trim($("input[name='apellido']", $("#newdata")).val()) === "") {
            alert("Debe informar el apellido");
            $("input[name='apellido']", $("#newdata")).focus();
            return false;
        }
        
        if($.trim($("input[name='nombre']", $("#newdata")).val()) === "") {
            alert("Debe informar el nombre");
            $("input[name='nombre']", $("#newdata")).focus();
            return false;
        }
        
       if($.trim($("input[name='nro_documento']", $("#newdata")).val()) === "") {
            alert("Debe informar el documento");
            $("input[name='nro_documento']", $("#newdata")).focus();
            return false;
        }
        
        jUtils.hiding("response", false);
        jUtils.executing("executing");
        
        $.ajax({
            url: "index.jsp?action=PersonasGuardarNueva",
            type: "post",
            dataType: "html",
            data: $.param($("input[type='text'],select,input[type='checkbox']:checked", $("#newdata"))),
            error: function(hr) {
                jUtils.showing("message", hr.responseText);
                jUtils.hiding("executing");
                jUtils.showing("response");
            },
            success: function(html) {
                jUtils.hiding("response");
                jUtils.hiding("executing");
                jUtils.showing("main");
                jPersona.getListaPersona();
            }
        });
         
    }
    
}

