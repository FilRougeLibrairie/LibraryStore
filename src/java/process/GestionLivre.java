
package process;

import accessBD.BookDAO;
import entity.Book;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;


public class GestionLivre implements Serializable{
    
    private BookDAO bookDAO;
    
    private List<String> clefs;
    
    private Book book;
    
    
    
    public GestionLivre() throws NamingException {
        bookDAO = new BookDAO();
        clefs = new ArrayList<>();
        // attention jeux de test en dur - c'est pas bien -
        clefs.add("A-B");
        clefs.add("C");
        clefs.add("D-F");
        clefs.add("G-K");
        clefs.add("L-N");
        clefs.add("O-R");
        clefs.add("S-V");
        clefs.add("W-Z");
    }
    
    
//    public bookPage() throws NamingException {
//        
//        bookDAO = new BookDAO();
//        book = new Book();
//        
//        book.setBooTitle(null);
//        
//        
//        return book;
//    }
    
    
    
    
    
     public HashMap<String, List<Book>> findBook() throws SQLException{
        List<Book> lp = bookDAO.findAll();
        HashMap<String, List<Book>> mp = new HashMap<>();
        for(String s : clefs){
            List<Book> lcp = new ArrayList<>();
            mp.put(s, lcp);
        }
        for(Book p : lp){
            String nom = p.getBooTitle().charAt(0)+"";
            nom = nom.toUpperCase();
            for(String cle : clefs){
                String regex = "["+cle+"]";
                if(nom.matches(regex)){
                    mp.get(cle).add(p);
                }
            }
        }
        return mp;
    }
    
    public List<String> getClefs(){
        return clefs;
    }
    
}
