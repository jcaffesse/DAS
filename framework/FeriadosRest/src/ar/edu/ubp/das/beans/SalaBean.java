/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Javier
 */
@XmlRootElement
public class SalaBean implements Bean {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private String tipo;
    
    public Integer getId() {
        return this.id;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getDesc() {
        return this.descripcion;
    }
        
    public String getTipo() {
        return this.tipo;
    }
    
    @XmlElement
    public void setId(Integer i) {
        this.id = i;
    }
    
    @XmlElement
    public void setNombre(String n) {
        this.nombre = n;
    }
    
    @XmlElement
    public void setDesc(String d) {
        this.descripcion = d;
    }

    @XmlElement
    public void setTipo(String t) {
        this.tipo = t;
    }
    
    @Override
    public int compareTo(Bean bean) {
        return 0;
    }
    
    @Override
    public String toString() {
        return "{ \"id_sala\" : \"" + id + "\", \"nombre_sala\" : \"" + nombre 
            +"\", \"desc_sala\" : \"" + descripcion +"\", \"tipo_sala\" : \"" + tipo + "\"}";
    }
    
    
}
