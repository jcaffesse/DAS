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
import ar.edu.ubp.das.beans.InvitacionBean;
import ar.edu.ubp.das.beans.SalaBean;
import ar.edu.ubp.das.beans.UsuarioBean;
import java.util.LinkedList;

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
        SalaBean sala;
        if (SalaBean.class.getSimpleName().equals(bean.getClass().getSimpleName())) {
            sala = SalaBean.class.cast(bean);
		
            this.connect();

            this.setProcedure("dbo.insert_sala(?,?,?,?)");  
            this.setParameter(1, sala.getNombre());
            this.setParameter(2, sala.getDesc());
            this.setParameter(3, sala.getTipo());
            this.setParameter(4, sala.getColor());
            
        } else if (InvitacionBean.class.getSimpleName().equals(bean.getClass().getSimpleName())) {
            InvitacionBean inv = InvitacionBean.class.cast(bean);
            sala = new SalaBean();
                sala.setNombre("Sala " + inv.getUsr_orig().getId() + inv.getId_destino());
                sala.setDesc("Sala privada");
                sala.setTipo("private");
                sala.createColor();
		
            this.connect();
		
            this.setProcedure("dbo.insert_sala_privada(?,?,?,?,?)");  
            this.setParameter(1, sala.getNombre());
            this.setParameter(2, sala.getDesc());
            this.setParameter(3, sala.getColor());
            this.setParameter(4, inv.getUsr_orig().getId());
            this.setParameter(5, inv.getId_destino());
        }
        
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
        List<Bean> list;
        
        this.connect();
        try {
            String beanClass = bean.getClass().getSimpleName();
            if (beanClass.equals(SalaBean.class.getSimpleName())){
                SalaBean sala = SalaBean.class.cast(bean);
                this.setProcedure("dbo.get_sala(?)");
                this.setParameter(1, sala.getId());
            }
            if (beanClass.equals(UsuarioBean.class.getSimpleName())) {
                this.setProcedure("dbo.get_salas_usuario(?)");
                this.setParameter(1, UsuarioBean.class.cast(bean).getId());
            }
        } catch (NullPointerException e) {
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
