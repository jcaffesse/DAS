/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.InvitacionBean;
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
@Path("/invitaciones")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class InvitacionResource {
    
    @GET
    @Path("/{id_usuario}")
    public Response getInvitaciones(
        @PathParam("id_usuario") String id_usuario
    ) {
        try {
            UsuarioBean bean = new UsuarioBean();
                bean.setId(Integer.parseInt(id_usuario));
            try {
                Dao dao = DaoFactory.getDao("Invitaciones");
                List<Bean> list = dao.select(bean);
                if (list.isEmpty()) {
                    return Response.status(Response.Status.NOT_FOUND).build();
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
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response addInvitacion(
            @FormParam("id_usuario") String id_usuario, 
            @FormParam("id_destino") String id_destino,
            @FormParam("mensaje_invitacion") String mensaje_invitacion
        ) {

        try {
            InvitacionBean bean = new InvitacionBean();
                bean.setId_usuario(Integer.parseInt(id_usuario));
                bean.setId_destino(Integer.parseInt(id_destino));
                bean.setMensaje_invitacion(mensaje_invitacion);

            Dao dao = DaoFactory.getDao("Invitaciones");
            dao.insert(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }    
    
    @PUT
    @Path("/{id_usuario}/{id_destino}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response updateInvitacion(
            @PathParam("id_usuario") String id_usuario, 
            @PathParam("id_destino") String id_destino,
            @FormParam("mensaje_invitacion") String mensaje_invitacion
        ) {

        try {
            InvitacionBean bean = new InvitacionBean();
                bean.setId_usuario(Integer.parseInt(id_usuario));
                bean.setId_destino(Integer.parseInt(id_destino));
                bean.setMensaje_invitacion(mensaje_invitacion);

            Dao dao = DaoFactory.getDao("Invitaciones");
            dao.update(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    } 
    
    @DELETE
    @Path("/{id_usuario}/{id_destino}")
    public Response delSala(
            @PathParam("id_usuario") String id_usuario,
            @PathParam("id_destino") String id_destino
        ) {
        try {
            InvitacionBean bean = new InvitacionBean();
                bean.setId_usuario(Integer.parseInt(id_usuario));
                bean.setId_destino(Integer.parseInt(id_destino));
           
            Dao dao = DaoFactory.getDao("Invitaciones");
            dao.delete(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }    
}
