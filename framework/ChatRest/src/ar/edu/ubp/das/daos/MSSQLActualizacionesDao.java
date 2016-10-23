/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import ar.edu.ubp.das.beans.ActualizacionBean;
import ar.edu.ubp.das.beans.Bean;
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
            act.setFecha_actualizacion(fecha_act);
            act.setId_sala(result.getInt("id_sala"));
            
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
        this.setProcedure("dbo.get_actualizaciones_portal()");
        if (act.getId_sala() > 0) {
            this.setParameter(1, act.getId_sala());
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
