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
 
/**
 * This filter verify the access permissions for a user
 * based on username and passowrd provided in request
 * */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter
{
     
    @Context
    private ResourceInfo resourceInfo;
     
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_BEARER = "BEARER ";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
        .entity("No es posible acceder a este recurso").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
        .entity("Acceso bloqueado para todos los usuarios").build();
    private static final Response NEED_RELOGIN = Response.status(Response.Status.FOUND)
        .entity("El token de acceso ha vencido").build();
      
    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
              
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
              
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_HEADER);
            
            if((authorization == null || authorization.isEmpty())) { 
                requestContext.abortWith(ACCESS_DENIED);
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
                            requestContext.abortWith(ACCESS_DENIED);
                            return;
                        }

                        if(!tokensDao.valid(list.get(0))) {
                            requestContext.abortWith(NEED_RELOGIN);
                            return;
                        }
                    } catch (SQLException e) {
                        requestContext.abortWith(ACCESS_DENIED);
                        return;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }

            }
        }
    }
}
