/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.resources;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.beans.Bean;
import ar.edu.ubp.das.beans.FeriadoBean;
import ar.edu.ubp.das.beans.IdiomaFeriadoBean;
import ar.edu.ubp.das.beans.SalaBean;
import ar.edu.ubp.das.daos.Dao;
import ar.edu.ubp.das.daos.DaoFactory;
import ar.edu.ubp.das.sources.SalasList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rocio
 */
@Path("/salas")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class SalaResource {
    private SalasList list;
    
    @GET
    public Response getSalas() {
        list = new SalasList();
        for(int i=0; i<6;i++) {
            SalaBean bean = new SalaBean();
            bean.setNombre("Sala " + i);
            this.list.addSala(bean);
        }
        
        return Response.status(Response.Status.OK).entity(list.toString()).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response addSala(@FormParam("nombre") String nombre) {
        System.out.println(nombre);
        SalaBean bean = new SalaBean();
        list = new SalasList();
        bean.setNombre(nombre);
        this.list.addSala(bean);
        
        return Response.status(Response.Status.OK).entity(list.toString()).build();
    }
}


