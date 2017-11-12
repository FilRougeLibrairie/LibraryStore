
package process;

import accessBD.AuthorDAO;
import accessBD.BookDAO;
import accessBD.BookLanguageDAO;
import accessBD.EditorDAO;
import accessBD.FormatsDAO;
import accessBD.KeywordsDAO;
import accessBD.OfferDAO;
import accessBD.VatDAO;
import entity.Author;
import entity.Book;
import entity.Offer;
import entity.webPage.AuthorElement;
import entity.webPage.CatalogElement;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.NamingException;


public class GestionSearch implements Serializable {
    

     private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private EditorDAO editorDAO;
    private VatDAO vatDAO;
    private BookLanguageDAO bookLanguageDAO;
    private FormatsDAO formatsDAO;
    private OfferDAO offerDAO;
    private KeywordsDAO keywordDAO;

    public GestionSearch() throws NamingException {
        bookDAO = new BookDAO();
        authorDAO = new AuthorDAO();
        editorDAO = new EditorDAO();
        vatDAO = new VatDAO();
        bookLanguageDAO = new BookLanguageDAO();
        formatsDAO = new FormatsDAO();
        offerDAO =new OfferDAO();
        keywordDAO= new KeywordsDAO();
    }
    
    
    
    
    // Tout les authors
    public Collection <Author> findAuthor(String nom) throws SQLException {
        Collection lp = new ArrayList();
        Collection<Author> authors = authorDAO.findByColumnName("autLastName", nom);
        
        for (Author author : authors) {
            AuthorElement authElement = new AuthorElement();
            authElement.setAuthor(author);
            
            lp.add(authElement);
        }
        
        
        return lp;
        
    }
    
    
     // Tous les books
    
    public Collection <Book> findBook(String nom) throws SQLException {
        
        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findByColumnSearch("booTitle", nom);
        
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount=offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            
            lp.add(catElement);
        }
        return lp;
        
    }
    
    
    // tous les books par isbn
    
    public Collection <Book> findBookIsbn(String nom) throws SQLException {
        
        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findByColumnSearch("booIsbn13", nom);
        
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount=offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            
            lp.add(catElement);
        }
        return lp;
        
    }
    
    // tous les books par kw
    
     public Collection <Book> findByKW(String nom) throws SQLException {
        System.out.println("HELOOOOOOOOOOO");
        Collection lp = new ArrayList();
        Collection<Book> books = keywordDAO.findByKW(nom);
        System.out.println(books);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount=offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            
            lp.add(catElement);
        }
        return lp;
    
        
    }
    
    
    
    
}
