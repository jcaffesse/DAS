/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author mpgaviotta
 */
public class PersonaBean {

    private String apellido, nombre;    
    
    public PersonaBean() { }
            
    public void setApellido(String a) {
        this.apellido = a;
    }
            
    public void setNombre(String n) {
        this.nombre = n;
    }
    
    public String getApellido() {
        return this.apellido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getNombreCompleto() {
        return this.apellido + ", " + this.nombre;
    }

}
