package ar.edu.ubp.das.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.stream.MalformedJsonException;

/**
 * Servlet implementation class FeriadosEliminarServlet
 */
@WebServlet("/feriados-eliminar.jsp")
public class FeriadosEliminarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeriadosEliminarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=ISO-8859-1");
		try {            
        	String patron = String.valueOf(request.getSession().getAttribute("formato"));
        	Locale idioma = new Locale(String.valueOf(request.getSession().getAttribute("idioma")));
            SimpleDateFormat formato   = new SimpleDateFormat(patron, idioma);
            SimpleDateFormat formatoUS = new SimpleDateFormat("yyyy-MM-dd");
            
            URI uri = URI.create("http://localhost:8085/FeriadosRest/rest/feriados/" + formatoUS.format(formato.parse(request.getParameter("feriado"))));            
                        
			HttpRequestBase req = new HttpDelete();
                            req.setURI(uri);
	    	
			HttpClient client = HttpClientBuilder.create().build();
	    		            
            HttpResponse resp = client.execute(req);
            
            HttpEntity responseEntity = resp.getEntity();
            StatusLine responseStatus = resp.getStatusLine();

            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
        } 
        catch (ParseException | MalformedJsonException | RuntimeException ex) {
            response.setStatus(400);
            this.printMessage(response, ex.getMessage());
        } 
	}
	
    private void printMessage(HttpServletResponse response, String message) throws IOException {
        PrintWriter out = response.getWriter();
        out.println(message);
        out.close();
    }
}
