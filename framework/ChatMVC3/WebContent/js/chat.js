/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var jChat = {
    login: login,
    ingresarSala : ingresarSala,
    listarMensajes: listarMensajes,
    listarUsuarios: listarUsuarios,
    expulsarUsuario : expulsarUsuario,
    borrarMensaje : borrarMensaje,
    volverDashboard: volverDashboard,
    actualizarMensajesWatcher: actualizarMensajesWatcher,
    removerWatchers: removerWatchers,
    mostrarBorrar: mostrarBorrar
};

var mensajesWatcher;

function login() {
    var params = $('#login-form input');
    jChat.removerWatchers();
    $.ajax({
        url: "/ChatMVC3/chat/Valid.do/",
        type: "post",
        dataType: "html",
        data: params,
        error: function(err){
            jUtils.showing("message", err.responseText);
        },
        success: function(data) {
            var parsed = $.parseHTML(data);
            jUtils.hiding("message");
            $('#dashboard').html($(parsed).filter("div#dashboard")[0].innerHTML);
            $('.login-logo').addClass('dashboard');
        }
    });
};

function ingresarSala(id_sala){
    $.ajax({
        url: "/ChatMVC3/chat/SalasJoin.do",
        type: "post",
        dataType: "html",
        data: {id_sala: id_sala},
        error: function(err){
            jUtils.showing("message", err.responseText);
        },
        success: function(data) {
            /*jChat.removerWatchers();
            jChat.actualizarMensajesWatcher();*/
            jUtils.hiding("message");
            jUtils.hiding("dashboard");
            jUtils.showing("response", data);
            jChat.listarMensajes();
        }
    });        
};

function listarMensajes(){
    $.ajax({
        url: "/ChatMVC3/chat/MessagesList.do",
        type: "post",
        dataType: "html",
        data: {},
        error: function(err){
            jUtils.showing("message", err.responseText);
        },
        success: function(data) {
            /*jChat.removerWatchers();
            jChat.actualizarMensajesWatcher();*/
            jUtils.hiding("message");
            $("#mensajes").append(data);
            jChat.listarUsuarios();
        }
    });      
}

function listarUsuarios(){
    $.ajax({
        url: "/ChatMVC3/chat/UsuariosList.do",
        type: "post",
        dataType: "html",
        data: {},
        error: function(err){
            jUtils.showing("message", err.responseText);
        },
        success: function(data) {
            /*jChat.removerWatchers();*/
            jUtils.hiding("message");
            $("#usuarios").append(data);
        }
    });        
};

function expulsarUsuario(id_usuario, id_sala) {
    $.ajax({
        url: "/ChatMVC3/chat/ExpulsarUsuario.do",
        type: "post",
        dataType: "html",
        data: {id_usuario: id_usuario, id_sala: id_sala},
        error: function(err){
            jUtils.showing("message", err.responseText);
        },
        success: function(data) {
            jUtils.hiding("message");
        }
    });   
};

function borrarMensaje(event, id_mensaje){
    $.ajax({
        url: "/ChatMVC3/chat/EliminarMensaje.do",
        type: "post",
        dataType: "html",
        data: {id_mensaje: id_mensaje},
        error: function(err){
            jUtils.showing("message", err.responseText);
        },
        success: function(data) {
            $(event.target).closest('li').remove();
        }
    });   
};

function volverDashboard() {
    $.ajax({
        url: "/ChatMVC3/chat/Dashboard.do",
        type: "post",
        dataType: "html",
        data: {},
        error: function(err){
            jUtils.showing("message", err.responseText);
        },
        success: function(data) {
            jChat.removerWatchers();
            var parsed = $.parseHTML(data);
            jUtils.hiding("message");
            jUtils.hiding("response");
            jUtils.showing("dashboard", $(parsed).filter("div#dashboard")[0].innerHTML);
        }
    });    
};

function actualizarMensajesWatcher() {
    mensajesWatcher = setInterval(actualizarMensajes, 10*1000);

    function actualizarMensajes() {
        $.ajax({
            url: "/ChatMVC3/chat/ActualizarMensajes.do",
            type: "post",
            dataType: "html",
            data: {},
            error: function(err){
                jUtils.showing("message", err.responseText);
            },
            success: function(data) {
                $("#response table").append(data);
            }
        });
    };
};

function removerWatchers() {
    if (mensajesWatcher) {
        clearInterval(mensajesWatcher);
        mensajesWatcher = null;
    }    
}
 
function mostrarBorrar(e) {
    $('.mensaje').removeClass('selected').siblings('.control-buttons').hide();
    var mensaje = $(e.target).parent('.mensaje');
    mensaje.addClass('selected').siblings('.control-buttons').show();
}

