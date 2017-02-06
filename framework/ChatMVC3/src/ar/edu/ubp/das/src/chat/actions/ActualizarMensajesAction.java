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
import ar.edu.ubp.das.mvc.beans.SalaBean;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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
            SalaBean sala = (SalaBean) request.getSession().getAttribute("sala");
            String ultimo_mensaje = String.valueOf(request.getSession().getAttribute("ultimo_mensaje"));
            String authToken = String.valueOf(request.getSession().getAttribute("token"));
            
            if(ultimo_mensaje.equals("null") || ultimo_mensaje.isEmpty()) {
                ultimo_mensaje = "-1";
            }
            
            URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost("25.136.78.82").setPort(8080).setPath("/mensajes/sala/" + sala.getId());
                builder.setParameter("ultimo_mensaje", ultimo_mensaje);

            HttpGet getRequest = new HttpGet();
                getRequest.setURI(builder.build());
                getRequest.addHeader("Authorization", "BEARER " + authToken);
                getRequest.addHeader("accept", "application/json; charset=ISO-8859-1");
            
            CloseableHttpResponse getResponse = httpClient.execute(getRequest);
            HttpEntity responseEntity = getResponse.getEntity();
            StatusLine responseStatus = getResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
            
            //parse message data from response
            Gson gson = new Gson();
            Type listType = new TypeToken<LinkedList<MensajeBean>>(){}.getType();
            List<MensajeBean> mensajes = gson.fromJson(restResp, listType);
            
            if(!mensajes.isEmpty()) {
                MensajeBean ultimo = mensajes.get(mensajes.size() -1);
                request.getSession().removeAttribute("ultimo_mensaje");
                request.getSession().setAttribute("ultimo_mensaje", ultimo.getId_mensaje());
            }
            
            if(!ultimo_mensaje.equals("-1")) { 
                request.setAttribute("mensajes", mensajes);
            }
            
            return mapping.getForwardByName("success");

        } catch (IOException | URISyntaxException | RuntimeException e) {
            String id_sala = (String) request.getSession().getAttribute("id_sala");
            request.setAttribute("message", "Error al intentar actualizar mensajes de Sala " + id_sala + ": " + e.getMessage());
            response.setStatus(400);
            return mapping.getForwardByName("failure");
        }
    }
    
}
