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
import ar.edu.ubp.das.mvc.beans.UsuarioBean;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
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
public class LoginAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        if(form.getItem("user") != null && form.getItem("pw") != null) {
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
                HttpPost httpPost = new HttpPost("http://25.136.78.82:8080/login/");
                List <NameValuePair> params = new ArrayList <>();
                params.add(new BasicNameValuePair("nombre_usuario", form.getItem("user")));
                params.add(new BasicNameValuePair("password", form.getItem("pw")));
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                CloseableHttpResponse postResponse = httpClient.execute(httpPost);

                HttpEntity responseEntity = postResponse.getEntity();
                StatusLine responseStatus = postResponse.getStatusLine();
                Header[] responseHeaders = postResponse.getAllHeaders();
                String authHeader = null;
                
                for (Header responseHeader : responseHeaders) {
                    if(responseHeader.getName().equals("Auth-Token")) {
                        authHeader = responseHeader.getValue();
                    }
                }
                String restResp = EntityUtils.toString(responseEntity);
                
                if(responseStatus.getStatusCode() != 200) {
                    throw new RuntimeException(restResp);
                }
                
                Gson gson = new Gson();
                Type usuarioType = new TypeToken<LoginTempBean>(){}.getType();
                LoginTempBean user = gson.fromJson(restResp, usuarioType);
                
                request.setAttribute("id_usuario", user.getId());
                request.setAttribute("token", authHeader);
                return mapping.getForwardByName("list");

            } catch (IOException | RuntimeException e) {
               request.setAttribute("message", "Error al realizar login: " + e.getMessage());
               return mapping.getForwardByName("error");
            }
        }
        
        return mapping.getForwardByName("success");
    }
    
}
