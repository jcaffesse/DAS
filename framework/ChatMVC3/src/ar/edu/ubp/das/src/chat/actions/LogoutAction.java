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
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


/**
 *
 * @author Jav
 */
public class LogoutAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String authToken = String.valueOf(request.getSession().getAttribute("token"));
            
            HttpPost httpPost = new HttpPost("http://25.136.78.82:8080/logout/");
            httpPost.addHeader("Authorization", "BEARER " + authToken);
            httpPost.addHeader("accept", "application/json");
          
            CloseableHttpResponse postResponse = httpClient.execute(httpPost);

            HttpEntity responseEntity = postResponse.getEntity();
            StatusLine responseStatus = postResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);

            if(responseStatus.getStatusCode() != 200) {
                throw new RuntimeException(restResp);
            }
          
            request.getSession().removeAttribute("token");
            
            return mapping.getForwardByName("success");

        } catch (IOException | RuntimeException e) {
            request.setAttribute("message", "Error al realizar logout: " + e.getMessage());
            response.setStatus(400);
            return mapping.getForwardByName("failure");
        }
    }
}
