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
        
        UsuarioBean usr = new UsuarioBean();
            usr.setId(result.getInt("id_usuario"));
        InvitacionBean invite = new InvitacionBean();
            invite.setId_destino(result.getInt("id_destino"));
            invite.setFecha_invitacion(fecha_invitacion);
            invite.setMensaje_invitacion(result.getString("mensaje_invitacion"));
            invite.setEstado(result.getInt("estado_invitacion"));
        try {
            Dao dao = DaoFactory.getDao("Usuarios");
            List<Bean> list = dao.select(usr);
            if (!list.isEmpty()) {
                usr = UsuarioBean.class.cast(list.get(0));
                invite.setUsr_orig(usr);
            }
        } catch (SQLException e) {
            System.out.println("No es posible crear la Invitacion, el usuario destino"
                + " no existe" + e.getErrorCode()+ e.getMessage());
        }

        return invite;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        InvitacionBean inv = InvitacionBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.insert_invitacion(?,?,?)");
        this.setParameter(1, inv.getUsr_orig().getId());
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
        this.setParameter(1, inv.getUsr_orig().getId());
        this.setParameter(2, inv.getId_destino());
        this.setParameter(3, inv.getEstado());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        InvitacionBean inv = InvitacionBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.delete_invitacion(?,?)");
        this.setParameter(1, inv.getUsr_orig().getId());
        this.setParameter(2, inv.getId_destino());
        
        this.executeUpdate();
        
        this.disconnect();
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        String beanClass = bean.getClass().getSimpleName();
        List<Bean> list;
        this.connect();
        
        if (beanClass.equals(UsuarioBean.class.getSimpleName())) {
        
            UsuarioBean usr = UsuarioBean.class.cast(bean);
            Date ultimaAct = usr.getUltimaAct();
            if (ultimaAct != null) {
                java.sql.Timestamp sqlUA = new java.sql.Timestamp(ultimaAct.getTime());
                this.setProcedure("dbo.get_invitaciones_usuario(?, ?)");
                this.setParameter(1, usr.getId());
                this.setParameter(2, sqlUA.toString());
            } else {
                this.setProcedure("dbo.get_invitaciones_usuario(?)");
                this.setParameter(1, usr.getId());
            }
        } 
        
        if (beanClass.equals(InvitacionBean.class.getSimpleName())) {
            InvitacionBean inv = InvitacionBean.class.cast(bean);
            System.out.println("hello");
            this.setProcedure("dbo.get_invitacion(?,?)");
            this.setParameter(1, inv.getUsr_orig().getId());
            this.setParameter(2, inv.getId_destino());
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
