/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.UsuarioSalaBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Javier
 */
public class MSSQLUsuariosSalasDao extends MSSQLDao{

    @Override
    public Bean make(ResultSet result) throws SQLException {
        Date fecha_ingreso = result.getTimestamp("fecha_ingreso");
        UsuarioSalaBean usuarioSala = new UsuarioSalaBean();
        usuarioSala.setId_usuario(result.getInt("id_usuario"));
        usuarioSala.setId_sala(result.getInt("id_sala"));
        usuarioSala.setEstado(result.getInt("estado_usuario"));
        usuarioSala.setFecha_ingreso(fecha_ingreso);
        usuarioSala.setEmail_usuario(result.getString("email_usuario"));
        usuarioSala.setNombre_usuario(result.getString("nombre_usuario"));
        usuarioSala.setRol_usuario(result.getInt("id_rol"));
        return usuarioSala;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        UsuarioSalaBean usuarioSala = UsuarioSalaBean.class.cast(bean);
		
        this.connect();
		
        this.setProcedure("dbo.insert_usuario_sala(?,?,?)");  
        this.setParameter(1, usuarioSala.getId_usuario());
        this.setParameter(2, usuarioSala.getId_sala());
        this.setParameter(3, usuarioSala.getEstado());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void update(Bean bean) throws SQLException {
        UsuarioSalaBean usuarioSala = UsuarioSalaBean.class.cast(bean);
		
        this.connect();
		
        this.setProcedure("dbo.update_usuario_sala(?,?,?)");
        this.setParameter(1, usuarioSala.getId_usuario());
        this.setParameter(2, usuarioSala.getId_sala());
        this.setParameter(3, usuarioSala.getEstado());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        UsuarioSalaBean usuarioSala = UsuarioSalaBean.class.cast(bean);
		
        this.connect();
		
        this.setProcedure("dbo.delete_usuario_sala(?,?)");
        this.setParameter(1, usuarioSala.getId_usuario());
        this.setParameter(2, usuarioSala.getId_sala());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        List<Bean> list;
        
        this.connect();
        
        this.setProcedure("dbo.get_usuarios_sala(?)");
        this.setParameter(1, UsuarioSalaBean.class.cast(bean).getId_sala());

        list = this.executeQuery();
        this.disconnect();
        
        return list;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
