package accessBD;

//import names.SQLNames.KeywordsNames;
import entity.Author;
import entity.Book;
import entity.BookLanguage;
import entity.Editor;
import entity.Forma;
import entity.Keywords;
import entity.Vat;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;

public class KeywordsDAO implements Serializable {

    private MyConnexion mc;
    private final String NAME = "keyName";
 private final String TABLE = "Book";
    private final String ISBN_13 = "booIsbn13";
    private final String VAT_CODE = "vatCode";
    private final String EDITOR_ID = "ediId";
    private final String TITLE = "booTitle";
    private final String SUBTITLE = "booSubtitle";
    private final String PUBLICATION_YEAR = "booPublishYear";
    private final String PRICE_HT = "booPriceHT";
    private final String RESUME = "booResume";
    private final String QUANTITY = "booQuantity";
    private final String STATUS = "booStatus";
    private final String FRONT_COVER = "booFrontCover";
    private final String PAGE_NUMBER = "booPageNumber";
    private final String LANGUAGE_ID = "bookLangCode";
    private final String FORMAT_ID = "forId";
    private String COLUMNS = NAME;

    public KeywordsDAO() throws NamingException {
        mc = new MyConnexion();
    }

    public void create(Object obj) {
        Keywords kw = (Keywords) obj;

        String query = "IF NOT EXISTS(SELECT * FROM keywords WHERE keyName= '" + kw.getKeyName() + "')"
                + "INSERT INTO KEYWORDS VALUES ('" + kw.getKeyName() + "')";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query);) {

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING CONTACT : " + ex.getErrorCode() + " / " + ex.getMessage());

        }
    }

    public void delete(Object obj) {
        String kw = ((Keywords) obj).getKeyName();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM KEYWORDS WHERE ")
                .append(NAME)
                .append(" = ")
                .append("'" + kw + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Done");

        }
    }

    public Boolean answer(Keywords obj) {
        Boolean answer = true;
        Keywords kw = (Keywords) obj;
        String query = "SELECT * FROM keywords WHERE keyName= '" + kw.getKeyName() + "'";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {
                answer = true;
            } else {
                answer = false;
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return answer;

    }

    public void update(Object obj) {
        Keywords kw = (Keywords) obj;
        StringBuilder query = new StringBuilder("UPDATE KEYWORDS SET ");
        query.append(NAME).append(" = ? ");

        query.append("WHERE " + NAME + " = '")
                .append(kw.getKeyName())
                .append("'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, kw.getKeyName());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING CONTACT : " + ex.getMessage());

        }
    }

    public Keywords find(String keyName) {
        Keywords kw = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM Keywords WHERE ")
                .append(NAME)
                .append(" = ")
                .append("'" + keyName + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    kw = new Keywords();
                    kw.setKeyName(rs.getString(NAME));

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Customer : " + ex.getMessage());

        }
        return kw;

    }

    public Vector findAll() {
        Vector<Keywords> keywordsList = new Vector<Keywords>();
        Keywords kw = null;

        String query = "SELECT * FROM Keywords";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    kw = new Keywords();
                    kw.setKeyName(rs.getString(NAME));
                    keywordsList.add(kw);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Customer : " + ex.getMessage());

        }
        return keywordsList;
    }

    public Vector<Keywords> findByColumn(String column, String term) {

        Vector<Keywords> keywordsList = new Vector<Keywords>();
        Keywords kw = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM KEYWORDS WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    kw = new Keywords();
                    kw.setKeyName(NAME);
                    keywordsList.add(kw);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Customer : " + ex.getMessage());

        }
        return keywordsList;
    }

    public Object find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vector<Book> findByKW(String term) {
        
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;

        String query = " select * "
                +" FROM Book boo "
                +" JOIN Vat vat "
                +" ON boo.vatCode = vat.vatCode "
                +" JOIN Writing wri "
                +" ON boo.booIsbn13 = wri.booIsbn13 "
                +" JOIN Author aut "
                +" ON wri.autId = aut.autId "
                +" JOIN Definitions def "
                +" ON boo.booIsbn13 = def.booIsbn13 "
                +" JOIN Keywords kew "
                +" ON def.keyName = kew.keyName "
                +" WHERE def.keyName  = '" + term  + "' AND booStatus=0 ";
                
                
                

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();

                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt("vatCode"));
                    book.setVatCode(vat);
                    editor.setEdiId(rs.getInt(EDITOR_ID));
                    book.setEdiId(editor);
                    book.setBooTitle(rs.getString(TITLE));
                    book.setBooSubtitle(rs.getString(SUBTITLE));
                    book.setBooPublishYear(rs.getString(PUBLICATION_YEAR));
                    book.setBooPriceHT(rs.getFloat(PRICE_HT));
                    book.setBooResume(rs.getString(RESUME));
                    book.setBooQuantity(rs.getInt(QUANTITY));
                    book.setBooStatus(rs.getInt(STATUS));
                    book.setBooFrontCover(rs.getString(FRONT_COVER));
                    book.setBooPageNumber(rs.getInt(PAGE_NUMBER));
                    language.setBooLangCode(rs.getInt(LANGUAGE_ID));
                    book.setBooLangCode(language);
                    format.setForId(rs.getInt(FORMAT_ID));
                    book.setFormat(format);

                    bookList.add(book);
                    System.out.println("MA LISTEEEEEEEEEEEEEE"+bookList);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return bookList;
        
    }

}
