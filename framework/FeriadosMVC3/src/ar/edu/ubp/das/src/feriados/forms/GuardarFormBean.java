package ar.edu.ubp.das.src.feriados.forms;

import javax.servlet.http.HttpServletRequest;

import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class GuardarFormBean extends DynaActionForm {

	@Override
	public void validate(ActionMapping mapping, HttpServletRequest request)	throws RuntimeException {
		if(this.getItem("feriado").isEmpty()) {
			throw new RuntimeException("fechaObligatoria");
		}
		
		if(this.getItem("desc_feriado").isEmpty()) {
			throw new RuntimeException("descripcionObligatoria");
		}

		if(this.getItem("tipo_feriado").isEmpty()) {
			throw new RuntimeException("tipoObligatorio");
		}
	}
	
}
