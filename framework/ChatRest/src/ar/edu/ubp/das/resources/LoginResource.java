/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.RolBean;
import ar.edu.ubp.das.beans.TokenBean;
import ar.edu.ubp.das.beans.UsuarioBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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

@Path("/login")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class LoginResource {
    private static final String AUTH_RESPONSE_HEADER = "Auth-Token";
    private static final String AUTHORIZATION_BEARER = "BEARER ";
    private static final RolBean ADMIN_ROLE = new RolBean(100, "Administrador");
    
    @POST
    @PermitAll
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
    
    @POST
    @Path("/admin")
    public Response portalAdmin(
        @HeaderParam("Authorization") String authHeader
    ) {
        String tokenString = authHeader.substring(AUTHORIZATION_BEARER.length());
        TokenBean token = new TokenBean();
            token.setToken(tokenString);
        
        try {
            Dao tokensDao = DaoFactory.getDao("Tokens");
            
            List<Bean> list = tokensDao.select(token);
            if (!list.isEmpty()) {
                token = TokenBean.class.cast(list.get(0));
                UsuarioBean user = new UsuarioBean();
                    user.setId(token.getId_usuario());
                
                Dao usersDao = DaoFactory.getDao("Usuarios");
                List<Bean> userList = usersDao.select(user);
                
                if (!userList.isEmpty()) {
                    user = (UsuarioBean) userList.get(0);
                    if(user.getIdRol().equals(ADMIN_ROLE.getId_rol())) {
                        return Response.status(Response.Status.OK).build();
                    }
                }               
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
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
