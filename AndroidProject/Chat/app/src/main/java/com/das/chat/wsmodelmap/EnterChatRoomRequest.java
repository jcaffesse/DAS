package com.das.chat.wsmodelmap;

/**
 * Created by Pablo on 27/09/2015.
 */
public class EnterChatRoomRequest
{
    private String idSala;
    private String idUsuario;
    private String estado;

    public String getForm()
    {
        String res = new String();

        res += "id_sala=";
        res += idSala;
        res += "&";

        res += "id_usuario=";
        res += idUsuario;
        res += "&";

        res += "estado=";
        res += estado;

        return res;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getIdSala() {
        return this.idSala;
    }

}
