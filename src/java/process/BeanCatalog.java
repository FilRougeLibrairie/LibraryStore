package process;

import accessBD.AuthorDAO;
import accessBD.BookDAO;
import accessBD.EditorDAO;
import accessBD.VatDAO;
import entity.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.NamingException;

public class BeanCatalog implements Serializable {

    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private EditorDAO editorDAO;
    private VatDAO vatDAO;

    public BeanCatalog() throws NamingException {
        bookDAO = new BookDAO();
        authorDAO = new AuthorDAO();
        editorDAO = new EditorDAO();
        vatDAO = new VatDAO();
    }

    public Collection findCatalogItems(int itemsPerPage, int countFrom) {
        
        Collection listItems = new ArrayList();
        Collection<Book> books = bookDAO.findBooksByOffset(itemsPerPage, countFrom);
        for(Book book : books){
            CatalogItem catItem = new CatalogItem();
            catItem.setBook(book);
            catItem.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catItem.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catItem.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            listItems.add(catItem);
        }
        return listItems;
    }
    
    public int getItemsCount(){
        return bookDAO.countBooksNumber();
    }

}
