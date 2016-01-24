/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.MensajeBean;
import ar.edu.ubp.das.beans.SalaBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class MSSQLMensajesDao extends MSSQLDao{

    @Override
    public Bean make(ResultSet result) throws SQLException {
        MensajeBean msj = new MensajeBean();
            msj.setId_mensaje(result.getInt("id_mensaje"));
            msj.setId_sala(result.getInt("id_sala"));
            msj.setId_usuario(result.getInt("id_usuario"));
            msj.setFecha_mensaje(result.getDate("fecha_mensaje"));
            msj.setMensaje(result.getString("mensaje"));
                        
        return msj;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        MensajeBean msj = MensajeBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.insert_mensaje(?,?,?)");
        this.setParameter(1, msj.getId_usuario());
        this.setParameter(2, msj.getId_sala());
        this.setParameter(3, msj.getMensaje());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void update(Bean bean) throws SQLException {
        MensajeBean msj = MensajeBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.update_mensaje(?,?)");
        this.setParameter(1, msj.getId_mensaje());
        this.setParameter(3, msj.getMensaje());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        MensajeBean msj = MensajeBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.delete_mensaje(?)");
        this.setParameter(1, msj.getId_mensaje());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        //checkear si bean es SalaBean o UsuarioBean !
        SalaBean sala = SalaBean.class.cast(bean);
        List<Bean> list;
        
        this.connect();
        
        this.setProcedure("dbo.get_mensajes(?,?)");
        this.setParameter(1, sala.getId()); // o user id
        list = this.executeQuery();
        this.disconnect();
        
        return list; 
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
