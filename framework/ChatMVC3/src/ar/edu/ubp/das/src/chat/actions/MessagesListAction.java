/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.src.chat.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.beans.LoginTempBean;
import ar.edu.ubp.das.mvc.beans.MensajeBean;
import ar.edu.ubp.das.mvc.beans.SalaBean;
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
public class MessagesListAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            
            Gson gson = new Gson();

            //prepare http get
            SalaBean sala = (SalaBean) request.getSession().getAttribute("sala");
            String login_tmst = (String) request.getSession().getAttribute("login_tmst");
            String authToken = String.valueOf(request.getSession().getAttribute("token"));
            
            URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost("25.136.78.82").setPort(8080).setPath("/mensajes/sala/" + sala.getId());
                builder.setParameter("fecha_desde", "1474659819413");
                //builder.setParameter("fecha_desde", login_tmst);
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
            MensajeBean[] msgList = gson.fromJson(restResp, MensajeBean[].class);
            
            if(msgList.length > 0) {
                request.getSession().setAttribute("ultimo_mensaje", msgList[msgList.length-1].getId_mensaje());
            }
            
            System.out.println("mensajes "+ msgList.toString());
            
            request.setAttribute("mensajes", msgList);
            
            return mapping.getForwardByName("success");

        } catch (IOException | URISyntaxException e) {
            request.setAttribute("message", "Error al intentar ingresar a Sala " + e.getMessage());
            return mapping.getForwardByName("error");
        }
    }
    
}
