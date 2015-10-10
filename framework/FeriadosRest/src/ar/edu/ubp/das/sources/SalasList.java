/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.sources;

import ar.edu.ubp.das.beans.SalaBean;
import java.util.ArrayList;

/**
 *
 * @author Rocio
 */
public class SalasList {
    private ArrayList<SalaBean> list;
    
    public SalasList() {
        list = new ArrayList();
    }
    public void addSala(SalaBean s) {
        this.list.add(s);
    }
    
    public ArrayList getSalas() {
        return this.list;
    }
    
    @Override
    public String toString() {
        String result = "[";
        for(int i=0; i<this.list.size();i++) {
            result += this.list.get(i).toString();
            if (i!= this.list.size()-1) {
                result += ",";
            }
            
        }
        result += "]";
        return result;
    }

}
