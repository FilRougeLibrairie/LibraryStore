
package process;


import accessBD.AuthorDAO;
import accessBD.BookDAO;
import accessBD.EditorDAO;
import accessBD.HavingDAO;
import accessBD.OfferDAO;
import accessBD.VatDAO;
import entity.Book;
import entity.Offer;
import entity.webPage.CatalogElement;
import entity.webPage.OfferElement;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.NamingException;


public class GestionEvent implements Serializable {
    
    
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private EditorDAO editorDAO;
    private VatDAO vatDAO;
    private OfferDAO offerDAO;
    private HavingDAO haveDAO;

    public GestionEvent() throws NamingException {
        bookDAO = new BookDAO();
        authorDAO = new AuthorDAO();
        editorDAO = new EditorDAO();
        vatDAO = new VatDAO();
        offerDAO = new OfferDAO();
    }
   
    // tous les offers actif
    
    
    public Collection findEvent() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Offer> offers = offerDAO.findAllEventStatus1();
        for (Offer offer : offers) {
            OfferElement offerElement = new OfferElement();
            offerElement.setOffer(offer);
            lp.add(offerElement);
        }
        return lp;
    }
    
    // tout les books offer
   
     public Collection findAllBookByOffer() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllBookByOffer();
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
    
    
     
     
     public Collection findAllBookByOffer(int term) throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllBookByOffer(term);
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
