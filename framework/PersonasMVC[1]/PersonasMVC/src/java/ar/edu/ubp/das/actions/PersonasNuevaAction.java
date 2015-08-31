/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.actions;

import ar.edu.ubp.das.mvc.actions.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mpgaviotta
 */
public class PersonasNuevaAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.gotoPage("/nueva.jsp", request, response);
    }
    
}
