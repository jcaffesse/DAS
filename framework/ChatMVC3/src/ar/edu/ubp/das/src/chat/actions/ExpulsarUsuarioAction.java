/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.src.chat.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Jav
 */
public class ExpulsarUsuarioAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            
            //get request data
            String id_usuario = form.getItem("id_usuario");
            String id_sala = form.getItem("id_sala");
            String authToken = String.valueOf(request.getSession().getAttribute("token"));
            
            URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost("25.136.78.82").setPort(8080).setPath("/usuarios-salas/" + id_usuario + "/" + id_sala);

            HttpDelete delete = new HttpDelete();
                delete.setURI(builder.build());
                delete.addHeader("Authorization", "BEARER " + authToken);
                delete.addHeader("accept", "application/json");
            
            CloseableHttpResponse deleteResponse = httpClient.execute(delete);
            
            HttpEntity responseEntity = deleteResponse.getEntity();
            StatusLine responseStatus = deleteResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }

            return mapping.getForwardByName("success");

        } catch (IOException | URISyntaxException e) {
            String id_usuario = form.getItem("id_usuario");
            request.setAttribute("message", "Error al intentar expulsar usuario: " + id_usuario + "; " + e.getMessage());
            return mapping.getForwardByName("error");
        }
    }
    
}
