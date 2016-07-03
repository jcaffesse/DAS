package com.das.chat.wsmodelmap;

public class SendInvitationRequest
{
    private String idUsuario;
    private String idDestino;
    private String mensaje;

    public String getForm()
    {
        String res = new String();

        res += "id_usuario=";
        res += idUsuario;
        res += "&";

        res += "id_destino=";
        res += idDestino;
        res += "&";

        res += "mensaje=";
        res += mensaje;

        return res;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdDestino(String idDestino) {
        this.idDestino = idDestino;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
