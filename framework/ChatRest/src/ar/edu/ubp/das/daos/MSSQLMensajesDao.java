/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.MensajeBean;
import ar.edu.ubp.das.beans.SalaBean;
import ar.edu.ubp.das.beans.UsuarioBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class MSSQLMensajesDao extends MSSQLDao{

    @Override
    public Bean make(ResultSet result) throws SQLException {
        Date fecha_mensaje = result.getTimestamp("fecha_mensaje");
        
        UsuarioBean usr = new UsuarioBean();
            usr.setId(result.getInt("id_usuario"));
        
        MensajeBean msj = new MensajeBean();
            msj.setId_mensaje(result.getInt("id_mensaje"));
            msj.setId_sala(result.getInt("id_sala"));
            msj.setFecha_mensaje(fecha_mensaje);
            msj.setMensaje(result.getString("texto_mensaje"));
            
        try {
            Dao dao = DaoFactory.getDao("Usuarios");
            List<Bean> list = dao.select(usr);
            if (!list.isEmpty()) {
                usr = UsuarioBean.class.cast(list.get(0));
                msj.setUsuario(usr);
            }
        } catch (SQLException e) {
            System.out.println("No es posible crear la Invitacion, el usuario destino"
                + " no existe" + e.getErrorCode()+ e.getMessage());
        }            
                        
        return msj;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        MensajeBean msj = MensajeBean.class.cast(bean);
        
        this.connect();
        
        this.setProcedure("dbo.insert_mensaje(?,?,?)");
        this.setParameter(1, msj.getUsuario().getId());
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
        this.setParameter(2, msj.getMensaje());
        
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
        String procedureName = "dbo.get_mensajes_";
        String beanClass = bean.getClass().getSimpleName();
        
        procedureName += beanClass.substring(0, beanClass.length()-4).toLowerCase() + "(?";
        List<Bean> list;

        this.connect();
        
        
        if (beanClass.equals(SalaBean.class.getSimpleName())) {
            SalaBean sala = SalaBean.class.cast(bean);
            try {
                Date ultimaAct = sala.getUltimaAct();
                java.sql.Timestamp tmst = new java.sql.Timestamp(ultimaAct.getTime());
                procedureName += ", ?)";
                this.setProcedure(procedureName);
                this.setParameter(1, sala.getId());
                this.setParameter(2, tmst.toString());
            } catch (NullPointerException e) {
                procedureName += ")";
                this.setProcedure(procedureName);
                this.setParameter(1, sala.getId());
            }
        } else if (beanClass.equals(UsuarioBean.class.getSimpleName())) {
            UsuarioBean usuario = UsuarioBean.class.cast(bean);
            try {
                Date ultimaAct = usuario.getUltimaAct();
                java.sql.Timestamp tmst = new java.sql.Timestamp(ultimaAct.getTime());
                procedureName = "dbo.get_mensajes_usuario_salas(?, ?)";
                this.setProcedure(procedureName);
                this.setParameter(1, usuario.getId());
                this.setParameter(2, tmst.toString());
            } catch (NullPointerException e) {
                procedureName += ")";
                this.setProcedure(procedureName);
                this.setParameter(1, usuario.getId());
            }
        } else if (beanClass.equals(MensajeBean.class.getSimpleName())) {
            MensajeBean mensaje = MensajeBean.class.cast(bean);
            procedureName += ")";
            this.setProcedure(procedureName);
            this.setParameter(1, mensaje.getId_mensaje());
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
