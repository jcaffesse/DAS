/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.MensajeBean;
import ar.edu.ubp.das.beans.SalaBean;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pablo
 */
@Path("/mensaje")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class MensajeResource {
    
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMensajesSala(
        @FormParam("id_sala") String id_sala
    ) {
        try {
            SalaBean bean = new SalaBean();
                bean.setId(Integer.parseInt(id_sala));
                
            Dao dao = DaoFactory.getDao("Mensajes");
            List<Bean> list = dao.select(bean);
            return Response.status(Response.Status.OK).entity(list.toString()).build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMensajesUser(
        @FormParam("id_usuario") String id_usuario
    ) {
        try {
            UsuarioBean bean = new UsuarioBean();
                bean.setId(Integer.parseInt(id_usuario));
                
            Dao dao = DaoFactory.getDao("Mensajes");
            List<Bean> list = dao.select(bean);
            return Response.status(Response.Status.OK).entity(list.toString()).build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postMensaje(
        @FormParam("id_usuario") String id_usuario,
        @FormParam("id_sala") String id_sala,
        @FormParam("mensaje") String mensaje
    ) {
        try {
            MensajeBean bean = new MensajeBean();
                bean.setId_sala(Integer.parseInt(id_sala));
                bean.setId_usuario(Integer.parseInt(id_usuario));
                bean.setMensaje(mensaje);

            Dao dao = DaoFactory.getDao("Mensajes");
            dao.insert(bean);
          
            return Response.status(Response.Status.OK).build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response delMensaje(
        @FormParam("id_mensaje") String id_mensaje   
    ) 
    {
        try {
            MensajeBean bean = new MensajeBean();
                bean.setId_mensaje(Integer.parseInt(id_mensaje));
           
            Dao dao = DaoFactory.getDao("Mensajes");
            dao.delete(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }        
    }
    
}
