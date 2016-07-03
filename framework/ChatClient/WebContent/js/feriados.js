var jFeriados = {
	agregar: function() {		
		$.ajax({
            url: "./feriados-agregar.jsp",
            type: "post",
            dataType: "html",
            error: function(hr){
                jUtils.showing("message", hr.responseText);
            },
            success: function(tr) {
                $("table tbody").append(tr);
            }
        });
	},
	
	cancelarNuevo: function(index) {
		$("#tr-" + index).remove();
	},
	
	guardar: function(index) {
		jUtils.hiding("message");
		
		if($.trim($("#data-feriado-" + index).val()) == "") {
			alert($.i18n.prop('fechaObligatoria'));
			$("#data-feriado-" + index).focus();
			return false;
		}
		
		if($.trim($("#data-desc_feriado-" + index).val()) == "") {
			alert($.i18n.prop('descripcionObligatoria'));
			$("#data-desc_feriado-" + index).focus();
			return false;
		}

		if($.trim($("#data-tipo_feriado-" + index).val()) == "") {
			alert($.i18n.prop('tipoObligatorio'));
			$("#data-tipo_feriado-" + index).focus();
			return false;
		}
		
		jUtils.hiding("feriado-" + index, false);
		jUtils.hiding("desc_feriado-" + index, false);
		jUtils.hiding("tipo_feriado-" + index, false);
		
		jUtils.executing("exec-feriado-" + index);
		jUtils.executing("exec-desc_feriado-" + index);
		jUtils.executing("exec-tipo_feriado-" + index);
		
		$.ajax({
            url: "./feriados-guardar.jsp",
            type: "post",
            dataType: "html",
            data: $.param($("input[type=hidden],input[type=text],select", $("#tr-" + index))),
            error: function(hr){
                jUtils.showing("message", hr.responseText);
        		jUtils.hiding("exec-feriado-" + index);
        		jUtils.hiding("exec-desc_feriado-" + index);
        		jUtils.hiding("exec-tipo_feriado-" + index);
        		jUtils.showing("feriado-" + index);
        		jUtils.showing("desc_feriado-" + index);
        		jUtils.showing("tipo_feriado-" + index);
            },
            success: function(tr) {
        		$("#tr-" + index).replaceWith(tr);
            }
        });
	},
	
	eliminar: function(index) {
		if(confirm($.i18n.prop('confirmarBorrado'))) {		
			jUtils.hiding("message");
			jUtils.hiding("feriado-" + index, false);
			jUtils.hiding("desc_feriado-" + index, false);
			jUtils.hiding("tipo_feriado-" + index, false);
			
			jUtils.executing("exec-feriado-" + index);
			jUtils.executing("exec-desc_feriado-" + index);
			jUtils.executing("exec-tipo_feriado-" + index);
			
			$.ajax({
	            url: "./feriados-eliminar.jsp",
	            type: "post",
	            dataType: "html",
	            data: $.param($("input[type=hidden]", $("#tr-" + index))),
	            error: function(hr){
	                jUtils.showing("message", hr.responseText);
	        		jUtils.hiding("exec-feriado-" + index);
	        		jUtils.hiding("exec-desc_feriado-" + index);
	        		jUtils.hiding("exec-tipo_feriado-" + index);
	        		jUtils.showing("feriado-" + index);
	        		jUtils.showing("desc_feriado-" + index);
	        		jUtils.showing("tipo_feriado-" + index);
	            },
	            success: function(html) {
	        		$("#tr-" + index).remove();
	            }
	        });
		}
	},
	
	editar: function(index) {
		jUtils.hiding("message");
		jUtils.hiding("feriado-" + index, false);
		jUtils.hiding("desc_feriado-" + index, false);
		jUtils.hiding("tipo_feriado-" + index, false);
		
		jUtils.executing("exec-feriado-" + index);
		jUtils.executing("exec-desc_feriado-" + index);
		jUtils.executing("exec-tipo_feriado-" + index);

		$.ajax({
            url: "./feriados-editar.jsp",
            type: "post",
            dataType: "html",
            data: $.param($("input[type=hidden]", $("#tr-" + index))),
            error: function(hr){
                jUtils.showing("message", hr.responseText);
        		jUtils.hiding("exec-feriado-" + index);
        		jUtils.hiding("exec-desc_feriado-" + index);
        		jUtils.hiding("exec-tipo_feriado-" + index);
        		jUtils.showing("feriado-" + index);
        		jUtils.showing("desc_feriado-" + index);
        		jUtils.showing("tipo_feriado-" + index);
            },
            success: function(tr) {
        		$("#tr-" + index).replaceWith(tr);
                $("input[type=text]:first", $("#tr-" + index)).focus();
            }
        });
	},
	
	cancelar: function(index) {
		$.ajax({
            url: "./feriados-guardar.jsp",
            type: "post",
            dataType: "html",
            data: {'ind_feriado': $("input[name=ind_feriado]", $("#tr-" + index)).val(), 
            	   'feriado': $("input[name=feriado]", $("#tr-" + index)).val(),
            	   'desc_feriado': $("input[name=desc_feriado_ant]", $("#tr-" + index)).val(),
            	   'tipo_feriado': $("input[name=tipo_feriado_ant]", $("#tr-" + index)).val()},
            error: function(hr){
                jUtils.showing("message", hr.responseText);
            },
            success: function(tr) {
        		$("#tr-" + index).replaceWith(tr);
            }
        });
	},
	
	getIdiomas: function(index) {
		jUtils.hiding("message");
		jUtils.hiding("main", false);
		jUtils.executing("response");
		$.ajax({
            url: "./feriados-idiomas-editar.jsp",
            type: "post",
            dataType: "html",
            data: $.param($("input[type=hidden]", $("#tr-" + index))),
            error: function(hr){
                jUtils.showing("message", hr.responseText);
                jUtils.showing("main");
                jUtils.hiding("response");
            },
            success: function(html) {
                jUtils.showing("response", html);
                $("input[type=text]:first", $("#response")).focus();
            }
        });
	},
	
	guardarIdiomas: function(index) {
		jUtils.hiding("message");
		jUtils.hiding("response", false);
		jUtils.executing("executing");
		$.ajax({
            url: "./feriados-idiomas-guardar.jsp",
            type: "post",
            dataType: "html",
            data: $.param($("input[type=hidden],input[type=text]", $("#idferiados"))),
            error: function(hr){
                jUtils.showing("message", hr.responseText);
                jUtils.showing("response");
                jUtils.hiding("executing");
            },
            success: function(html) {
                jUtils.hiding("response");
                jUtils.hiding("executing");
                jUtils.showing("main");
            }
        });
	},

	volver: function() {
		jUtils.hiding("response");
		jUtils.showing("main");
	}
};
