/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.filters;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.TokenBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Priority;
 
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
 
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter
{
     
    @Context
    private ResourceInfo resourceInfo;
     
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_BEARER = "BEARER ";
    private final Response.ResponseBuilder deniedBuilder = Response.status(Response.Status.UNAUTHORIZED);
    private final Response.ResponseBuilder forbiddenBuilder = Response.status(Response.Status.FORBIDDEN);
    private final Response.ResponseBuilder reloginBuilder = Response.status(Response.Status.FOUND);
      
    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        Method method = resourceInfo.getResourceMethod();
        //Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();        
        final List<String> authorization = headers.get(AUTHORIZATION_HEADER);
        //Access allowed for all
        if( !method.isAnnotationPresent(PermitAll.class))
        {
            this.initBuilders();
            
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(this.forbiddenBuilder.build());
                return;
            }
              
            if((authorization == null || authorization.isEmpty())) {
                requestContext.abortWith(this.deniedBuilder.build());
                return;
            } else {
                try {
                    String authToken = authorization.get(0)
                        .substring(AUTHORIZATION_BEARER.length());
                    
                    TokenBean bean = new TokenBean();
                        bean.setToken(authToken);
                    try {
                        Dao tokensDao = DaoFactory.getDao("Tokens");
                        List<Bean> list = tokensDao.select(bean);

                        if(list.isEmpty()) {
                            requestContext.abortWith(this.deniedBuilder.build());
                            return;
                        }
                        if(!tokensDao.valid(list.get(0))) {
                            requestContext.abortWith(this.reloginBuilder.build());
                        }
                    } catch (SQLException e) {
                        requestContext.abortWith(this.deniedBuilder.build());
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    requestContext.abortWith(this.deniedBuilder.build());
                }

            }
        }
        /* else if (method.isAnnotationPresent(RolesAllowed.class)) {
            System.out.println("hola");
            if((authorization == null || authorization.isEmpty())) {
                requestContext.abortWith(this.deniedBuilder.build());
            } else {
                String authToken = authorization.get(0)
                    .substring(AUTHORIZATION_BEARER.length());
                
                TokenBean token = new TokenBean();
                    token.setToken(authToken);

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
                            System.out.println("rol " + user.getIdRol());
                        }               
                    } else {
                        requestContext.abortWith(this.notFoundBuilder.build());
                    }
                }
                catch (SQLException e) {
                    requestContext.abortWith(this.badRequestBuilder.build());
                }
            }
                        
        }*/
    }
    
    private void initBuilders() {
        this.deniedBuilder.entity("No es posible acceder a este recurso");
        this.forbiddenBuilder.entity("Acceso denegado");
        this.reloginBuilder.entity("El token de autenticacion ha vencido");
    }
}
