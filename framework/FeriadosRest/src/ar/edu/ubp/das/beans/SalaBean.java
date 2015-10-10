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
 * @author Rocio
 */
@XmlRootElement
public class SalaBean implements Bean {
    
    private String nombre;
    private String descripcion;
    private Integer participantes;
    
   
    public String getNombre() {
        return this.nombre;
    }
    
    public String getDesc() {
        return this.descripcion;
    }
        
    public Integer getParts() {
        return this.participantes;
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
    public void setParts(Integer p) {
        this.participantes = p;
    }
    
    @Override
    public int compareTo(Bean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return "{\"nombre\": \""+ this.getNombre() + "\"}";
    }
    
    
}
