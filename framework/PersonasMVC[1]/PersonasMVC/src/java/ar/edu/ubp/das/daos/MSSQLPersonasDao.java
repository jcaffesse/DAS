/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.daos;

import ar.edu.ubp.das.mvc.actions.DynaActionForm;
import ar.edu.ubp.das.mvc.daos.MSSQLDao;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author mpgaviotta
 */
public class MSSQLPersonasDao extends MSSQLDao {

    @Override
    public DynaActionForm make(ResultSet result) throws Exception {
        DynaActionForm form = new DynaActionForm();
        form.setItem("tipo_documento", result.getString("tipo_documento"));
        form.setItem("nro_documento",  result.getString("nro_documento"));
        form.setItem("apellido",       result.getString("apellido"));
        form.setItem("nombre",         result.getString("nombre"));
        form.setItem("sexo",           result.getString("sexo"));
        form.setItem("vive",           result.getString("vive"));
        return form;
    }

    @Override
    public void insert(DynaActionForm form) throws Exception {
        String vive = String.valueOf(form.getItem("vive"));
        
        this.setStatement("dbo.ins_persona(?, ?, ?, ?, ?, ?)");
        this.getStatement().setString(1, String.valueOf(form.getItem("apellido")));
        this.getStatement().setString(2, String.valueOf(form.getItem("nombre")));
        this.getStatement().setString(3, String.valueOf(form.getItem("tipo_documento")));
        this.getStatement().setString(4, String.valueOf(form.getItem("nro_documento")));
        this.getStatement().setString(5, String.valueOf(form.getItem("sexo")));
        this.getStatement().setString(6, (vive == null ? "N" : "S"));
        this.executeUpdate();
    }

    @Override
    public void update(DynaActionForm form) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(DynaActionForm form) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws Exception {
        this.setStatement("dbo.get_lista_personas(?)");
        this.getStatement().setString(1, String.class.cast(form.getItem("string_busqueda")));
        return this.execute();
    }

    @Override
    public boolean valid(DynaActionForm form) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
