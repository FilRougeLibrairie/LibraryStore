package accessBD;


import names.SQLNames.AuthorNames;
import names.SQLNames.BookNames;
import entity.Author;
import entity.Book;
import entity.BookLanguage;
import entity.Editor;
import entity.Forma;
import entity.Vat;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class BookDAO  implements Serializable {

    private final String TABLE = "Book";
    private MyConnexion mc;
    private final String ISBN_13 = BookNames.ISBN_13;
    private final String VAT_CODE = BookNames.VAT_CODE;
    private final String EDITOR_ID = BookNames.EDITOR_ID;
    private final String TITLE = BookNames.TITLE;
    private final String SUBTITLE = BookNames.SUBTITLE;
    private final String PUBLICATION_YEAR = BookNames.PUBLICATION_YEAR;
    private final String PRICE_HT = BookNames.PRICE_HT;
    private final String RESUME = BookNames.RESUME;
    private final String QUANTITY = BookNames.QUANTITY;
    private final String STATUS = BookNames.STATUS;
    private final String FRONT_COVER = BookNames.FRONT_COVER;
    private final String PAGE_NUMBER = BookNames.PAGE_NUMBER;
    private final String LANGUAGE_ID = BookNames.LANGUAGE_ID;
    private final String FORMAT_ID = BookNames.FORMAT_ID;

    Vector<Book> bookList;
    private Book book;
    private Vat vat;
    private Editor editor;
    private BookLanguage language;
    private Forma format;

    private String COLUMNS_CREATE = ISBN_13 + ", " + VAT_CODE + ", "
            + EDITOR_ID + ", " + TITLE + ", " + SUBTITLE + ", "
            + PUBLICATION_YEAR + ", " + PRICE_HT + ", " + RESUME + ", "
            + QUANTITY + ", " + STATUS + ", " + FRONT_COVER + ", "
            + PAGE_NUMBER + ", " + LANGUAGE_ID + ", " + FORMAT_ID;

    public BookDAO() throws NamingException {
         mc= new MyConnexion();
    }

     
    public void create(Object obj) {
        book = (Book) obj;
        String query = "IF NOT EXISTS (SELECT * FROM " + TABLE + " WHERE " + ISBN_13 + " = '" + book.getBooIsbn13() + "')"
                + "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setString(1, book.getBooIsbn13());
            pstmt.setFloat(2, book.getVatCode().getVatCode());
            pstmt.setInt(3, book.getEdiId().getEdiId());
            pstmt.setString(4, book.getBooTitle());
            pstmt.setString(5, book.getBooSubtitle());
            pstmt.setString(6, book.getBooPublishYear());
            pstmt.setFloat(7, book.getBooPriceHT());
            pstmt.setString(8, book.getBooResume());
            pstmt.setInt(9, book.getBooQuantity());
            pstmt.setInt(10, book.getBooStatus());
            pstmt.setString(11, book.getBooFrontCover());
            pstmt.setInt(12, book.getBooPageNumber());
            pstmt.setInt(13, book.getBooLangCode().getBooLangCode());
            pstmt.setInt(14, book.getFormat().getForId());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
            
        }
    }

     
    public void delete(Object obj) {
        String bookId = ((Book) obj).getBooIsbn13();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ISBN_13)
                .append(" = ")
                .append("'" + bookId + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
    }

     
    public void update(Object obj) {
        book = (Book) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(VAT_CODE).append(" = ?, ");
        query.append(EDITOR_ID).append(" = ?, ");
        query.append(TITLE).append(" = ?, ");
        query.append(SUBTITLE).append(" = ?, ");
        query.append(PUBLICATION_YEAR).append(" = ?, ");
        query.append(PRICE_HT).append(" = ?, ");
        query.append(RESUME).append(" = ?, ");
        query.append(QUANTITY).append(" = ?, ");
        query.append(STATUS).append(" = ?, ");
        query.append(FRONT_COVER).append(" = ?, ");
        query.append(PAGE_NUMBER).append(" = ?, ");
        query.append(LANGUAGE_ID).append(" = ?, ");
        query.append(FORMAT_ID).append(" = ? ");

        query.append("WHERE " + ISBN_13 + " = '")
                .append(book.getBooIsbn13())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setFloat(1, book.getVatCode().getVatCode());
            pstmt.setInt(2, book.getEdiId().getEdiId());
            pstmt.setString(3, book.getBooTitle());
            pstmt.setString(4, book.getBooSubtitle());
            pstmt.setString(5, book.getBooPublishYear());
            pstmt.setFloat(6, book.getBooPriceHT());
            pstmt.setString(7, book.getBooResume());
            pstmt.setInt(8, book.getBooQuantity());
            pstmt.setInt(9, book.getBooStatus());
            pstmt.setString(10, book.getBooFrontCover());
            pstmt.setInt(11, book.getBooPageNumber());
            pstmt.setInt(12, book.getBooLangCode().getBooLangCode());
            pstmt.setInt(13, book.getFormat().getForId());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            

        }
    }

     
    public Vector findAll() {
        bookList = new Vector<Book>();
        book = null;
        vat = null;
        editor = null;
        language = null;
        format = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY " + BookNames.TITLE;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();

                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
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
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return bookList;
    }

     
    public Book find(int id) {
        book = null;
        vat = null;
        editor = null;
        language = null;
        format = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ISBN_13)
                .append(" = ")
                .append(id);

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();

                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
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

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return book;
    }

    public Author findAuthorByBook (String isbn) {
        Author author = null;
        Book boo = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT aut.autId, autLastName, autFirstName FROM ")
                .append("Author aut ")
                .append("JOIN WRITING wri ")
                .append("ON aut.autID = wri.autID ")
                .append("JOIN Book boo ")
                .append("ON wri.booIsbn13 = boo.booIsbn13 ")
                .append("WHERE wri.booIsbn13 ")
                .append(" = ")
                .append("'" + isbn + "'");
                

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(AuthorNames.ID));
                    author.setAutLastName(rs.getString(AuthorNames.LAST_NAME));
                    author.setAutFirstName(rs.getString(AuthorNames.FIRST_NAME));
                }
            } else {
                throw new SQLException("");
            }

        } catch (SQLException ex) {
          //  System.out.println("ERROR Retrieving Author Object : " + ex.getMessage());
            

        }
        return author;
        
    }
    
    public Author findAuthorByTitle (String isbn) {
        Author author = null;
        Book boo = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT aut.autId, autLastName, autFirstName FROM ")
                .append("Author aut ")
                .append("JOIN WRITING wri ")
                .append("ON aut.autID = wri.autID ")
                .append("JOIN Book boo ")
                .append("ON wri.booIsbn13 = boo.booIsbn13 ")
                .append("WHERE wri.booIsbn13 ")
                .append(" = ")
                .append("'" + isbn + "'");
                

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(AuthorNames.ID));
                    author.setAutLastName(rs.getString(AuthorNames.FIRST_NAME));
                    author.setAutFirstName(rs.getString(AuthorNames.LAST_NAME));
                    
                    
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return author;
        
    }
    
     
    public Book find(String name) {book = null;
        vat = null;
        editor = null;
        language = null;
        format = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ISBN_13)
                .append(" = ")
                .append("'" + name + "' ")
                .append("ORDER BY " + BookNames.TITLE);

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();

                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
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

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return book;
    }
 

     
    public Vector <Book> findByColumn(String column, String term) {
        bookList = new Vector<Book>();
        book = null;
        vat = null;
        editor = null;
        language = null;
        format = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" LIKE ")
                .append("'" + term + "%'");


        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                   book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();

                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
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
