package process;

import accessBD.AuthorDAO;
import accessBD.BookDAO;
import accessBD.EditorDAO;
import accessBD.VatDAO;
import entity.Author;
import entity.webPage.Ascii;
import entity.webPage.AuthorElement;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;

public class GestionAuthor implements Serializable {

    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private EditorDAO editorDAO;
    private VatDAO vatDAO;
    private List<String> keys;

    public GestionAuthor() throws NamingException {
        bookDAO = new BookDAO();
        authorDAO = new AuthorDAO();
        editorDAO = new EditorDAO();
        vatDAO = new VatDAO();

        // fonction ASCII
        keys = new ArrayList<>();
        Ascii ass = new Ascii();
        for (int i = 64; i < 90; i++) {
            String a = String.valueOf(ass.toChar(i + 1));
            i = i + 3;
            if (i == 91) {
                i = 90;
            }
            String b = String.valueOf(ass.toChar(i));
            String answer = a + "-" + b;
            keys.add(answer);
        }

    }

    // Tout les authors
    public Collection findAuthor() throws SQLException {

        Collection lp = new ArrayList();
        Collection<Author> authors = authorDAO.findAll();
        for (Author author : authors) {
            AuthorElement authorElement = new AuthorElement();
            authorElement.setAuthor(author);
            authorElement.setAuthorId(author);
            lp.add(authorElement);
        }
        return lp;
    }

    public List<String> getClefs() {
        return keys;
    }

    public Collection<Author> findAuthor(int id) throws SQLException {

        Collection<Author> lp = new ArrayList();
        Author author = authorDAO.find(id);
        lp.add(author);

        return lp;
    }

    // Author par clefs
    public Collection findAuthorByKeys(String keys) throws SQLException {
        Collection authorList = new ArrayList();
        Collection<Author> authors = authorDAO.findAll();

        for (Author author : authors) {

            String nom = author.getAutLastName().charAt(0) + "";
            nom = nom.toUpperCase();
            String regex = "[" + keys + "]";
            AuthorElement authorElement = new AuthorElement();
            if (nom.matches(regex)) {
                authorElement.setAuthor(author);
                authorElement.setAuthorId(author);
                authorList.add(authorElement);
            }
        }
        return authorList;
    }

}
