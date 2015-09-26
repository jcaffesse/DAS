package ar.edu.ubp.das.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Servlet implementation class FeriadosGuardarServlet
 */
@WebServlet("/feriados-guardar.jsp")
public class FeriadosGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeriadosGuardarServlet() {
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
        
            HttpPut req = new HttpPut("http://localhost:8085/FeriadosRest/rest/feriados/");
            
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
                                 nvps.add(new BasicNameValuePair("feriado", formatoUS.format(formato.parse(request.getParameter("feriado")))));
                                 nvps.add(new BasicNameValuePair("descFeriado", new String(request.getParameter("desc_feriado").getBytes("UTF-8"), "ISO-8859-1")));
                                 nvps.add(new BasicNameValuePair("tipoFeriado", request.getParameter("tipo_feriado")));
            
            req.setEntity(new UrlEncodedFormEntity(nvps));
            
			HttpClient client = HttpClientBuilder.create().build();
            
            HttpResponse resp = client.execute(req);
            
            HttpEntity responseEntity = resp.getEntity();
            StatusLine responseStatus = resp.getStatusLine();

            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
                
            this.gotoPage("/feriados-fila.jsp", request, response);
        } 
        catch (IllegalArgumentException | ParseException | NullPointerException ex) {
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
