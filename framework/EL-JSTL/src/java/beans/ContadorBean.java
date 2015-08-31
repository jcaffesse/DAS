/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author mpgaviotta
 */
public class ContadorBean {

    private int contador;
    
    public ContadorBean() {
        this.contador = 0;
    }
    
    public void setContador(int c) {
        this.contador += c;
    }
            
    public int getContador() {
        return this.contador;
    }
                
}
