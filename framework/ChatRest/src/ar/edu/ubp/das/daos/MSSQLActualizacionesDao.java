/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import ar.edu.ubp.das.beans.ActualizacionBean;
import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.UsuarioBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jav
 */
public class MSSQLActualizacionesDao extends MSSQLDao{

    @Override
    public Bean make(ResultSet result) throws SQLException {
        Date fecha_act = result.getTimestamp("fecha_actualizacion");
        ActualizacionBean act = new ActualizacionBean();
            act.setId_actualizacion(result.getInt("id_actualizacion"));
            act.setNombre_accion(result.getString("nombre_accion"));
            act.setNombre_tipo(result.getString("nombre_tipo"));
            act.setId_dato(result.getInt("id_dato"));
            act.setFecha_actualizacion(fecha_act);
            act.setId_sala(result.getInt("id_sala"));
            
        if(act.getNombre_tipo().equals("UsuarioSala")) {
            try {
                UsuarioBean usr = new UsuarioBean();
                    usr.setId(act.getId_dato());
                Dao dao = DaoFactory.getDao("Usuarios");
                List<Bean> usrList = dao.select(usr);
                if (!usrList.isEmpty()) {
                    usr = UsuarioBean.class.cast(usrList.get(0));
                    act.setUsuario(usr);
                }
            } catch (SQLException e) {
                System.out.println("No es posible crear la Invitacion, el usuario destino"
                    + " no existe" + e.getErrorCode()+ e.getMessage());
            }             
        }
            
        return act;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Bean bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        ActualizacionBean act = ActualizacionBean.class.cast(bean);
        List<Bean> list;
        this.connect();
        if (act.getId_sala() > 0) {
            Date ultimaAct = act.getUltima_act();
            if (ultimaAct != null) {
                java.sql.Timestamp sqlUA = new java.sql.Timestamp(ultimaAct.getTime());
                this.setProcedure("dbo.get_actualizaciones_portal(?, ?)");
                this.setParameter(1, act.getId_sala());
                this.setParameter(2, sqlUA.toString());
            } else {
                this.setProcedure("dbo.get_actualizaciones_portal(?)");
                this.setParameter(1, act.getId_sala());
            }            
        } else {
            this.setProcedure("dbo.get_actualizaciones_portal()");
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
