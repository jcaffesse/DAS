/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.ActualizacionBean;
import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jav
 */
@Path("/actualizaciones")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class ActualizacionResource {
    @GET
    @Path("/sala/{id_sala}")
    public Response getActualizacionesPortal(
        @PathParam("id_sala") String id_sala,
        @QueryParam("ultima_act") String ultima_act
    ) {
        Date ua = null;
        if (ultima_act != null) {
            try {
                ua = new Date(Long.parseLong(ultima_act));
            } catch (NumberFormatException l) {
                return Response.status(Response.Status.BAD_REQUEST).entity(l.getMessage()).build();
            }
        }        
        try {
            ActualizacionBean bean = new ActualizacionBean();
                bean.setId_sala(Integer.parseInt(id_sala));
                bean.setUltima_act(ua);
            try {
                Dao dao = DaoFactory.getDao("Actualizaciones");
                List<Bean> list = dao.select(bean);
                if (list.isEmpty()) {
                    return Response.status(Response.Status.OK).entity("").build();
                } else {
                    return Response.status(Response.Status.OK).entity(list.toString()).build();
                }
            }
            catch (SQLException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            }
        } catch (NumberFormatException n) {
            return Response.status(Response.Status.BAD_REQUEST).entity(n.getMessage()).build();
        }
    }
}
