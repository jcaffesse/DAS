package com.das.chat.wsmodelmap;

/**
 * Created by Pablo on 27/09/2015.
 */
public class EnterChatRoomRequest
{
    private String idSala;
    private String sessionToken;

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getIdSala() {
        return this.idSala;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
