package com.das.chat.wsmodelmap;

/**
 * Created by Pablo on 27/09/2015.
 */
public class AddRoomRequest
{
    private String nombreSala;
    private String descSala;
    private String tipoSala;
    private String sessionToken;

    public String getForm()
    {
        String res = new String();

        res += "nombre_sala=";
        res += nombreSala;
        res += "&";

        res += "desc_sala=";
        res += descSala;
        res += "&";

        res += "tipo_sala=";
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