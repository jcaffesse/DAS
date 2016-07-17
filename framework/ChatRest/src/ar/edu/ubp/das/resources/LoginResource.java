/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.TokenBean;
import ar.edu.ubp.das.beans.UsuarioBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
import java.security.SecureRandom;
import java.math.BigInteger;
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
    private static final String AUTH_RESPONSE_HEADER = "Auth-Token";
    
    @PermitAll
    @POST
    public Response login(
        @FormParam("nombre_usuario") String nombre, 
        @FormParam("password") String password
    ) {
        UsuarioBean usuario = new UsuarioBean();
            usuario.setNombre(nombre);
            usuario.setPassword(password);
        
        try {
            Dao usuariosDao = DaoFactory.getDao("Usuarios");
            Dao tokensDao = DaoFactory.getDao("Tokens");
            
            if(usuariosDao.valid(usuario)) {
                List<Bean> list = usuariosDao.select(usuario);
                //generar token para el usuario logeado
                usuario = UsuarioBean.class.cast(list.get(0));
                TokenBean token = generateToken(usuario.getId());
                tokensDao.insert(token);
                return Response.status(Response.Status.OK).entity(usuario)
                    .header(AUTH_RESPONSE_HEADER, token.getToken()).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("No esta autorizado a acceder a este recurso").build();
            }
        }
        catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    private TokenBean generateToken(int username) {
        SecureRandom random = new SecureRandom();

        String tokenString = new BigInteger(130, random).toString(32);
        
        TokenBean token = new TokenBean();
            token.setId_usuario(username);
            token.setToken(tokenString);
        
        return token;
    }
}
