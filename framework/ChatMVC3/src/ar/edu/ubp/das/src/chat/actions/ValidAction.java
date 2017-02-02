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
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 *
 * @author Jav
 */
public class ValidAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost("http://25.136.78.82:8080/login/");
            List <NameValuePair> params = new ArrayList <>();
            params.add(new BasicNameValuePair("nombre_usuario", form.getItem("user")));
            params.add(new BasicNameValuePair("password", form.getItem("pw")));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse postResponse = httpClient.execute(httpPost);

            HttpEntity responseEntity = postResponse.getEntity();
            StatusLine responseStatus = postResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);

            if(responseStatus.getStatusCode() != 200) {
                throw new RuntimeException(restResp);
            }
            
            Header authHeader = postResponse.getFirstHeader("Auth-Token");
            String headerValue = authHeader != null ? authHeader.getValue() : "";
            
            HttpPost adminPost = new HttpPost("http://25.136.78.82:8080/login/admin");
                adminPost.addHeader("Authorization", "BEARER " + headerValue);
                adminPost.addHeader("accept", "application/json");
            
            postResponse = httpClient.execute(adminPost);
            responseEntity = postResponse.getEntity();
            responseStatus = postResponse.getStatusLine();
            String adminResp = EntityUtils.toString(responseEntity);
            
            if(responseStatus.getStatusCode() != 200) {
                throw new RuntimeException(adminResp);
            }

            request.getSession().setAttribute("user", restResp);
            request.getSession().setAttribute("token", headerValue);
            request.getSession().setAttribute("login_tmst", String.valueOf(System.currentTimeMillis()));

            return mapping.getForwardByName("success");

        } catch (IOException e) {
            request.setAttribute("message", "Error al realizar login: " + e.getMessage());
            return mapping.getForwardByName("failure");
        }
    }
}
