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
 * @author Pablo
 */
@XmlRootElement
public class MensajeBean implements Bean {
    
    private int id_mensaje;
    private UsuarioBean usuario;
    private int id_sala;
    private String mensaje;
    private Date fecha_mensaje;
    
    public int getId_mensaje() {
        return id_mensaje;
    }
    
    @XmlElement
    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    @XmlElement
    public void setUsuario(UsuarioBean u) {
        this.usuario = u;
    }

    public int getId_sala() {
        return id_sala;
    }

    @XmlElement
    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getMensaje() {
        return mensaje;
    }

    @XmlElement
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha_mensaje() {
        return fecha_mensaje;
    }

    @XmlElement
    public void setFecha_mensaje(Date fecha_mensaje) {
        this.fecha_mensaje = fecha_mensaje;
    }

    @Override
    public String toString() {
        return "{ \"id_mensaje\" : \"" + id_mensaje + "\", \"usuario\" : " 
            + usuario.toString() + ", \"id_sala\" : \"" + id_sala 
            + "\", \"mensaje\" : \"" + mensaje +"\", \"fecha_mensaje\" : \""
            + fecha_mensaje.toString() + "\"}";
    }
    
    @Override
    public int compareTo(Bean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
