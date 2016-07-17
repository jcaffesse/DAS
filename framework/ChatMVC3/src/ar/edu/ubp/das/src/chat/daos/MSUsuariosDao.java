/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.src.chat.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jav
 */
public class MSUsuariosDao extends DaoImpl {

    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        DynaActionForm form = new DynaActionForm();
            form.setItem("id_usuario", String.valueOf(result.getInt("id_usuario")));
            form.setItem("nombre_usuario", String.valueOf(result.getInt("nombre_usuario")));
            form.setItem("email_usuario", String.valueOf(result.getInt("email_usuario")));
            form.setItem("id_rol", String.valueOf(result.getInt("id_rol")));
            form.setItem("color_usuario", String.valueOf(result.getInt("color_usuario")));
            
        return form;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        this.connect();
		
        this.setProcedure("dbo.insert_usuario(?,?,?,?,?)");  
        this.setParameter(1, form.getItem("id_usuario"));
        this.setParameter(2, form.getItem("nombre_usuario"));
        this.setParameter(3, form.getItem("email_usuario"));
        this.setParameter(4, form.getItem("id_rol"));
        this.setParameter(5, form.getItem("color_usuario"));
        
        this.executeUpdate();
        
        this.disconnect();        
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {
        this.connect();
		
        this.setProcedure("dbo.update_usuario(?,?,?,?,?,?)");  
        this.setParameter(1, form.getItem("id_usuario"));
        this.setParameter(2, form.getItem("nombre_usuario"));
        this.setParameter(3, form.getItem("email_usuario"));
        this.setParameter(4, form.getItem("id_rol"));
        this.setParameter(5, form.getItem("color_usuario"));
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {
        this.connect();
        
        this.setProcedure("dbo.delete_usuario(?)");        
        this.setParameter(1, form.getItem("id_usuario"));
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        List<DynaActionForm> list;
        
        this.connect();
        
        if (form.getItem("id_usuario") != null) {
            this.setProcedure("dbo.get_usuario(?,?)");
            this.setParameter(1, form.getItem("id_usuario"));
            this.setParameter(2, form.getItem("nombre_usuario"));
        } else {
            this.setProcedure("dbo.get_usuarios()");
        }

        list = this.executeQuery();
        this.disconnect();
        
        return list; 
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.validate_login(?,?)");
        this.setParameter(1, form.getItem("nombre_usuario"));
        this.setParameter(2, form.getItem("password"));
        
        return this.executeValidation();
    }
    
}