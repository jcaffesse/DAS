/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.mvc.beans;

import java.awt.Color;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Javier
 */
@XmlRootElement
public class UsuarioBean implements Bean {
    private Integer id_usuario;
    private String nombre_usuario;
    private String email_usuario;
    private Integer rol_usuario;
    private int color_usuario;

    public int getColor() {
        return color_usuario;
    }

    public void setColor(int color) {
        this.color_usuario = color;
    }

    public void createColor() {
        this.color_usuario = new Color((int)(Math.random() * 0x1000000)).getRGB();
    }
    
    public String getId() {
        return String.valueOf(id_usuario);
    }

    public String getNombre() {
        return nombre_usuario;
    }

    public String getEmail() {
        return email_usuario;
    }
    public String getRol() {
        return String.valueOf(rol_usuario);
    }

    public void setId(Integer id) {
        this.id_usuario = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre_usuario = nombre;
    }
    
    public void setEmail(String email) {
        this.email_usuario = email;
    }

    public void setIdRol(Integer id_rol) {
        this.rol_usuario = id_rol;
    }

    @Override
    public int compareTo(Bean bean) {
        return 0;
    }
    
    @Override
    public String toString() {
        return "["
            + "id_usuario: " + id_usuario + ", "
            + "nombre_usuario: " + nombre_usuario + ", " 
            + "email_usuario " + email_usuario + ", "
            + "rol_usuario " + rol_usuario + ", "
            + "color_usuario " + Integer.toString(color_usuario)
            + "]";            
    }
}
