/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var jChat = {
    login: login,
    logout: logout,
    ingresarSala : ingresarSala,
    listarMensajes: listarMensajes,
    listarUsuarios: listarUsuarios,
    expulsarUsuario : expulsarUsuario,
    borrarMensaje : borrarMensaje,
    volverDashboard: volverDashboard,
    actualizarMensajesWatcher: actualizarMensajesWatcher,
    actualizarUsuariosWatcher: actualizarUsuariosWatcher,
    removerWatchers: removerWatchers,
    mostrarBorrar: mostrarBorrar
};

var mensajesWatcher;

function login() {
    var params = $('#login-form input');
    jChat.removerWatchers();
    $.ajax({
        url: '/ChatMVC3/chat/Valid.do/',
        type: 'post',
        dataType: 'html',
        data: params,
        error: function(err){
            jUtils.showing('message', err.responseText);
        },
        success: function(data) {
            var parsed = $.parseHTML(data);
            jUtils.hiding('message');
            $('#dashboard').html($(parsed).filter('div#dashboard')[0].innerHTML);
            $('.login-logo').addClass('dashboard');
            $('.logout-btn').show();
           
        }
    });
};

function logout() {
    $.ajax({
        url: '/ChatMVC3/chat/Logout.do/',
        type: 'post',
        dataType: 'html',
        data: {},
        error: function(err){
            jUtils.showing('message', err.responseText);
        },
        success: function(data) {
            jChat.removerWatchers();
            var parsed = $.parseHTML(data);
            jUtils.hiding('message');
            jUtils.hiding('logout-btn');
            $('#dashboard').html($(parsed).filter('div#dashboard')[0].innerHTML);
            jUtils.hiding('response');
            jUtils.showing('dashboard');
            $('.logout-btn').hide();
        }
    });
};

function ingresarSala(id_sala){
    $.ajax({
        url: '/ChatMVC3/chat/SalasJoin.do',
        type: 'post',
        dataType: 'html',
        data: {id_sala: id_sala},
        error: function(err){
            jUtils.showing('message', err.responseText);
        },
        success: function(data) {
            jChat.removerWatchers();
            jChat.actualizarMensajesWatcher();
            jUtils.hiding('message');
            jUtils.hiding('dashboard');
            $('logout-btn').show();
            
            jUtils.showing('response', data);
            jChat.listarMensajes();
        }
    });        
};

function listarMensajes(){
    $.ajax({
        url: '/ChatMVC3/chat/MessagesList.do',
        type: 'post',
        dataType: 'html',
        data: {},
        error: function(err){
            jUtils.showing('message', err.responseText);
        },
        success: function(data) {
            jChat.removerWatchers();
            jChat.actualizarMensajesWatcher();
            jUtils.hiding('message');
            $('#mensajes').append(data);
            jChat.listarUsuarios();
        }
    });      
}

function listarUsuarios(){
    $.ajax({
        url: '/ChatMVC3/chat/UsuariosList.do',
        type: 'post',
        dataType: 'html',
        data: {},
        error: function(err){
            jUtils.showing('message', err.responseText);
        },
        success: function(data) {
            jUtils.hiding('message');
            $('#usuarios').append(data);
            var list = $($.parseHTML(data)).filter('ul');
            if(list) {
                $('.participantes').html(list.children().length);
            }
        }
    });        
};

function expulsarUsuario(event, id_usuario, id_sala) {
    event.preventDefault();
    $.ajax({
        url: '/ChatMVC3/chat/ExpulsarUsuario.do',
        type: 'post',
        dataType: 'html',
        data: {id_usuario: id_usuario, id_sala: id_sala},
        error: function(err){
            jUtils.showing('message', err.responseText);
        },
        success: function(data) {
            jUtils.hiding('message');
            $(event.target).closest('li').remove();
        }
    });   
};

function borrarMensaje(event, id_mensaje){
    $.ajax({
        url: '/ChatMVC3/chat/EliminarMensaje.do',
        type: 'post',
        dataType: 'html',
        data: {id_mensaje: id_mensaje},
        error: function(err){
            jUtils.showing('message', err.responseText);
        },
        success: function(data) {
            $(event.target).closest('li').remove();
        }
    });   
};

function volverDashboard() {
    $.ajax({
        url: '/ChatMVC3/chat/Dashboard.do',
        type: 'post',
        dataType: 'html',
        data: {},
        error: function(err){
            jUtils.showing('message', err.responseText);
        },
        success: function(data) {
            jChat.removerWatchers();
            var parsed = $.parseHTML(data);
            jUtils.hiding('message');
            jUtils.hiding('response');
            jUtils.showing('dashboard', $(parsed).filter('div#dashboard')[0].innerHTML);
        }
    });    
};

function actualizarMensajesWatcher() {
    mensajesWatcher = setInterval(actualizarMensajes, 10*1000);

    function actualizarMensajes() {
        $.ajax({
            url: '/ChatMVC3/chat/ActualizarMensajes.do',
            type: 'post',
            dataType: 'html',
            data: {},
            error: function(err){
                console.log(err);
                jUtils.showing('message', 'Error al actualizar los mensajes: ' + err.responseText);
            },
            success: function(data) {
                $('#mensajes ul').append(data);
            }
        });
    };
};

function actualizarUsuariosWatcher() {
    /*mensajesWatcher = setInterval(actualizarMensajes, 10*1000);*/

    actualizarUsuarios();

    function actualizarUsuarios() {
        $.ajax({
            url: '/ChatMVC3/chat/ActualizarUsuarios.do',
            type: 'post',
            dataType: 'html',
            data: {},
            error: function(err){
                console.log(err);
                jUtils.showing('message', err.responseText);
            },
            success: function(data) {
                var items = $($.parseHTML(data)).filter('li');

                items.each(function(key, item){
                    var id = $(item).attr('id');
                    if( $('#'+id).length > 0 ) {
                        $('#'+id).replaceWith(item);
                    } else {
                        $('#usuarios ul').append(item);
                    }
                });
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

