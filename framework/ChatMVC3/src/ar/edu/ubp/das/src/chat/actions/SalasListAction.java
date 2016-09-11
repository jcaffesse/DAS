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
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Jav
 */
public class SalasListAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String url = "http://localhost:8080/salas/";
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("Authorization", "BEARER 3bmdisqkr9i2ee8h49btbp6f1");
            getRequest.addHeader("accept", "application/json");
            
            HttpResponse getResponse = httpClient.execute(getRequest);
            HttpEntity responseEntity = getResponse.getEntity();
            StatusLine responseStatus = getResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
            Gson gson = new Gson();
            Type listType = new TypeToken<LinkedList<SalaBean>>(){}.getType();
            List<SalaBean> salas = gson.fromJson(restResp, listType);
            request.setAttribute("salas", salas);

        } catch (IOException | RuntimeException e) {
            System.out.println("Error al intentar listar Salas " + e.getMessage());
        }
            return mapping.getForwardByName("success");
    }
    
}

