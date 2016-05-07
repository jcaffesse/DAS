package com.das.chat.Model;

import java.io.Serializable;

/**
 * Created by pablo on 12/8/15.
 */
public class ChatRoom implements Serializable
{
    private String idSala;
    private String nombreSala;
    private String descSala;
    private String tipoSala;


    public void setIdSala(String idSala) {
        this.idSala = idSala;
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

    public String getIdSala() {
        return idSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public String getDescSala() {
        return descSala;
    }

    public String getTipoSala() {
        return tipoSala;
    }
}
