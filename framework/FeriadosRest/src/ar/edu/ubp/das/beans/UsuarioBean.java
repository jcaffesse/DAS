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
public class UsuarioBean implements Bean {
    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private Integer id_rol;
    private Date ultima_act;

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getIdRol() {
        return id_rol;
    }
    
    public Date getUltimaAct() {
        return ultima_act;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }
    
    @XmlElement
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }
    
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public void setIdRol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    @Override
    public int compareTo(Bean bean) {
        return 0;
    }
    
    @XmlElement
    public void setUltimaAct(Date d) {
       this.ultima_act = d;
    }
    
    @Override
    public String toString() {
        return "{ \"id_usuario\" : \"" + id + "\", \"nombre_usuario\" : \"" + nombre 
            +"\", \"email_usuario\" : \"" + email +"\", \"rol_usuario\" : \"" + id_rol + "\"}";
    }
}
