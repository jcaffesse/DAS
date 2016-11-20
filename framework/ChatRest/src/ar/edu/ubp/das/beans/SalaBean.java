/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.beans;

import java.awt.Color;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
    private Integer ultimo_mensaje;
    private Date fecha_desde;
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
    
    public Integer getUltimoMsg() {
        return ultimo_mensaje;
    }

    @XmlElement
    public void setUltimoMsg(Integer ultimo_mensaje) {
        this.ultimo_mensaje = ultimo_mensaje;
    }
    
    public Date getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(Date fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    @Override
    public int compareTo(Bean bean) {
        return 0;
    }
    
    @Override
    public String toString() {
        String str = "{ \"id_sala\" : \"" + id + "\", \"nombre_sala\" : \"" + nombre 
            +"\", \"desc_sala\" : \"" + descripcion +"\", \"tipo_sala\" : \"" + tipo + "\", \"color_sala\" : \"" + Integer.toString(color) + "\"}";
        return str;
    }
    
    public static List<Bean> getSalasPrivadas(List<Bean> salas) {
        List<Bean> result = new LinkedList<>();
        salas.stream()
            .filter((element) -> (SalaBean.class.cast(element).getTipo().equals("private")))
                .forEach((element) -> {
            result.add(element);
        });
        return result;
    }    
    
    
}
