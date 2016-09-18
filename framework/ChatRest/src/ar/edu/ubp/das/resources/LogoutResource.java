/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.TokenBean;
import ar.edu.ubp.das.beans.UsuarioBean;
import ar.edu.ubp.das.beans.UsuarioSalaBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Javier
 */
@Path("/logout")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class LogoutResource {
    private static final String AUTHORIZATION_BEARER = "BEARER ";
    
    @PermitAll
    @POST
    public Response logout(
        @HeaderParam("Authorization") String authHeader
    ) {
        String tokenString = authHeader.substring(AUTHORIZATION_BEARER.length());
        TokenBean token = new TokenBean();
            token.setToken(tokenString);
        
        try {
            Dao tokensDao = DaoFactory.getDao("Tokens");
            Dao usDao = DaoFactory.getDao("UsuariosSalas");
            List<Bean> list = tokensDao.select(token);
            if (!list.isEmpty()) {
                UsuarioSalaBean usBean = new UsuarioSalaBean();
                    usBean.setId_usuario(TokenBean.class.cast(list.get(0)).getId_usuario());
                
                tokensDao.delete(list.get(0));
                usDao.delete(usBean);

                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
