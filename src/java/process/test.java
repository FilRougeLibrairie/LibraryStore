package process;

import accessBD.CustomerDAO;
import entity.Customer;
import java.util.Vector;
import javax.naming.NamingException;



public class test {
    public static void main(String[] args) throws NamingException {

       
            CustomerDAO cDAO = new CustomerDAO();
            Vector<Customer> v = cDAO.findAll();
            System.out.println("---------------- DEBUT ------------");
            for(Customer c : v){
                System.out.println(c.getCusID());
            }
            System.out.println("---------------- FIN ------------");
      

    }
}
