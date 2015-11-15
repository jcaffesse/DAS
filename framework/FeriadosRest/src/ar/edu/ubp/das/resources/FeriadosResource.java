package ar.edu.ubp.das.resources;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.FeriadoBean;
import ar.edu.ubp.das.beans.IdiomaFeriadoBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;

@Path("/feriados")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class FeriadosResource {

	@GET
	public Response getFeriados() {
            try {
                    Dao dao = DaoFactory.getDao("Feriados");
                    List<Bean> list = dao.select(null);
                    return Response.status(Response.Status.OK).entity(list).build();
            } 
            catch (SQLException e) {
                    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            }
	}
	
	@POST
	@Path("/{fecha}")
	public Response getIdiomasFeriado(@PathParam("fecha") Date fecha) {
        try {
        	FeriadoBean bean = new FeriadoBean();
        	            bean.setFeriado(fecha);
        	
			Dao dao = DaoFactory.getDao("IdiomasFeriados");
			List<Bean> list = dao.select(bean);
			return Response.status(Response.Status.OK).entity(list).build();
		} 
		catch (SQLException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response actFeriado(@FormParam("feriado") Date feriado, 
						       @FormParam("descFeriado") String descFeriado, 
						       @FormParam("tipoFeriado") String tipoFeriado) {
        try {
        	FeriadoBean bean = new FeriadoBean();
        	            bean.setFeriado(feriado);
        	            bean.setDescFeriado(descFeriado);
        	            bean.setTipoFeriado(tipoFeriado);
        	
			Dao dao = DaoFactory.getDao("Feriados");
	            dao.update(bean);
	            
			return Response.status(Response.Status.OK).build();
		} 
		catch (SQLException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/idiomas")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response actIdiomasFeriado(@FormParam("idioma") String idioma, 
		                              @FormParam("feriado") Date feriado,
		                              @FormParam("nomFeriado") String nomFeriado) {
        try {
        	IdiomaFeriadoBean bean = new IdiomaFeriadoBean();
        	                  bean.setIdioma(idioma);
        	                  bean.setFeriado(feriado);
        	                  bean.setNomFeriado(nomFeriado);
        	
			Dao dao = DaoFactory.getDao("IdiomasFeriados");
	            dao.update(bean);
	            
			return Response.status(Response.Status.OK).build();
		} 
		catch (SQLException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{fecha}")
	public Response delFeriado(@PathParam("fecha") Date fecha) {
        try {
        	FeriadoBean bean = new FeriadoBean();
                        bean.setFeriado(fecha);

            Dao dao = DaoFactory.getDao("Feriados");
	            dao.delete(bean);
			return Response.status(Response.Status.OK).build();
		} 
		catch (SQLException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
}
