package process;

import accessBD.AuthorDAO;
import accessBD.BookDAO;
import accessBD.BookLanguageDAO;
import accessBD.CustomerDAO;
import accessBD.EditorDAO;
import accessBD.FormatsDAO;
import accessBD.OfferDAO;
import accessBD.VatDAO;
import entity.Author;
import entity.Book;
import entity.Offer;
import entity.Review;
import entity.webPage.CatalogElement;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.NamingException;

public class GestionCatalogue implements Serializable {

    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private EditorDAO editorDAO;
    private VatDAO vatDAO;
    private BookLanguageDAO bookLanguageDAO;
    private FormatsDAO formatsDAO;
    private OfferDAO offerDAO;
    private CustomerDAO customerDAO;

    public GestionCatalogue() throws NamingException {
        bookDAO = new BookDAO();
        authorDAO = new AuthorDAO();
        editorDAO = new EditorDAO();
        vatDAO = new VatDAO();
        bookLanguageDAO = new BookLanguageDAO();
        formatsDAO = new FormatsDAO();
        offerDAO = new OfferDAO();
        customerDAO = new CustomerDAO();
    }

    // Tout le catalogue
    public Collection findBook() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllAuthor();

        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);

            lp.add(catElement);
        }
        return lp;
    }

    public Collection findBookPage(int total, int numero) throws SQLException, NamingException {

        Collection lp = new ArrayList();

        BookDAO bookDAO = new BookDAO();
        Collection<Book> books = bookDAO.findBooksByOffset(total, numero);

        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);

            lp.add(catElement);
        }
        return lp;
    }

    public Collection findReview(String isbn) throws NamingException {
        Collection<Review> reviews = bookDAO.findAllReview(isbn);
        for (Review review : reviews) {
            review.setCusId(customerDAO.find(review.getCusId().getCusID()));
        }
        return reviews;
    }

    public Collection findBook(String id) throws SQLException {

        Collection lp = new ArrayList();
        Book books = bookDAO.find(id);
        CatalogElement catElement = new CatalogElement();
        catElement.setBook(books);
        catElement.setVat(vatDAO.find(books.getVatCode().getVatCode()));
        catElement.setListAuthors(authorDAO.findAuthorByBook(books.getBooIsbn13()));
        catElement.setEditor(editorDAO.find(books.getEdiId().getEdiId()));
        catElement.setBookLanguage(bookLanguageDAO.find(books.getBooLangCode().getBooLangCode()));
        catElement.setForma(formatsDAO.find(books.getFormat().getForId()));
        Offer offDiscount = offerDAO.findAllEventByBook(books.getBooIsbn13());
        catElement.setDiscount(offDiscount);
        catElement.setListReview(bookDAO.findAllReview(id));
        lp.add(catElement);

        return lp;
    }

    // Nouveauté
    public Collection findBookNewBook() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllNewBook();
        for (Book book : books) {
            System.out.println(book);
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }

        return lp;
    }

                //TRIE nouveaute
    public Collection findBookNewBookByPage(int itemsPerPage, int countFrom) throws SQLException, NamingException {

        Collection lp = new ArrayList();

        BookDAO bookDAO = new BookDAO();
        Collection<Book> books = bookDAO.findBookNewBookByPage(itemsPerPage, countFrom);

        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);

            lp.add(catElement);
        }
        return lp;
    }

    // Prix 
    public Collection findBookPrice(Float min, Float max) throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllPrice(min, max);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }

                ///// BY pagination
    public Collection findBookPriceByBook(Float min, Float max, int total, int numero) throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllPrice(min, max, total, numero);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }

//    //review
//    public Collection findReview(String id) {
//
//        Collection lp = new ArrayList();
//        Collection<Review> reviews = bookDAO.findAllReview(id);
//        for (Review review : reviews) {
//            review.getRevComment();
//            lp.add(review);
//        }
//
//        return lp;
//
//    }

    // authors
    public Collection findAuthors(String id) {

        Collection lp = new ArrayList();
        Collection<Author> authors = bookDAO.findAllAuthors(id);
        for (Author aut : authors) {
            aut.getAutFirstName();
            aut.getAutLastName();
            lp.add(aut);
        }

        return lp;

    }

    public Collection findBookReview0(int review) throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllReview(review);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }

        return lp;
    }

    public Collection findBookPageReviewPagi(int review, int itemsPerPage, int countFrom) throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllReviewPagi(review, itemsPerPage, countFrom);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }

        return lp;
    }

    public Collection findBookReview1() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllReview(1);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }

    public Collection findBookReview2() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllReview(2);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }

    public Collection findBookReview3() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllReview(3);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }

    public Collection findBookReview4() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllReview(4);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }

    public Collection findBookReview5() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllReview(5);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }
    // A paraitre

    public Collection findAllNewBookYear() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllNewBookYear();
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }

        ////// trie
    public Collection findBookPageAllNewBookYear(int total, int numero) throws SQLException, NamingException {

        Collection lp = new ArrayList();

        BookDAO bookDAO = new BookDAO();
        Collection<Book> books = bookDAO.findAllNewBookYear(total, numero);

        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);

            lp.add(catElement);
        }
        return lp;
    }

    // par auteur
    public Collection findByAuthor(int id) throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findByAuthor(id);
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);
        }
        return lp;
    }

    // 3 derniers ajouts
    public Collection findAllNewthreeBook() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findAllNews();
        for (Book book : books) {
            CatalogElement catElement = new CatalogElement();
            catElement.setBook(book);
            catElement.setVat(vatDAO.find(book.getVatCode().getVatCode()));
            catElement.setListAuthors(authorDAO.findAuthorByBook(book.getBooIsbn13()));
            catElement.setEditor(editorDAO.find(book.getEdiId().getEdiId()));
            Offer offDiscount = offerDAO.findAllEventByBook(book.getBooIsbn13());
            catElement.setDiscount(offDiscount);
            lp.add(catElement);

        }
        return lp;
    }

    //tous les commentaires pour un livre
    
    ////////////////////////////////WILL////////////////////////////////
    
    ///RECHERCHE SOUSTHEME

    public Collection findbyBySubTheme(int id) throws SQLException {

        Collection lp = new ArrayList();
        Collection<Book> books = bookDAO.findBySubTheme(id);
        
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
