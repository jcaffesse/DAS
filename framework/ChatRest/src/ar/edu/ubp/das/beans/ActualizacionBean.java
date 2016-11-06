/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.beans;

import java.util.Date;

/**
 *
 * @author Jav
 */
public class ActualizacionBean implements Bean {
    
    private int id_actualizacion;
    private String nombre_accion;
    private String nombre_tipo;
    private int id_dato;
    private Date fecha_actualizacion;
    private int id_sala;

    public int getId_actualizacion() {
        return id_actualizacion;
    }

    public void setId_actualizacion(int id_actualizacion) {
        this.id_actualizacion = id_actualizacion;
    }

    public String getNombre_accion() {
        return nombre_accion;
    }

    public void setNombre_accion(String nombre_accion) {
        this.nombre_accion = nombre_accion;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public int getId_dato() {
        return id_dato;
    }

    public void setId_dato(int id_dato) {
        this.id_dato = id_dato;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }
    
    @Override
    public String toString(){
        return "{ \"id_actualizacion\" : \"" + id_actualizacion + "\", "
            + "\"nombre_accion\" : \"" + nombre_accion + "\", "
            + "\"nombre_tipo\" : \"" + nombre_tipo + "\", "
            + "\"id_dato\" : \"" + id_dato + "\", "
            + "\"fecha_actualizacion\" : \"" + fecha_actualizacion + "\", "
            + "\"id_sala\" : \"" + id_sala + "\" }";
    }
    
    @Override
    public int compareTo(Bean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}