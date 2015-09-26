package ar.edu.ubp.das.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.FeriadoBean;
import ar.edu.ubp.das.beans.IdiomaFeriadoBean;

public class MSSQLIdiomasFeriadosDao extends MSSQLDao {

	@Override
	public Bean make(ResultSet result) throws SQLException {
    	IdiomaFeriadoBean idFeriado = new IdiomaFeriadoBean();
                          idFeriado.setIdioma(result.getString("idioma"));
                          idFeriado.setDescIdioma(result.getString("desc_idioma"));
                          idFeriado.setFeriado(result.getDate("feriado"));
                          idFeriado.setNomFeriado(result.getString("nom_feriado"));     
        return idFeriado;
	}

	@Override
	public void insert(Bean bean) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Bean bean) throws SQLException {
        IdiomaFeriadoBean idFeriado = IdiomaFeriadoBean.class.cast(bean);

		this.connect();
        
        this.setProcedure("dbo.set_idioma_feriado(?,?,?)");
		this.setParameter(1, new Date(idFeriado.getFeriado().getTime()));
		this.setParameter(2, idFeriado.getIdioma());
		this.setParameter(3, idFeriado.getNomFeriado());		
		
		this.executeUpdate();
        
		this.disconnect();
	}

	@Override
	public void delete(Bean bean) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bean> select(Bean bean) throws SQLException {
		List<Bean> list;
		
		this.connect();

		this.setProcedure("dbo.get_idiomas_feriados(?)");
        this.setParameter(1, new Date(FeriadoBean.class.cast(bean).getFeriado().getTime()));
        
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
