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
import ar.edu.ubp.das.beans.TokenBean;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Javier
 */
public class MSSQLTokensDao extends MSSQLDao {

    @Override
    public Bean make(ResultSet result) throws SQLException {
        Date fecha_expiracion = result.getTimestamp("fecha_expiracion");
        
        TokenBean bean = new TokenBean();
            bean.setId_token(result.getInt("id_token"));
            bean.setId_usuario(result.getInt("id_usuario"));
            bean.setToken(result.getString("token"));
            bean.setFecha_expiracion(fecha_expiracion);
            
        return bean;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        TokenBean token = TokenBean.class.cast(bean);
        
        //borrar tokens viejos asignados a un usuario
        this.delete(bean);
        
        this.connect();
		
        this.setProcedure("dbo.insert_token(?,?)");  
        this.setParameter(1, token.getId_usuario());
        this.setParameter(2, token.getToken());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void update(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        TokenBean token = TokenBean.class.cast(bean);
        
        this.connect();
		
        this.setProcedure("dbo.delete_token(?)");  
        this.setParameter(1, token.getId_usuario());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        TokenBean token = TokenBean.class.cast(bean);
        List<Bean> list;
        
        this.connect();
        this.setProcedure("dbo.get_token(?)");
        this.setParameter(1, token.getToken());
        
        list = this.executeQuery();
        this.disconnect();
        
        return list;        
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        TokenBean token = TokenBean.class.cast(bean);
        
        return (System.currentTimeMillis() <= token.getFecha_expiracion().getTime());
    }
    
}
