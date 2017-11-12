
package process;


import accessBD.MyLibraryDAO;
import entity.MyLibrary;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.NamingException;


public class GestionLibrary implements Serializable {
    
   
    private MyLibraryDAO myLibraryDAO;

    public GestionLibrary() throws NamingException{
       
            myLibraryDAO = new MyLibraryDAO();
        
    }
    
    
    public Collection <MyLibrary> findLibrary() throws SQLException {

        Collection <MyLibrary>  lp = new ArrayList();
        lp= myLibraryDAO.findAll();
            
        return lp;
    }
    
    
    
    
    
    
}
