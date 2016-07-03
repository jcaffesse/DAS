package ar.edu.ubp.das.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import ar.edu.ubp.das.beans.FeriadoBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;

/**
 * Servlet implementation class FeriadoServlet
 */
@WebServlet("/index.jsp")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=ISO-8859-1");
            try {    
                    HttpRequestBase req = new HttpGet("http://localhost:8080/login");

                    HttpClient client = HttpClientBuilder.create().build();
	    		            
            HttpResponse resp = client.execute(req);
            
            HttpEntity responseEntity = resp.getEntity();
            StatusLine responseStatus = resp.getStatusLine();

            String restResp = EntityUtils.toString(responseEntity);	

            if(responseStatus.getStatusCode() != 200) {
            	throw new RuntimeException(restResp);
            }
            
			Gson gson = new Gson();
			LinkedList<FeriadoBean> feriados = gson.fromJson(restResp, new TypeToken<LinkedList<FeriadoBean>>(){}.getType());
			request.setAttribute("feriados", feriados);
			
			this.gotoPage("/feriados.jsp", request, response);
        } 
            catch (NoClassDefFoundError | MalformedJsonException | RuntimeException ex) {
                this.printMessage(response, ex.getMessage());
            } 
	}

    private void printMessage(HttpServletResponse response, String message) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"./css/style.css\" />");
        out.println("<title>404</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"error\">" + message + "<br/><br/>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
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
