/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.filters;

import org.glassfish.jersey.server.ResourceConfig;
 
public class DASApplication extends ResourceConfig
{
    public DASApplication()
    {
        packages("ar.edu.ubp.das.filters");
        //Register Auth Filter here
        register(AuthenticationFilter.class);
    }
}
