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
import java.awt.Color;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 *
 * @author Javier
 */
@Path("/salas")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class SalaResource {
    @GET
    public Response getSalas() {
        try {
            Dao dao = DaoFactory.getDao("Salas");
            String str = String.valueOf(new Color((int)(Math.random() * 0x1000000)).getRGB());
            List<Bean> list = dao.select(null);
            
            return Response.status(Response.Status.OK).entity(list.toString()).build();
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/{id_sala}")
    public Response getSala(
        @PathParam("id_sala") String id_sala
    ) {
        try {
            SalaBean sala = new SalaBean();
                sala.setId(Integer.parseInt(id_sala));
            
            try {
                Dao dao = DaoFactory.getDao("Salas");
                List<Bean> list = dao.select(sala);
                if (list.isEmpty()) {
                    return Response.status(Response.Status.NOT_FOUND).build();
                } else {
                    return Response.status(Response.Status.OK).entity(list.get(0)).build();
                }
            }
            catch (SQLException e) {
                System.out.println(e.getErrorCode()+ e.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            }                
            
        } catch (NumberFormatException n ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(n.getMessage()).build();
        }

    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response addSala(
            @FormParam("nombre") String nombre, 
            @FormParam("desc") String desc,
            @FormParam("tipo") String tipo
        ) {

        try {
            SalaBean bean = new SalaBean();
                bean.setNombre(nombre);
                bean.setDesc(desc);
                bean.setTipo(tipo);
                bean.createColor();

            Dao dao = DaoFactory.getDao("Salas");
            dao.insert(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @PUT
    @Path("/{id_sala}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response updateSala(
            @PathParam("id_sala") String id_sala,
            @FormParam("nombre") String nombre, 
            @FormParam("desc") String desc,
            @FormParam("tipo") String tipo
        ) {

        try {
            SalaBean bean = new SalaBean();
                bean.setId(Integer.parseInt(id_sala));
                bean.setNombre(nombre);
                bean.setDesc(desc);
                bean.setTipo(tipo);

            Dao dao = DaoFactory.getDao("Salas");
            dao.update(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/{id_sala}")
    public Response delSala(
            @PathParam("id_sala") Integer id
        ) {
        try {
            SalaBean bean = new SalaBean();
                bean.setId(id);
           
            Dao dao = DaoFactory.getDao("Salas");
            dao.delete(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}


