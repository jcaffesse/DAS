/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.UsuarioBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Javier
 */
public class MSSQLUsuariosDao extends MSSQLDao {

    @Override
    public Bean make(ResultSet result) throws SQLException {
        UsuarioBean usuario = new UsuarioBean();
            usuario.setId(result.getInt("id_usuario"));
            usuario.setNombre(result.getString("nombre_usuario"));
            usuario.setEmail(result.getString("email_usuario"));
            usuario.setIdRol(result.getInt("id_rol"));
            
        return usuario;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        UsuarioBean usuario = UsuarioBean.class.cast(bean);
		
        this.connect();
		
        this.setProcedure("dbo.insert_usuario(?,?,?,?)");  
        this.setParameter(1, usuario.getNombre());
        this.setParameter(2, usuario.getEmail());
        this.setParameter(3, usuario.getPassword());
        this.setParameter(4, usuario.getIdRol());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void update(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        UsuarioBean usuario = UsuarioBean.class.cast(bean);
        this.connect();
        
        this.setProcedure("dbo.delete_usuario(?)");        
        this.setParameter(1, usuario.getId());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        List<Bean> list;
        
        this.connect();
        
        this.setProcedure("dbo.get_usuarios(?)");
        try {
            this.setParameter(1, UsuarioBean.class.cast(bean).getNombre());
        } catch(NullPointerException e) {
            this.setParameter(1, "");
        }

        list = this.executeQuery();
        this.disconnect();
        
        return list; 
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        UsuarioBean usuario = UsuarioBean.class.cast(bean);
        
        this.connect();
        this.setProcedure("dbo.validate_login(?, ?)");
        this.setParameter(1, usuario.getNombre());
        this.setParameter(2, usuario.getPassword());
        
        return this.executeValidation();
    }
    
}
