/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package process;

import accessBD.BookDAO;
import entity.Book;
import java.io.Serializable;
import java.util.Collection;
import javax.naming.NamingException;

/**
 *
 * @author ggarvanese
 */
public class beanPagination implements Serializable {
    
    private BookDAO bookDAO;
    private int totalItems;
    private int itemsPerPage;
    private int fromItemNumber;
    

    public beanPagination(int totalItems, int itemsPerPage, int fromItemNumber) throws NamingException {
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
        this.fromItemNumber = fromItemNumber;
        bookDAO = new BookDAO();
    }
    
    public Collection<Book> getBooks(int itemsPerPage, int fromItemNumber){
        
        return bookDAO.findBooksByOffset(itemsPerPage, fromItemNumber);
    }
    
    
}
