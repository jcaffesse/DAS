package com.das.chat.dao;

import java.io.Serializable;


public class ChatRoom implements Serializable
{
    private String idSala;
    private String nombreSala;
    private String descSala;
    private String tipoSala;
    private int color;
    private boolean alertaSala;

    public int getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Integer.valueOf(color);
    }

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

    public Boolean getAlertaSala() {
        return alertaSala;
    }

    public void setAlertaSala(Boolean alertaSala) {
        this.alertaSala = alertaSala;
    }
}
