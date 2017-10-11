
package process;

import accessBD.BookDAO;
import entity.Book;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;

public class GestionCatalogue implements Serializable {
    private BookDAO bDAO;
    private List<String> keys;
    
    public GestionCatalogue() throws NamingException{
        
        bDAO= new BookDAO();
        keys = new ArrayList<>();
        
        keys.add("A-B");
        keys.add("C");
        keys.add("D-F");
        keys.add("G-K");
        keys.add("L-N");
        keys.add("O-R");
        keys.add("S-V");
        keys.add("W-Z");
        
    }
    
    public HashMap<String, List<Book>> findBook() throws SQLException{
        List<Book> lp = bDAO.findAll();
        
        HashMap<String, List<Book>> mp = new HashMap<>();
        for(String s : keys){
            List<Book> lcp = new ArrayList<>();
            mp.put(s, lcp);
        }
        for(Book p : lp){
            String nom = p.getBooTitle().charAt(0)+"";
            
            nom = nom.toUpperCase();
            for(String cle : keys){
                String regex = "["+cle+"]";
                if(nom.matches(regex)){
                    mp.get(cle).add(p);
                   
                }
            }
        }
        return mp;
    }
    
    public List<String> getKeys(){
        return keys;
    }
    
    
    
    
    
    
}
