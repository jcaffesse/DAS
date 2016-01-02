/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.filters;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
 
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
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
                return;
            }
            
            System.out.println("validar token");
            
            //validar token
            //Get token
            
            //final String token = authorization.get(0);
              
            /*//Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
              
            //Verifying Username and password
            System.out.println(username);
            System.out.println(password);*/
              
            //Verify user access
            /*if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                  
                //Is user valid?
                if( ! isUserAllowed(token, rolesSet))
                {
                    requestContext.abortWith(ACCESS_DENIED);
                }
            }*/
        }
    }
    private boolean isUserAllowed(final String token, final Set<String> rolesSet)
    {
        boolean isAllowed = false;
          
        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);
         
        if(token.equals("dummytoken"))
        {
            String userRole = "ADMIN";
             
            //Step 2. Verify user role
            if(rolesSet.contains(userRole))
            {
                isAllowed = true;
            }
        }
        return isAllowed;
    }
}
