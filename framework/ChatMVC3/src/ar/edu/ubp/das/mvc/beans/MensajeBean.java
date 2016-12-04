/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.mvc.beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jav
 */
public class MensajeBean implements Bean {
    
    private int id_mensaje;
    private UsuarioBean usuario;
    private int id_sala;
    private String mensaje;
    private String fecha_mensaje;
    
    public int getId_mensaje() {
        return id_mensaje;
    }
    
    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean u) {
        this.usuario = u;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha_mensaje() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date fecha = format.parse(fecha_mensaje);
        DateFormat pFormat = new SimpleDateFormat("d MMM HH:mm");
        return pFormat.format(fecha);
    }

    public void setFecha_mensaje(String fecha_mensaje) {
        this.fecha_mensaje = fecha_mensaje;
    }

    @Override
    public String toString() {
        return "{ \"id_mensaje\" : \"" + id_mensaje + "\", \"usuario\" : " 
            + usuario.toString() + ", \"id_sala\" : \"" + id_sala 
            + "\", \"mensaje\" : \"" + mensaje +"\", \"fecha_mensaje\" : \""
            + fecha_mensaje + "\"}";
    }
    
    @Override
    public int compareTo(Bean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
