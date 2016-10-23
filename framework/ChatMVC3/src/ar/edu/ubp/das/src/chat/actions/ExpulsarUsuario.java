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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Jav
 */
public class ExpulsarUsuario implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
       /* try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpDelete httpDelete = new HttpDelete("http://25.136.78.82:8080/usuarios-salas/");
            List <NameValuePair> params = new ArrayList <>();
            params.add(new BasicNameValuePair("id_usuario", form.getItem("id_usuario")));
            params.add(new BasicNameValuePair("id_sala", form.getItem("id_sala")));
            httpDelete.addHeader("Authorization", "BEARER " + request.getAttribute("token"));
            httpDelete.addHeader("accept", "application/json");
            
            CloseableHttpResponse postResponse = httpClient.execute(httpPost);
            
            HttpEntity responseEntity = postResponse.getEntity();
            StatusLine responseStatus = postResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
            
            request.setAttribute("id_sala", form.getItem("id_sala"));
            request.setAttribute("token", form.getItem("token"));
            return mapping.getForwardByName("userslist");

        } catch (IOException | RuntimeException e) {
           request.setAttribute("message", "Error al intentar ingresar a Sala " + e.getMessage());
            return mapping.getForwardByName("error");
        }*/
       return mapping.getForwardByName("error");
    }
    
}
