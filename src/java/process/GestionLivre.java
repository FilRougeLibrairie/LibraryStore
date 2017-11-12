package process;

import accessBD.AuthorDAO;
import accessBD.BookDAO;
import accessBD.BookLanguageDAO;
import accessBD.EditorDAO;
import accessBD.FormatsDAO;
import accessBD.OfferDAO;
import accessBD.SubThemeDAO;
import accessBD.ThemeDAO;
import accessBD.VatDAO;
import entity.Book;
import entity.SubTheme;
import entity.Theme;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.naming.NamingException;

public class GestionLivre implements Serializable {

    private BookDAO bookDAO;
    private ThemeDAO themeDAO;
    private SubThemeDAO subThemeDAO;
    private VatDAO vatDAO;
    private AuthorDAO authorDAO;
    private EditorDAO editorDAO;
    private BookLanguageDAO bookLanguageDAO;
    private FormatsDAO formatsDAO;
    private OfferDAO offerDAO;
           

    public GestionLivre() throws NamingException {
        bookDAO = new BookDAO();
        themeDAO = new ThemeDAO();
        subThemeDAO = new SubThemeDAO();
        vatDAO = new VatDAO();
        authorDAO = new AuthorDAO();
        editorDAO = new EditorDAO();
        bookLanguageDAO = new BookLanguageDAO();
        formatsDAO = new FormatsDAO();
        offerDAO = new OfferDAO();
    }

    public Collection<Book> pageTousLivres() throws SQLException {
        return bookDAO.rechercheTousLivres();
    }


    public List<Theme> rechercheParTheme() throws SQLException {

        List<Theme> theme = themeDAO.listeDesThemes();

        return theme;
    }

    public List<SubTheme> rechercheParSousTheme(int themeId) throws SQLException {

        return subThemeDAO.listeDesSousThemes(themeId);

    }

    public List<Book> findBookBySubTheme(int subId) throws SQLException {

        List<String> listeISBN = subThemeDAO.rechercheBookBySubTheme(subId);
        List<Book> listeBook = new ArrayList<>();
        for (String isbn : listeISBN) {
            Book book = bookDAO.find(isbn);
            listeBook.add(book);
        }
        return listeBook;
    }

}
