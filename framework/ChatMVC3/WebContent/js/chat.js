/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var jChat = {
    login: login,
    ingresarSala : ingresarSala,
    mostrarSala : mostrarSala,
    expulsarUsuario : expulsarUsuario
};

function login() {
    var params = $('#login-form input');

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
            console.log($(parsed).filter("div#main")[0].innerHTML);
            jUtils.hiding("message");
            $('#main').html($(parsed).filter("div#main")[0].innerHTML);
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
            jUtils.hiding("message");
            $('#main').html(data);
        }
    });        
};

function mostrarSala(id_sala) {

};

function expulsarUsuario(id_usuario, id_sala) {
    $.post('/ChatMVC3/chat/ExpulsarUsuario.do', {id_usuario: id_usuario, id_sala: id_sala})
        .done(function(data) {
            alert(data);
        });
}

/*function expulsarUsuario(id_usuario, id_sala, token){
    // $.post('/ChatMVC3/chat/ExpulsarUsuario.do', {id_usuario: id_usuario, id_sala: id_sala, token: token})
    //     .done(function(data) {
    //                 //$('#main').html(data);
    // });
    $.post('/ChatMVC3/chat/SalasJoin.do', {id_usuario: id_usuario, id_sala: id_sala, token: token})
        .done(function(data) {
            $('#main').html(data);
    });    
};*/