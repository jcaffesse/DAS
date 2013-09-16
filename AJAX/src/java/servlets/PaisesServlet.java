/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.PaisBean;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mpgaviotta
 */
@WebServlet(name = "PaisesServlet", urlPatterns = {"/index.jsp"})
public class PaisesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=windows-1252");
            
            Connection conn;
            CallableStatement st;
            List<PaisBean> paises;
            PaisBean pais;
            ResultSet result;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlserver://ocrux;databaseName=das", "sa", "pyxis");            
            conn.setAutoCommit(true);

            st = conn.prepareCall("{CALL dbo.get_paises}");

            result = st.executeQuery();
            
            paises = new LinkedList();
            while(result.next()) {
                pais = new PaisBean();
                pais.setCodPais(result.getString("cod_pais"));
                pais.setPais(result.getString("pais"));
                paises.add(pais);
            }
            request.setAttribute("paises", paises);
            
            st.close();
            conn.close();           
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            request.setAttribute("error", ex.getMessage());            
        }
        finally {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/paises.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
