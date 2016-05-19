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
    
    private int id_usuario;
    private int id_destino;
    private Date fecha_invitacion;
    private String mensaje_invitacion;
    private String estado;

    public int getId_usuario() {
        return id_usuario;
    }

    @XmlElement
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_destino() {
        return id_destino;
    }

    @XmlElement    
    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
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

    public String getEstado() {
        return estado;
    }

    @XmlElement
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "{ \"id_usuario\" : \"" + id_usuario + "\", \"id_destino\" : \"" 
            + id_destino + "\", \"fecha_invitacion\" : \"" + fecha_invitacion.toString() 
            + "\", \"mensaje_invitacion\" : \"" + mensaje_invitacion +"\","
            + " \"estado\" : \"" + estado + "\" }";
    }
    
    @Override
    public int compareTo(Bean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
