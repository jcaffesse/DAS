/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.src.chat.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.beans.ActualizacionBean;
import ar.edu.ubp.das.mvc.beans.MensajeBean;
import ar.edu.ubp.das.mvc.beans.SalaBean;
import ar.edu.ubp.das.mvc.beans.UsuarioBean;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
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
public class ActualizarUsuariosAction implements Action {

    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            //prepare http get
            SalaBean sala = (SalaBean) request.getSession().getAttribute("sala");
            String ultima_actualizacion = String.valueOf(request.getSession().getAttribute("ultima_actualizacion"));
            String authToken = String.valueOf(request.getSession().getAttribute("token"));
            
            if(ultima_actualizacion.equals("null") || ultima_actualizacion.isEmpty()) {
                ultima_actualizacion = String.valueOf(System.currentTimeMillis());
            }
            
            URIBuilder builder = new URIBuilder();
                builder.setScheme("http").setHost("25.136.78.82").setPort(8080).setPath("/actualizaciones/sala/" + sala.getId());
                builder.setParameter("ultima_act", ultima_actualizacion);

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
            
            if(restResp.equals("null") || restResp.isEmpty()) {
                return mapping.getForwardByName("success");
            }
            //parse actualizacion data from response
            Gson gson = new Gson();
            Type listType = new TypeToken<LinkedList<ActualizacionBean>>(){}.getType();
            List<ActualizacionBean> actualizaciones = gson.fromJson(restResp, listType);
            List<UsuarioBean> usuarios = actualizaciones.stream()
                .filter(a -> a.getNombre_tipo().equals("UsuarioSala"))
                .map(m -> m.getUsuario())
                .collect(Collectors.toList());
            
            request.getSession().setAttribute("ultima_actualizacion", String.valueOf(System.currentTimeMillis()));
            
            if(!usuarios.isEmpty()) {
                request.setAttribute("usuarios", usuarios);
            }

            return mapping.getForwardByName("success");

        } catch (IOException | URISyntaxException | RuntimeException e) {
            SalaBean sala = (SalaBean) request.getSession().getAttribute("sala");
            request.setAttribute("message", "Error al intentar actualizar usuarios de Sala " + sala.getId() + ": " + e.getMessage());
            response.setStatus(400);
            return mapping.getForwardByName("failure");
        }
    }
    
}
