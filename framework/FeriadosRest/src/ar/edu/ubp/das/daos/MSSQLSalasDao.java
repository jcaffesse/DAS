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
            sala.setId(result.getInt("id_sala"));
            sala.setNombre(result.getString("nombre_sala"));
            sala.setDesc(result.getString("desc_sala"));
            sala.setTipo(result.getString("tipo_sala"));
            sala.setColor(result.getInt("color_sala"));
            
        return sala;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        SalaBean sala = SalaBean.class.cast(bean);
		
        this.connect();
		
        this.setProcedure("dbo.insert_sala(?,?,?,?)");  
        this.setParameter(1, sala.getNombre());
        this.setParameter(2, sala.getDesc());
        this.setParameter(3, sala.getTipo());
        this.setParameter(4, sala.getColor());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void update(Bean bean) throws SQLException {
        SalaBean sala = SalaBean.class.cast(bean);
		
        this.connect();
        
        this.setProcedure("dbo.update_sala(?,?,?,?,?)");  
        this.setParameter(1, sala.getId());
        this.setParameter(2, sala.getNombre());
        this.setParameter(3, sala.getDesc());
        this.setParameter(4, sala.getTipo());
        this.setParameter(5, sala.getColor());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        SalaBean sala = SalaBean.class.cast(bean);
        this.connect();
        
        this.setProcedure("dbo.delete_sala(?)");        
        this.setParameter(1, sala.getId());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        SalaBean sala = SalaBean.class.cast(bean);
        List<Bean> list;
        
        this.connect();
        
        if (bean != null && bean.getClass() == SalaBean.class) {
            this.setProcedure("dbo.get_sala(?)");
            this.setParameter(1, sala.getId());
        } else {
            this.setProcedure("dbo.get_salas");
        }
        
        list = this.executeQuery();
        this.disconnect();
        
        return list; 
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
