/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.SalaBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;

/**
 *
 * @author Rocio
 */
@Path("/salas")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class SalaResource {
    //private SalasList list;
    
    @GET
    public Response getSalas() {
        try {
            Dao dao = DaoFactory.getDao("Salas");
            List<Bean> list = dao.select(null);
            System.out.println("LIST"+list.toString());
            return Response.status(Response.Status.OK).entity(list.toString()).build();
            
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage() + e.getCause());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response addSala(
            @FormParam("id") String id,
            @FormParam("nombre") String nombre, 
            @FormParam("desc") String desc,
            @FormParam("parts") String parts
        ) {

        try {
            SalaBean bean = new SalaBean();
                bean.setId(Integer.parseInt(id));
                bean.setNombre(nombre);
                bean.setDesc(desc);
                bean.setParts(Integer.parseInt(parts));

            Dao dao = DaoFactory.getDao("Salas");
            dao.update(bean);

            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage() + e.getCause());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}


