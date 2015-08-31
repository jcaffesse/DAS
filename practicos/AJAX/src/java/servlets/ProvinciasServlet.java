/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ProvinciaBean;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProvinciasServlet", urlPatterns = {"/ProvinciasServlet"})
public class ProvinciasServlet extends HttpServlet {

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
        String address = "/provincias.jsp"; 
        try {
            response.setContentType("text/html;charset=windows-1252");
            
            Connection conn;
            CallableStatement st;
            List<ProvinciaBean> provincias;
            ProvinciaBean provincia;
            ResultSet result;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlserver://ocrux;databaseName=das", "sa", "pyxis");            
            conn.setAutoCommit(true);

            st = conn.prepareCall("{CALL dbo.get_provincias(?)}");
            st.setString(1, request.getParameter("cod_pais"));

            result = st.executeQuery();
            
            provincias = new LinkedList();
            while(result.next()) {
                provincia = new ProvinciaBean();
                provincia.setCodPais(result.getString("cod_pais"));
                provincia.setCodProvincia(result.getString("cod_provincia"));
                provincia.setProvincia(result.getString("provincia"));
                provincias.add(provincia);
            }
            request.setAttribute("provincias", provincias);
            
            st.close();
            conn.close();           
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            response.setStatus(400);            
            request.setAttribute("error", ex.getMessage());            
            address = "/error.jsp";
        }
        finally {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
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
