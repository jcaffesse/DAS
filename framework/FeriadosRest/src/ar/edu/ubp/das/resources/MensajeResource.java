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
import java.util.Date;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pablo
 */
@Path("/mensajes")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class MensajeResource {
    
    @GET
    @Path("/{id_mensaje}")
    public Response getMensaje(
        @PathParam("id_mensaje") String id_mensaje
    ) {
        try {
            MensajeBean bean = new MensajeBean();
                bean.setId_mensaje(Integer.parseInt(id_mensaje));
            try {
                Dao dao = DaoFactory.getDao("Mensajes");
                List<Bean> list = dao.select(bean);
                if (list.isEmpty()) {
                    return Response.status(Response.Status.NOT_FOUND).build();
                } else {
                    return Response.status(Response.Status.OK).entity(list.get(0)).build();
                }
            }
            catch (SQLException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            }
        } catch (NumberFormatException n) {
            return Response.status(Response.Status.BAD_REQUEST).entity(n.getMessage()).build();
        }
    }
    
    @GET
    @Path("/sala/{id_sala}")
    public Response getMensajesSala(
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
            SalaBean bean = new SalaBean();
                bean.setId(Integer.parseInt(id_sala));
            if (ua != null) {
                bean.setUltimaAct(ua);
            }
            try {
                Dao dao = DaoFactory.getDao("Mensajes");
                List<Bean> list = dao.select(bean);
                if (list.isEmpty()) {
                    return Response.status(Response.Status.OK).entity("[]").build();
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
    
    @GET
    @Path("/usuario/{id_usuario}")
    public Response getMensajesUser(
        @PathParam("id_usuario") String id_usuario,
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
            UsuarioBean bean = new UsuarioBean();
                bean.setId(Integer.parseInt(id_usuario));
            if (ua != null) {
                bean.setUltimaAct(ua);
            }
            
            try {
                Dao dao = DaoFactory.getDao("Mensajes");
                List<Bean> list = dao.select(bean);
                if (list.isEmpty()) {
                    return Response.status(Response.Status.OK).entity("[]").build();
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
    
    @PUT
    @Path("/{id_mensaje}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateMensaje(
        @PathParam("id_mensaje") String id_mensaje,
        @FormParam("mensaje") String mensaje
    ) {
        try {
            MensajeBean bean = new MensajeBean();
                bean.setId_mensaje(Integer.parseInt(id_mensaje));
                bean.setMensaje(mensaje);

            Dao dao = DaoFactory.getDao("Mensajes");
            dao.update(bean);
            
            return Response.status(Response.Status.OK).build();
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @DELETE
    @Path("/{id_mensaje}")
    public Response delMensaje(
        @PathParam("id_mensaje") String id_mensaje   
    ) 
    {
        System.out.println("bang bang");
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
