package com.das.chat.wsmodelmap;

public class GetUpdatesRequest
{
    private String idSala;

    public String getForm()
    {
        String res = new String();

        res += "id_sala=";
        res += idSala;

        return res;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }
}
