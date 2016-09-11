/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.mvc.beans;

import java.util.Date;

/**
 *
 * @author Jav
 */
public class SalaBean implements Bean {
    
    private Integer id_sala;
    private String nombre_sala;
    private String desc_sala;
    private String tipo_sala;
    private Date ultima_act;
    private int color_sala;

    public int getColor() {
        return color_sala;
    }
    
    public Integer getId() {
        return this.id_sala;
    }
    
    public String getNombre() {
        return this.nombre_sala;
    }
    
    public String getDesc() {
        return this.desc_sala;
    }
        
    public String getTipo() {
        return this.tipo_sala;
    }
    
    public Date getUltimaAct() {
        return this.ultima_act;
    }
    
    public void setNombre(String n) {
        this.nombre_sala = n;
    }
    
    public void setDesc(String d) {
        this.desc_sala = d;
    }

    public void setTipo(String t) {
        this.tipo_sala = t;
    }
    
    
    @Override
    public int compareTo(Bean bean) {
        return 0;
    }
    
    @Override
    public String toString() {
        String str = "{ \"id_sala\" : \"" + id_sala + "\", \"nombre_sala\" : \"" + nombre_sala 
            +"\", \"desc_sala\" : \"" + desc_sala +"\", \"tipo_sala\" : \"" + tipo_sala + "\", \"color_sala\" : \"" + Integer.toString(color_sala) + "\"}";
        return str;
    }
}
