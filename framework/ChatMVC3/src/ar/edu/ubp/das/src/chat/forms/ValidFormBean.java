/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.ubp.das.src.chat.forms;

import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jav
 */
public class ValidFormBean extends DynaActionForm {
    @Override
    public void validate(ActionMapping mapping, HttpServletRequest request) throws RuntimeException {
            if(this.getItem("user").isEmpty()) {
                    throw new RuntimeException("nombreUsuarioRequired");
            }

            if(this.getItem("pw").isEmpty()) {
                    throw new RuntimeException("passwordRequired");
            }
    }
    
}
