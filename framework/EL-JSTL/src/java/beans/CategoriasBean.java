/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author mpgaviotta
 */
public class CategoriasBean {

    private String[] categorias;

    public CategoriasBean() {	
        this.categorias = new String[100];
    }

    public String getCategoria(int pos) { 
	return categorias[pos]; 
    }

    public String[] getCategorias() {
        return this.categorias;
    }

    public void setCategoria(String categoria, int pos) { 
	this.categorias[pos] = categoria; 
    }
    
    public void setCategorias(String[] c) {
        this.categorias =c;
    }

}
