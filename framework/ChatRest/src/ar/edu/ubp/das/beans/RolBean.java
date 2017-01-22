/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.beans;

/**
 *
 * @author Jav
 */
public class RolBean implements Bean {
    
    private int id_rol;
    private String tipo_rol;
    
    public RolBean(int id, String tipo) {
        this.id_rol = id;
        this.tipo_rol = tipo;
    }
    
    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getTipo_rol() {
        return tipo_rol;
    }

    public void setTipo_rol(String tipo_rol) {
        this.tipo_rol = tipo_rol;
    }
    
    @Override
    public int compareTo(Bean bean) {
        String compare = RolBean.class.cast(bean).getTipo_rol();
        return this.getTipo_rol().compareTo(compare);
    }
    
}
