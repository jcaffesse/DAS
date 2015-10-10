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
@Path("/chat")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class ChatResource {
    	@GET
	public Response getSalas() {
            String json = "[\"Sala 1\",\"Sala 2\",\"Sala 3\",\"Sala 4\"]";
            /*SalaBean bean = new SalaBean();
            bean.setSala("Sala 1");
            bean.setSala("Sala 2");
            bean.setSala("Sala 3");
            bean.setSala("Sala 4");
            bean.setSala("Sala 5");*/

            return Response.status(Response.Status.OK).entity(json).build();
	}

    
}


