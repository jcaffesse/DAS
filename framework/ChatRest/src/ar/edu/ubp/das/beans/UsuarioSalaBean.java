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
public class UsuarioSalaBean implements Bean {
    private Integer id_usuario;
    private Integer id_sala;
    private Integer estado;
    private Date fecha_ingreso;
    private String nombre_usuario;
    private String email_usuario;
    private Integer rol_usuario;

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    @XmlElement
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    @XmlElement
    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public Integer getRol_usuario() {
        return rol_usuario;
    }

    @XmlElement
    public void setRol_usuario(Integer rol_usuario) {
        this.rol_usuario = rol_usuario;
    }
    

    public Integer getId_usuario() {
        return id_usuario;
    }

    @XmlElement
    public void setId_usuario(Integer id) {
        this.id_usuario = id;
    }

    public Integer getId_sala() {
        return id_sala;
    }

    @XmlElement
    public void setId_sala(Integer id_sala) {
        this.id_sala = id_sala;
    }

    public Integer getEstado() {
        return estado;
    }

    @XmlElement
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    @XmlElement
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    @Override
    public int compareTo(Bean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return "{ \"id_usuario\" : \"" + id_usuario + "\", \"nombre_usuario\" : \"" + nombre_usuario 
            +"\", \"email_usuario\" : \"" + email_usuario +"\", \"rol_usuario\" : \"" + rol_usuario
            + "\", \"fecha_ingreso\" : \"" + fecha_ingreso + "\" }";
    }
    
    
}
