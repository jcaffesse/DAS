package com.das.chat.wsmodelmap;

public class UpdateInvitationRequest
{
    private String idUsuario;
    private String idDestino;
    private String estado;

    public String getForm()
    {
        String res = new String();

        res += "id_usuario=";
        res += idUsuario;
        res += "&";

        res += "id_destino=";
        res += idDestino;
        res += "&";

        res += "estado=";
        res += estado;

        return res;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdDestino(String idDestino) {
        this.idDestino = idDestino;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
