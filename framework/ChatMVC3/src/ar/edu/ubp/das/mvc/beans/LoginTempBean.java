/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.mvc.beans;

import java.awt.Color;

/**
 *
 * @author Jav
 */
public class LoginTempBean implements Bean{
    private Integer id;
    private String nombre;
    private String email;
    private Integer idRol;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void createColor() {
        this.color = new Color((int)(Math.random() * 0x1000000)).getRGB();
    }
    
    public String getId() {
        return String.valueOf(id);
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
    public String getRol() {
        return String.valueOf(idRol);
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdRol(Integer id_rol) {
        this.idRol = id_rol;
    }

    @Override
    public int compareTo(Bean bean) {
        return 0;
    }
    
    @Override
    public String toString() {
        return "["
            + "id: " + id + ", "
            + "nombre: " + nombre + ", " 
            + "email_usuario: " + email + ", "
            + "idRol: " + idRol + ", "
            + "color_usuario: " + Integer.toString(color)
            + "]";            
    }
}
