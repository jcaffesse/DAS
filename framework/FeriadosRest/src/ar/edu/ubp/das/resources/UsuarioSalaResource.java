/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.UsuarioSalaBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
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

/**
 *
 * @author Javier
 */
@Path("/usuarios-salas")
public class UsuarioSalaResource {
    @GET
    @Path("/sala/{id_sala}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getUsuariosSala(
        @PathParam("id_sala") String id_sala
    ) {
        UsuarioSalaBean usuarioSala = new UsuarioSalaBean();
        usuarioSala.setId_sala(Integer.parseInt(id_sala));
        try {
            Dao dao = DaoFactory.getDao("UsuariosSalas");
            List<Bean> list = dao.select(usuarioSala);
            return Response.status(Response.Status.OK).entity(list.toString()).build();
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postUsuarioSala(
        @FormParam("id_usuario") String id_usuario,
        @FormParam("id_sala") String id_sala
    ) {
        UsuarioSalaBean usuarioSala = new UsuarioSalaBean();
            usuarioSala.setId_usuario(Integer.parseInt(id_usuario));
            usuarioSala.setId_sala(Integer.parseInt(id_sala));
            usuarioSala.setEstado(1);
            
        try {
            Dao dao = DaoFactory.getDao("UsuariosSalas");
                dao.insert(usuarioSala);
            return Response.status(Response.Status.OK).build();
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteUsuarioSala(
        @FormParam("id_usuario") String id_usuario,
        @FormParam("id_sala") String id_sala
    ) {
        UsuarioSalaBean usuarioSala = new UsuarioSalaBean();
            usuarioSala.setId_usuario(Integer.parseInt(id_usuario));
            usuarioSala.setId_sala(Integer.parseInt(id_sala));
            
        try {
            Dao dao = DaoFactory.getDao("UsuariosSalas");
                dao.delete(usuarioSala);
            return Response.status(Response.Status.OK).build();
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response putUsuarioSala(
        @FormParam("id_usuario") String id_usuario,
        @FormParam("id_sala") String id_sala,
        @FormParam("estado") String estado
    ) {
        UsuarioSalaBean usuarioSala = new UsuarioSalaBean();
            usuarioSala.setId_usuario(Integer.parseInt(id_usuario));
            usuarioSala.setId_sala(Integer.parseInt(id_sala));
            usuarioSala.setEstado(Integer.parseInt(estado));
            
        try {
            Dao dao = DaoFactory.getDao("UsuariosSalas");
                dao.update(usuarioSala);
            return Response.status(Response.Status.OK).build();
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    
}
