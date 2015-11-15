package ar.edu.ubp.das.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.FeriadoBean;

public class MSSQLFeriadosDao extends MSSQLDao {

	@Override
	public Bean make(ResultSet result) throws SQLException {
    	FeriadoBean feriado = new FeriadoBean();
                    feriado.setFeriado(result.getDate("feriado"));
                    feriado.setDescFeriado(result.getString("desc_feriado"));
                    feriado.setTipoFeriado(result.getString("tipo_feriado"));
        return feriado;
	}

	@Override
	public void insert(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Bean bean) throws SQLException {
        FeriadoBean feriado = FeriadoBean.class.cast(bean);
		
        this.connect();
		
        this.setProcedure("dbo.set_feriado(?,?,?)");        
        this.setParameter(1, new Date(feriado.getFeriado().getTime()));
        this.setParameter(2, feriado.getDescFeriado());
        this.setParameter(3, feriado.getTipoFeriado());
        
        this.executeUpdate();
        
        this.disconnect();
	}

	@Override
	public void delete(Bean bean) throws SQLException {
        this.connect();
		
        this.setProcedure("dbo.del_feriado(?)");        
        this.setParameter(1, new Date(FeriadoBean.class.cast(bean).getFeriado().getTime()));
        
        this.executeUpdate();
        
        this.disconnect();
	}

	@Override
	public List<Bean> select(Bean bean) throws SQLException {
		List<Bean> list;
		
		this.connect();
        
        this.setProcedure("dbo.get_salas");

        list = this.executeQuery();
        
        this.disconnect();
        
        return list;
	}

	@Override
	public boolean valid(Bean bean) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
