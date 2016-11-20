package ar.edu.ubp.das.src.feriados.actions;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;

public class GuardarAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
		try {
	        if(form.getItem("feriado_str") == null) {
		        String patron = String.valueOf(request.getSession().getAttribute("formato"));
		        Locale idioma = new Locale(String.valueOf(request.getSession().getAttribute("idioma")));

		        Date             fecha   = new SimpleDateFormat(patron, idioma).parse(form.getItem("feriado"));
		        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		        
		        form.setItem("feriado", formato.format(fecha));
		        request.setAttribute("feriado", formato.format(fecha));
	        }
	        
	        Dao dao = DaoFactory.getDao("Feriados", "feriados");
			    dao.update(form);
			return mapping.getForwardByName("success");
		} 
		catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
	}

}
