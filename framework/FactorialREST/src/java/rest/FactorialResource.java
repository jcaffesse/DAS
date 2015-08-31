/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author mpgaviotta
 */
@Path("factorial")
@Stateless
public class FactorialResource {

    @GET
    @Produces("text/html")
    public String factorialQ(@QueryParam("nro") long nro) 
    {
        return "<p>Factorial: " + Long.toString(this.fact(nro)) + "</p>";
    } 

    @GET
    @Path("{nro}")
    @Produces("text/html")
    public String factorialP(@PathParam("nro") long nro) 
    {
        return "<p>Factorial: " + Long.toString(this.fact(nro)) + "</p>";
    } 
    
    private long fact(long x) 
    {
        if(x == 0) 
	{
            return 1;
	}  
        else 
        {
            return x * fact(x - 1);
        }  
    } 

}
