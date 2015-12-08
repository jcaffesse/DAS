/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.UsuarioBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Javier
 */
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class UsuarioResource {
    @GET
    public Response getUsuarios() {
        try {
            Dao dao = DaoFactory.getDao("Usuarios");
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
    public Response addUsuario(
            @FormParam("nombre") String nombre, 
            @FormParam("email") String email,
            @FormParam("password") String password,
            @FormParam("id_rol") String id_rol
        ) {

        try {
            UsuarioBean bean = new UsuarioBean();
                bean.setNombre(nombre);
                bean.setEmail(email);
                bean.setPassword(password);
                bean.setIdRol(Integer.parseInt(id_rol));

            Dao dao = DaoFactory.getDao("Usuarios");
            dao.insert(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/{id_usuario}")
    public Response delUsuario(
            @PathParam("id_usuario") Integer id
        ) {
        try {
            UsuarioBean bean = new UsuarioBean();
                bean.setId(id);
           
            Dao dao = DaoFactory.getDao("Usuarios");
            dao.delete(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}