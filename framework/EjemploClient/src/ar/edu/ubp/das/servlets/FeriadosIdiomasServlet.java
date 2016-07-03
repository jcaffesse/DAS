package ar.edu.ubp.das.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import ar.edu.ubp.das.beans.IdiomaFeriadoBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;

/**
 * Servlet implementation class FeriadosIdiomasServlet
 */
@WebServlet("/feriados-idiomas-editar.jsp")
public class FeriadosIdiomasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeriadosIdiomasServlet() {
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
                        
			HttpRequestBase req = new HttpPost();
                            req.setURI(uri);
	    	
			HttpClient client = HttpClientBuilder.create().build();
	    		            
            HttpResponse resp = client.execute(req);
            
            HttpEntity responseEntity = resp.getEntity();
            StatusLine responseStatus = resp.getStatusLine();

            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
            
			Gson gson = new Gson();
			LinkedList<IdiomaFeriadoBean> idiomas = gson.fromJson(restResp, new TypeToken<LinkedList<IdiomaFeriadoBean>>(){}.getType());
			request.setAttribute("idiomas", idiomas);       
			this.gotoPage("/feriados-idiomas.jsp", request, response);
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

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
        catch (ServletException ex) {
            this.printMessage(response, ex.getMessage());
        }
    }

}
