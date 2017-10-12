
package process;

import accessBD.CustomerDAO;
import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author CDI305
 */
public class BeanConnexionClient{
    CustomerDAO cusDAO;
    public BeanConnexionClient() {
        try {
            cusDAO = new CustomerDAO();
        } catch (NamingException ex) {
            Logger.getLogger(BeanConnexionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  List <Customer> listCus = new ArrayList();
  
    

    
    public boolean checkLogin(String adresseMail, String motDePasse)  {
        if (adresseMail == null) {
            return false;
        }
        if (motDePasse == null) {
            return false;
        }
        if (adresseMail.trim().isEmpty()) {
            return false;
        }
        if (motDePasse.trim().isEmpty()) {
            return false;
        }

        System.out.println(cusDAO.find(adresseMail).getCusClearPassword());
        if (adresseMail.equals(cusDAO.find(adresseMail).getCusEmail())) {
            if (motDePasse.equals(cusDAO.find(adresseMail).getCusClearPassword())) {
                return true;
            }
        }
        
        
        
        return false;
    }
}
    
    

