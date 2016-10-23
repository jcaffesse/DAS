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

function login(){

}


function ingresarSala(id_usuario, id_sala, token){
    $.post('/ChatMVC3/chat/SalasJoin.do', {id_usuario: id_usuario, id_sala: id_sala, token: token})
        .done(function(data) {
            $('#main').html(data);
    });
}

function mostrarSala(id_sala) {
}

function expulsarUsuario(id_usuario, id_sala, token){
    $.post('/ChatMVC3/chat/ExpulsarUsuario.do', {id_usuario: id_usuario, id_sala: id_sala, token: token})
        .done(function(data) {
            //$('#main').html(data);
    });    
}