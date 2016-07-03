/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Javier
 */
@XmlRootElement
public class InvitacionBean implements Bean {
    
    private UsuarioBean usr_orig;
    private int id_destino;
    private Date fecha_invitacion;
    private String mensaje_invitacion;
    private int estado_invitacion;

    public int getId_destino() {
        return id_destino;
    }

    @XmlElement
    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public UsuarioBean getUsr_orig() {
        return usr_orig;
    }

    @XmlElement    
    public void setUsr_orig(UsuarioBean usr) {
        this.usr_orig = usr;
    }

    public Date getFecha_invitacion() {
        return fecha_invitacion;
    }

    @XmlElement    
    public void setFecha_invitacion(Date fecha_invitacion) {
        this.fecha_invitacion = fecha_invitacion;
    }

    public String getMensaje_invitacion() {
        return mensaje_invitacion;
    }

    @XmlElement    
    public void setMensaje_invitacion(String mensaje_invitacion) {
        this.mensaje_invitacion = mensaje_invitacion;
    }

    public int getEstado() {
        return estado_invitacion;
    }

    @XmlElement
    public void setEstado(int estado) {
        this.estado_invitacion = estado;
    }
    
    @Override
    public String toString() {
        return "{\"usuario\" : " + usr_orig.toString() + ","
            + "\"id_destino\" : \"" + id_destino + "\",\"fecha_invitacion\" : \"" 
            + fecha_invitacion.toString() + "\", \"mensaje_invitacion\" : \"" 
            + mensaje_invitacion +"\"," + " \"estado\" : \"" + estado_invitacion + "\"}";
    }
    
    @Override
    public int compareTo(Bean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
