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
    removerWatchers: removerWatchers
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
            jChat.removerWatchers();           
            jChat.actualizarMensajesWatcher();
            jUtils.hiding("message");
            jUtils.hiding("dashboard");
            jUtils.showing("response", data);
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
            jChat.removerWatchers();
            jChat.actualizarMensajesWatcher();
            jUtils.hiding("message");
            jUtils.hiding("dashboard");
            jUtils.showing("response", data);
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
            jChat.removerWatchers();
            jUtils.hiding("message");
            jUtils.hiding("dashboard");
            jUtils.showing("response", data);
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
            $('#usr-'+id_usuario).remove();
        }
    });   
};

function borrarMensaje(id_mensaje){
    $.ajax({
        url: "/ChatMVC3/chat/EliminarMensaje.do",
        type: "post",
        dataType: "html",
        data: {id_mensaje: id_mensaje},
        error: function(err){
            jUtils.showing("message", err.responseText);
        },
        success: function(data) {
            jUtils.hiding("message");
            $('#msg-' + id_mensaje).remove();
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

