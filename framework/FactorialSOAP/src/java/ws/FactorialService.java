/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author mpgaviotta
 */
@WebService(serviceName = "FactorialService")
@Stateless()
public class FactorialService {

    @WebMethod(operationName = "factorial")
    public String factorial(@WebParam(name = "nro") long nro)
    {
        return Long.toString(this.fact(nro));
    }

    private long fact(long x)
    {
        if(x == 0)
	{
            return 1;
	}
        else
        {
            return x * fact(x - 1);
        }
    }

}
