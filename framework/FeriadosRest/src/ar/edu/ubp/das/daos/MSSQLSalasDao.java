/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.SalaBean;

/**
 *
 * @author Javier
 */
public class MSSQLSalasDao extends MSSQLDao {

    @Override
    public Bean make(ResultSet result) throws SQLException {
        SalaBean sala = new SalaBean();
            sala.setId(result.getInt("id"));
            sala.setNombre(result.getString("nombre"));
            sala.setDesc(result.getString("descripcion"));
            sala.setParts(result.getInt("participantes"));
            
        return sala;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Bean bean) throws SQLException {
        SalaBean sala = SalaBean.class.cast(bean);
		
        this.connect();
		
        this.setProcedure("dbo.set_sala(?,?,?,?)");        
        this.setParameter(1, sala.getId());
        this.setParameter(2, sala.getNombre());
        this.setParameter(3, sala.getDesc());
        this.setParameter(4, sala.getParts());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        this.connect();
		
        this.setProcedure("dbo.del_sala(?)");        
        this.setParameter(1, SalaBean.class.cast(bean).getNombre());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        List<Bean> list = null;
        
        System.out.println("SELECT");
		
        this.connect();
        
        this.setProcedure("dbo.get_salas");
        list = this.executeQuery();
        this.disconnect();
        
        System.out.println("LISTA: "+list.toString());
        return list; 
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
