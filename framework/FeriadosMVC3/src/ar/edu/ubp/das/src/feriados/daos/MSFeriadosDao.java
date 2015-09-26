package ar.edu.ubp.das.src.feriados.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSFeriadosDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		DynaActionForm form = new DynaActionForm();
                       form.setItem("feriado",      String.valueOf(result.getDate("feriado")));
                       form.setItem("desc_feriado", result.getString("desc_feriado"));
                       form.setItem("tipo_feriado", result.getString("tipo_feriado"));
        return form;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
        this.connect();
        
        this.setProcedure("dbo.set_feriado(?,?,?)");        
		this.setParameter(1, Date.valueOf(form.getItem("feriado")));
        this.setParameter(2, form.getItem("desc_feriado"));
        this.setParameter(3, form.getItem("tipo_feriado"));
        
        this.executeUpdate();
        
        this.disconnect();
    }

	@Override
	public void delete(DynaActionForm form) throws SQLException {
        this.connect();
		
        this.setProcedure("dbo.del_feriado(?)");        
        this.setParameter(1, Date.valueOf(form.getItem("feriado")));
        
        this.executeUpdate();
        
        this.disconnect();
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm bean) throws SQLException {
		List<DynaActionForm> list;
		
		this.connect();
        
        this.setProcedure("dbo.get_feriados");

        list = this.executeQuery();
        
        this.disconnect();
        
        return list;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		return false;
	}

}
