package ar.edu.ubp.das.src.feriados.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSIdiomasFeriadoDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		DynaActionForm form = new DynaActionForm();
                       form.setItem("idioma",      result.getString("idioma"));
                       form.setItem("desc_idioma", result.getString("desc_idioma"));
                       form.setItem("feriado",     String.valueOf(result.getDate("feriado")));
                       form.setItem("nom_feriado", result.getString("nom_feriado") == null ? "" : result.getString("nom_feriado"));     
        return form;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		this.connect();
        
        this.setProcedure("dbo.set_idioma_feriado(?,?,?)");
		this.setParameter(1, Date.valueOf(form.getItem("feriado")));
		this.setParameter(2, form.getItem("idioma"));
		this.setParameter(3, form.getItem("nom_feriado"));		
		
		this.executeUpdate();
        
		this.disconnect();
	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		List<DynaActionForm> list;
		
		this.connect();

		this.setProcedure("dbo.get_idiomas_feriados(?)");
        this.setParameter(1, Date.valueOf(form.getItem("feriado")));
        
        list = this.executeQuery();
        
        this.disconnect();
        
        return list;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		return false;
	}

}
