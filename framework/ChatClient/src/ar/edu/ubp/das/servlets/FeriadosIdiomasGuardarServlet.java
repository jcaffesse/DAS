package ar.edu.ubp.das.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

import com.google.gson.stream.MalformedJsonException;

/**
 * Servlet implementation class FeriadosIdiomasGuardarServlet
 */
@WebServlet("/feriados-idiomas-guardar.jsp")
public class FeriadosIdiomasGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeriadosIdiomasGuardarServlet() {
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
            
            HttpPut req = new HttpPut("http://localhost:8085/FeriadosRest/rest/feriados/idiomas/");
                   
       
            String idiomas[] = request.getParameterValues("idioma");
            String nombres[] = request.getParameterValues("nom_feriado");
            for(int i = 0, l = idiomas.length; i < l; i ++) {
                List <NameValuePair> nvps = new ArrayList <NameValuePair>();
                                     nvps.add(new BasicNameValuePair("idioma", idiomas[i]));
                                     nvps.add(new BasicNameValuePair("feriado", formatoUS.format(formato.parse(request.getParameter("feriado")))));
                                     nvps.add(new BasicNameValuePair("nomFeriado", new String(nombres[i].getBytes("UTF-8"), "ISO-8859-1")));

                req.setEntity(new UrlEncodedFormEntity(nvps));

                HttpClient client = HttpClientBuilder.create().build();
                HttpResponse resp = client.execute(req);

                HttpEntity responseEntity = resp.getEntity();
                StatusLine responseStatus = resp.getStatusLine();

                if(responseStatus.getStatusCode() != 200) {
                	throw new RuntimeException(EntityUtils.toString(responseEntity));
                }
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
