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
import ar.edu.ubp.das.mvc.beans.SalaBean;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class SalasJoinAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            
            //get user data from session storage
            Gson gson = new Gson();
            Type usuarioType = new TypeToken<LoginTempBean>(){}.getType();
            String sessUser = String.valueOf(request.getSession().getAttribute("user"));
            LoginTempBean user = gson.fromJson(sessUser, usuarioType);
            
            //prepare http post
            HttpPost httpPost = new HttpPost("http://25.136.78.82:8080/usuarios-salas/");
            List <NameValuePair> params = new ArrayList <>();
            params.add(new BasicNameValuePair("id_usuario", user.getId()));
            params.add(new BasicNameValuePair("id_sala", form.getItem("id_sala")));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            httpPost.addHeader("Authorization", "BEARER " + request.getSession().getAttribute("token"));
            httpPost.addHeader("accept", "application/json");
            
            CloseableHttpResponse postResponse = httpClient.execute(httpPost);
            
            HttpEntity responseEntity = postResponse.getEntity();
            StatusLine responseStatus = postResponse.getStatusLine();
            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
            
            //get user data from session storage
            String salas = String.valueOf(request.getSession().getAttribute("salas"));
            List<SalaBean> salaList = gson.fromJson(salas, new TypeToken<List<SalaBean>>(){}.getType());
            SalaBean actual = salaList.stream()
                .filter(s -> s.getId() == Integer.parseInt(form.getItem("id_sala")))
                .collect(Collectors.toList()).get(0);
            
            request.getSession().setAttribute("sala", actual);
            
            return mapping.getForwardByName("success");

        } catch (IOException e) {
           request.setAttribute("message", "Error al intentar ingresar a Sala " + e.getMessage());
            return mapping.getForwardByName("error");
        }
    }
    
}
