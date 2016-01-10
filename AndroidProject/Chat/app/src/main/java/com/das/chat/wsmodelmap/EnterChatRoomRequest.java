package com.das.chat.wsmodelmap;

/**
 * Created by Pablo on 27/09/2015.
 */
public class EnterChatRoomRequest
{
    private String idSala;
    private String sessionToken;

    public String getForm()
    {
        String res = new String();

        res += "id_sala=";
        res += idSala;

        return res;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
