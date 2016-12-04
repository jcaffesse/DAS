/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.src.chat.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.beans.MensajeBean;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Jav
 */
public class ActualizarMensajesAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            
            //prepare http get
            String id_sala = String.valueOf(request.getSession().getAttribute("id_sala"));
            String ultimo_mensaje = String.valueOf(request.getSession().getAttribute("ultimo_mensaje"));
            String authToken = String.valueOf(request.getSession().getAttribute("token"));
            
            System.out.println("sala" + id_sala);
            System.out.println("ult mensaje" + ultimo_mensaje);
            
            URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost("25.136.78.82").setPort(8080).setPath("/mensajes/sala/" + id_sala);
                builder.setParameter("ultimo_mensaje", ultimo_mensaje);

            HttpGet getRequest = new HttpGet();
                getRequest.setURI(builder.build());
                getRequest.addHeader("Authorization", "BEARER " + authToken);
                getRequest.addHeader("accept", "application/json");
            
            CloseableHttpResponse getResponse = httpClient.execute(getRequest);
            HttpEntity responseEntity = getResponse.getEntity();
            StatusLine responseStatus = getResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
            
            //parse message data from response
            Gson gson = new Gson();
            MensajeBean[] msgList = gson.fromJson(restResp, MensajeBean[].class);
            
            request.setAttribute("mensajes", msgList);
            return mapping.getForwardByName("success");

        } catch (IOException | URISyntaxException e) {
            String id_sala = (String) request.getSession().getAttribute("id_sala");
            request.setAttribute("message", "Error al intentar actualizar mensajes de Sala " + id_sala + "; " + e.getMessage());
            return mapping.getForwardByName("error");
        }
    }
    
}
