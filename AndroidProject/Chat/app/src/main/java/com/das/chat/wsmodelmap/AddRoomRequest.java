package com.das.chat.wsmodelmap;


public class AddRoomRequest
{
    private String nombreSala;
    private String descSala;
    private String tipoSala;
    private String sessionToken;

    public String getForm()
    {
        String res = new String();

        res += "nombre=";
        res += nombreSala;
        res += "&";

        res += "desc=";
        res += descSala;
        res += "&";

        res += "tipo=";
        res += tipoSala;

        return res;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public void setDescSala(String descSala) {
        this.descSala = descSala;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }
}
