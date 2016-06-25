package com.das.chat.wsmodelmap;

/**
 * Created by Pablo on 27/09/2015.
 */
public class SendMessageRequest
{
    private String idUsuario;
    private String idSala;
    private String message;
    private String sessionToken;

    public String getForm()
    {
        String res = new String();

        res += "id_sala=";
        res += idSala;
        res += "&";

        res += "id_usuario=";
        res += idUsuario;
        res += "&";

        res += "mensaje=";
        res += message;

        return res;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIdSala() {
        return idSala;
    }

    public String getMessage() {
        return message;
    }
}
