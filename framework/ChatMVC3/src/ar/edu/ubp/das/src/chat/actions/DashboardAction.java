/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.src.chat.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.beans.SalaBean;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Jav
 */
public class DashboardAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String url = "http://25.136.78.82:8080/salas";
            HttpGet getRequest = new HttpGet(url);
            String authToken = String.valueOf(request.getSession().getAttribute("token"));

            getRequest.addHeader("Authorization", "BEARER " + authToken);
            getRequest.addHeader("accept", "application/json; charset=ISO-8859-1");
            
            CloseableHttpResponse getResponse = httpClient.execute(getRequest);
            HttpEntity responseEntity = getResponse.getEntity();
            StatusLine responseStatus = getResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }

            Gson gson = new Gson();
            Type listType = new TypeToken<LinkedList<SalaBean>>(){}.getType();
            List<SalaBean> salas = gson.fromJson(restResp, listType);
            request.getSession().setAttribute("salas", salas);
            return mapping.getForwardByName("success");

        } catch (IOException | RuntimeException e) {
           request.setAttribute("message", "Error al intentar listar Salas " + e.getMessage());
           response.setStatus(400);
           return mapping.getForwardByName("failure");
        }
    }
    
}
