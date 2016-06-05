/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.InvitacionBean;
import ar.edu.ubp.das.beans.UsuarioBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Javier
 */
public class MSSQLInvitacionesDao extends MSSQLDao{

    @Override
    public Bean make(ResultSet result) throws SQLException {
        Date fecha_invitacion = result.getTimestamp("fecha_invitacion");
        
        InvitacionBean invite = new InvitacionBean();
            invite.setId_usuario(result.getInt("id_usuario"));
            invite.setId_destino(result.getInt("id_destino"));
            invite.setFecha_invitacion(fecha_invitacion);
            invite.setMensaje_invitacion(result.getString("mensaje_invitacion"));
            invite.setEstado(result.getString("estado"));
        return invite;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        InvitacionBean inv = InvitacionBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.insert_invitacion(?,?,?)");
        this.setParameter(1, inv.getId_usuario());
        this.setParameter(2, inv.getId_destino());
        this.setParameter(3, inv.getMensaje_invitacion());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void update(Bean bean) throws SQLException {
        InvitacionBean inv = InvitacionBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.update_invitacion(?,?,?)");
        this.setParameter(1, inv.getId_usuario());
        this.setParameter(2, inv.getId_destino());
        this.setParameter(3, inv.getMensaje_invitacion());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        InvitacionBean inv = InvitacionBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.delete_invitacion(?,?)");
        this.setParameter(1, inv.getId_usuario());
        this.setParameter(2, inv.getId_destino());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        UsuarioBean usr = UsuarioBean.class.cast(bean);
        List<Bean> list;
        DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        
        this.connect();
        if (usr.getUltimaAct() != null) {
            this.setProcedure("dbo.get_invitaciones_usuario(?, ?)");
            this.setParameter(1, usr.getId());
            this.setParameter(2, sdf.format(usr.getUltimaAct()));
        } else {
            this.setProcedure("dbo.get_invitaciones_usuario(?)");
            this.setParameter(1, usr.getId());
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
