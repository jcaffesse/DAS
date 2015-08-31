/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Jersey REST client generated for REST resource:FactorialResource
 * [factorial]<br>
 * USAGE:
 * <pre>
 *        FactorialClient client = new FactorialClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author mpgaviotta
 */
public class FactorialClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FactorialRest/webresources";

    public FactorialClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("factorial");
    }

    public String factorialP(String nro) {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{nro}));
        return resource.accept(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public String factorialQ(String nro) {
        WebResource resource = webResource;
        if (nro != null) {
            resource = resource.queryParam("nro", nro);
        }
        return resource.accept(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public void close() {
        client.destroy();
    }
    
}
