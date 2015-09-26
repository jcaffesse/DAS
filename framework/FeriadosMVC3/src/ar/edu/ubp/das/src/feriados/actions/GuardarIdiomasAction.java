package ar.edu.ubp.das.src.feriados.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;

public class GuardarIdiomasAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        Dao dao = DaoFactory.getDao("IdiomasFeriado", "feriados");
        
        String idiomas[] = form.getItemValues("idioma");
        String nombres[] = form.getItemValues("nom_feriado");
        for(int i = 0, l = idiomas.length; i < l; i ++) {
        	DynaActionForm idFeriado = new DynaActionForm();
        	               idFeriado.setItem("feriado",     form.getItem("feriado"));
        	               idFeriado.setItem("idioma",      idiomas[i]);
          	               idFeriado.setItem("nom_feriado", nombres[i]);
          	dao.update(idFeriado);                  
        }
        
        return null;
	}

}
