/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.actions;

import ar.edu.ubp.das.mvc.actions.Action;
import ar.edu.ubp.das.mvc.daos.Dao;
import ar.edu.ubp.das.mvc.daos.DaoFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mpgaviotta
 */
public class PersonasGuardarNuevaAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Dao dao = DaoFactory.getDao("Personas");
            dao.insert(this.getForm());
    }
    
}
