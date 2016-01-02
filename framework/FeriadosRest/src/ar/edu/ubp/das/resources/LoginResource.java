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
import javax.annotation.security.PermitAll;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Javier
 */
@Path("/login")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class LoginResource {
    @PermitAll
    @POST
    public Response login(
            @FormParam("usuario") String nombre, 
            @FormParam("password") String password
    ) {
        UsuarioBean bean = new UsuarioBean();
            bean.setNombre(nombre);
            bean.setPassword(password);
        try {
            Dao dao = DaoFactory.getDao("Usuarios");
            
            if(dao.valid(bean)) {
                //generar y guardar token
                List<Bean> list = dao.select(bean);
                return Response.status(Response.Status.OK).entity(list.get(0)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("No es posible acceder a este recurso").build();
            }
            
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
