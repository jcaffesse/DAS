/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.InvitacionBean;
import ar.edu.ubp.das.beans.TokenBean;
import ar.edu.ubp.das.beans.UsuarioBean;
import ar.edu.ubp.das.beans.UsuarioSalaBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
 * @author Javier
 */
@Path("/invitaciones")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class InvitacionResource {
    private static final String AUTHORIZATION_BEARER = "BEARER ";
    
    @GET
    @Path("/{id_usuario}")
    public Response getInvitaciones(
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
                Dao dao = DaoFactory.getDao("Invitaciones");
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
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response addInvitacion(
        @FormParam("id_usuario") String id_usuario, 
        @FormParam("id_destino") String id_destino,
        @FormParam("mensaje") String mensaje_invitacion
    ) {
        List<Bean> list;
        try {
            UsuarioBean usr = new UsuarioBean();
                usr.setId(Integer.parseInt(id_usuario));
                
            Dao userDao = DaoFactory.getDao("Usuarios");
            usr = UsuarioBean.class.cast(userDao.select(usr).get(0));
            
            InvitacionBean invitacionBean = new InvitacionBean();
                invitacionBean.setUsr_orig(usr);
                invitacionBean.setId_destino(Integer.parseInt(id_destino));
                invitacionBean.setMensaje_invitacion(mensaje_invitacion);
                invitacionBean.setFecha_invitacion(new Date());

            Dao dao = DaoFactory.getDao("Invitaciones");
            
            list = dao.select(invitacionBean);
            
            if(list.isEmpty()) {
                try {
                    dao.insert(invitacionBean);
                    
                    Dao salaDao = DaoFactory.getDao("Salas");
                        salaDao.insert(invitacionBean);
                        
                    invitacionBean = InvitacionBean.class.cast(dao.select(invitacionBean).get(0));
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode()+ e.getMessage());
                    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
                }
            } else {
                return Response.status(Response.Status.CONFLICT).entity("El recurso ya existe").build();
            }
            return Response.status(Response.Status.OK).entity(invitacionBean.toString()).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }    
    
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response updateInvitacion(
            @FormParam("id_usuario") String id_usuario, 
            @FormParam("id_destino") String id_destino,
            @FormParam("estado") String estado,
            @HeaderParam("Authorization") String authHeader
        ) {
        String tokenString = authHeader.substring(AUTHORIZATION_BEARER.length());
        TokenBean token = new TokenBean();
            token.setToken(tokenString);

        try {
            UsuarioBean usr = new UsuarioBean();
                usr.setId(Integer.parseInt(id_usuario));
            InvitacionBean bean = new InvitacionBean();
                bean.setUsr_orig(usr);
                bean.setId_destino(Integer.parseInt(id_destino));
                bean.setEstado(estado);
                
            Dao dao = DaoFactory.getDao("Invitaciones");
            dao.update(bean);

            /*Dao tokenDao = DaoFactory.getDao("Tokens");
                token = TokenBean.class.cast(tokenDao.select(token).get(0));            
            
          /*  if (bean.getEstado().compareTo(InvitacionBean.EstadoInvitacion.ACCEPTED) == 0
                    && token.getId_usuario() == bean.getId_destino()) {
                try {
                    bean = InvitacionBean.class.cast(dao.select(bean).get(0));
                    UsuarioSalaBean us = new UsuarioSalaBean();
                        us.set
                    
                    Dao usuarioSalaDao = DaoFactory.getDao("UsuariosSalas");
                        usuarioSalaDao.insert(bean);
                } catch (SQLException s) {
                    return Response.status(Response.Status.BAD_REQUEST).entity(s.getMessage()).build();
                }
            }*/
            
            //estado = 2 DELETE inv
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            System.out.println(e.getErrorCode()+ e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    } 
    
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response delInvitacion(
            @FormParam("id_usuario") String id_usuario,
            @FormParam("id_destino") String id_destino
        ) {
        try {
            UsuarioBean usr = new UsuarioBean();
                usr.setId(Integer.parseInt(id_usuario));
            InvitacionBean bean = new InvitacionBean();
                bean.setUsr_orig(usr);
                bean.setId_destino(Integer.parseInt(id_destino));
           
            Dao dao = DaoFactory.getDao("Invitaciones");
            dao.delete(bean);
          
            return Response.status(Response.Status.OK).build();
        } 
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }    
}
