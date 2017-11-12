package accessBD;

import entity.Author;
import entity.Book;
import entity.BookLanguage;
import entity.Customer;
import entity.Editor;
import entity.Forma;
import entity.Review;
import entity.Vat;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import javax.naming.NamingException;

public class BookDAO implements Serializable {

    private MyConnexion mc;
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
    
    private final int REVIEW_STATUS_PUBLISHED = 3;
    

    private String COLUMNS = ISBN_13 + ", " + VAT_CODE + ", "
            + EDITOR_ID + ", " + TITLE + ", " + SUBTITLE + ", "
            + PUBLICATION_YEAR + ", " + PRICE_HT + ", " + RESUME + ", "
            + QUANTITY + ", " + STATUS + ", " + FRONT_COVER + ", "
            + PAGE_NUMBER + ", " + LANGUAGE_ID + ", " + FORMAT_ID;

    public BookDAO() throws NamingException {
        mc = new MyConnexion();
    }

    public void create(Object obj) {
        Book book = (Book) obj;
        String query = "IF NOT EXISTS (SELECT * FROM " + TABLE + " WHERE " + ISBN_13 + " = '" + book.getBooIsbn13() + "')"
                + "INSERT INTO " + TABLE + " (" + COLUMNS + ")"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query);) {

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

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
    }

    public void update(Object obj) {
        Book book = (Book) obj;
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

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

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

    public int countBooksNumber() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Book";
        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return count;
    }

    public Collection findBooksByOffset(int itemsPerPage, int countFrom) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;

        StringBuilder query = new StringBuilder();
        query.append("DECLARE @itemsPerPage int, @countFrom int ")
                .append("SET @itemsPerPage = ? ")
                .append("SET @countFrom = ? ")
                .append("SELECT " + COLUMNS + " FROM Book b ")
                .append("WHERE booStatus=0 ")
                .append("ORDER BY b.booTitle ASC OFFSET @countFrom ROWS ")
                .append("FETCH NEXT @itemsPerPage ROWS ONLY");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, itemsPerPage);
            pstmt.setInt(2, countFrom);

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

    public Vector findAll() {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY " + TITLE;

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
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ISBN_13)
                .append(" = ")
                .append(id);

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

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

    public Author findAuthorByBook(String isbn) {
        Author author = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT aut.autId, autLastName, autFirstName FROM ")
                .append("Author aut ")
                .append("JOIN WRITING wri ")
                .append("ON aut.autID = wri.autID ")
                .append("JOIN Book boo ")
                .append("ON wri.booIsbn13 = boo.booIsbn13 ")
                .append("WHERE wri.booIsbn13 ")
                .append(" = ? ");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, isbn);

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt("autId"));
                    author.setAutLastName(rs.getString("autLastName"));
                    author.setAutFirstName(rs.getString("autFirstName"));
                }
            } else {
                throw new SQLException("");
            }

        } catch (SQLException ex) {
            //  System.out.println("ERROR Retrieving Author Object : " + ex.getMessage());

        }
        return author;

    }

    public Book find(String isbn) {
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ISBN_13)
                .append(" = ?");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, isbn);

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

    public Vector<Book> findByColumn(String column, String term) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" LIKE ")
                .append("'" + term + "%'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

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

    ////////////////////////////////CHRYS///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    public Collection findAllBookByOffer(int term) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "SELECT boo.booIsbn13, boo.booQuantity, vat.vatCode,boo.booPriceHT, boo.booFrontCover,offName, autLastName , autFirstName, booTitle, booSubtitle, offDateStart, offDateEnd, offDiscount "
                + "FROM Offer ofe "
                + "JOIN Have hav "
                + "ON ofe.offId = hav.offId "
                + "JOIN Book boo "
                + "ON boo.booIsbn13 = hav.booIsbn13 "
                + "JOIN Writing wri "
                + "ON boo.booIsbn13 = wri.booIsbn13 "
                + "JOIN Author aut "
                + "ON wri.autId = aut.autId "
                + "JOIN vat vat "
                + "ON vat.vatCode=boo.vatCode "
                + "WHERE offDateStart<GETDATE() AND offDateEnd>GETDATE() and ofe.offId=" + term;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
                    book.setVatCode(vat);
                    book.setBooTitle(rs.getString(TITLE));
                    book.setBooSubtitle(rs.getString(SUBTITLE));
                    book.setBooPriceHT(rs.getFloat(PRICE_HT));
                    book.setBooQuantity(rs.getInt(QUANTITY));
                    book.setBooFrontCover(rs.getString(FRONT_COVER));

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

    //Livre Offer
    public Collection findAllBookByOffer() {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "SELECT boo.booIsbn13, vat.vatCode,boo.booPriceHT, boo.booFrontCover,offName, autLastName , autFirstName, booTitle, booSubtitle, offDateStart, offDateEnd, offDiscount "
                + "FROM Offer ofe "
                + "JOIN Have hav "
                + "ON ofe.offId = hav.offId "
                + "JOIN Book boo "
                + "ON boo.booIsbn13 = hav.booIsbn13 "
                + "JOIN Writing wri "
                + "ON boo.booIsbn13 = wri.booIsbn13 "
                + "JOIN Author aut "
                + "ON wri.autId = aut.autId "
                + "JOIN vat vat "
                + "ON vat.vatCode=boo.vatCode "
                + "WHERE offDateStart<GETDATE() AND offDateEnd>GETDATE() AND boo.booStatus=0";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
                    book.setVatCode(vat);
                    book.setBooTitle(rs.getString(TITLE));
                    book.setBooSubtitle(rs.getString(SUBTITLE));
                    book.setBooPriceHT(rs.getFloat(PRICE_HT));

                    book.setBooFrontCover(rs.getString(FRONT_COVER));

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

    // TOUS LES LIVRES
    public Collection findAllAuthor() {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "SELECT * FROM " + TABLE + " boo "
                + " WHERE boo.booStatus=0";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    book.setAuthorList(author);
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

    public Collection findAllNewBook() {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "SELECT * FROM " + TABLE + " boo "
                + " JOIN Writing wri "
                + " ON wri.booIsbn13=boo.booIsbn13"
                + " JOIN Author aut "
                + " ON aut.autId=wri.autId "
                + " WHERE " + PUBLICATION_YEAR + " > GETDATE() AND " + STATUS + "=0";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
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

    public Collection findBookNewBookByPage(int itemsPerPage, int countFrom) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        StringBuilder query = new StringBuilder();
        query.append("DECLARE @itemsPerPage int, @countFrom int ")
                .append("SET @itemsPerPage = ? ")
                .append("SET @countFrom = ? ")
                .append(" SELECT * FROM " + TABLE + " boo ")
                .append(" JOIN Writing wri ")
                .append(" ON wri.booIsbn13=boo.booIsbn13 ")
                .append(" JOIN Author aut ")
                .append(" ON aut.autId=wri.autId ")
                .append(" WHERE " + PUBLICATION_YEAR + " > GETDATE() AND " + STATUS + "=0")
                .append(" ORDER BY boo.booTitle ASC OFFSET @countFrom ROWS ")
                .append(" FETCH NEXT @itemsPerPage ROWS ONLY");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, itemsPerPage);
            pstmt.setInt(2, countFrom);

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
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

    public Collection findAllNewBookYear() {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "SELECT * FROM " + TABLE + " boo "
                + " JOIN Writing wri "
                + " ON wri.booIsbn13=boo.booIsbn13 "
                + " JOIN Author aut "
                + " ON aut.autId=wri.autId "
                + " WHERE YEAR(booPublishYear) = YEAR(GETDATE()) AND " + PUBLICATION_YEAR + " < GETDATE()";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
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

    public Collection findAllPrice(Float priceMin, Float priceMax) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String priceTTC = "CAST((SELECT booPriceHT*(1+vatRate/100))AS DECIMAL(9,2))";

        String query = "SELECT * FROM " + TABLE + " boo "
                + "JOIN Vat vat ON boo.vatCode = vat.vatCode "
                + " JOIN Writing wri "
                + " ON wri.booIsbn13=boo.booIsbn13 "
                + " JOIN Author aut "
                + " ON aut.autId=wri.autId "
                + "WHERE " + priceTTC + " <= " + priceMax + " AND " + priceTTC + ">=" + priceMin + "AND boo.booStatus=0 "
                + " ORDER BY " + priceTTC;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
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

    public Collection findAllReview(int review) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "SELECT boo.booIsbn13, aut.autFirstName, aut.autLastName, boo.vatCode, boo.booTitle, boo.booPriceHT, boo.booFrontCover "
                + "FROM " + TABLE + " boo JOIN review rev "
                + " ON rev.booIsbn13=boo.booIsbn13 "
                + " JOIN Writing wri "
                + " ON wri.booIsbn13=boo.booIsbn13 "
                + " JOIN Author aut "
                + " ON aut.autId=wri.autId "
                + " WHERE boo.booStatus=0 AND rev.revStatus = " + REVIEW_STATUS_PUBLISHED
                + " GROUP BY boo.booIsbn13, aut.autFirstName, aut.autLastName, boo.vatCode, boo.booTitle, boo.booPriceHT, boo.booFrontCover "
                + " HAVING ROUND(AVG(rev.revNote),0)=" + review;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
                    book.setVatCode(vat);
                    book.setBooTitle(rs.getString(TITLE));
                    book.setBooPriceHT(rs.getFloat(PRICE_HT));
                    book.setBooFrontCover(rs.getString(FRONT_COVER));

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

    // 3 derniers ajouts
    public Collection findAllNews() {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;
        String query = "DECLARE @itemsPerPage int, @countFrom int "
                + " SET @itemsPerPage = 3 "
                + "SET @countFrom = (( "
                + "SELECT COUNT(*) FROM book)-3) "
                + "SELECT * FROM Book b "
                + "ORDER BY b.booTitle ASC OFFSET @countFrom ROWS "
                + "FETCH NEXT @itemsPerPage ROWS ONLY ";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();

                    book.setAuthorList(author);
                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
                    book.setVatCode(vat);
                    book.setBooTitle(rs.getString(TITLE));
                    book.setBooPriceHT(rs.getFloat(PRICE_HT));
                    book.setBooFrontCover(rs.getString(FRONT_COVER));

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

    public Collection findByAuthor(int id) {
        Collection bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "SELECT *, autLastName, autFirstName "
                + " FROM Book boo "
                + " JOIN Writing wri "
                + " ON boo.booIsbn13 = wri.booIsbn13 "
                + " JOIN Author aut "
                + " ON wri.autId = aut.autId "
                + " WHERE aut.autId=" + id;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
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
////////////////////////////////////////NEWS REQUETE CHRYS

    public Vector<Review> findAllReview(String isbn) {
        Vector<Review> listReview = new Vector<Review>();

        String query = "SELECT boo.booIsbn13, rev.revNote, rev.revComment, rev.cusId "
                + "FROM " + TABLE + " boo JOIN review rev "
                + " ON rev.booIsbn13=boo.booIsbn13"
                + " WHERE rev.booIsbn13 = " + isbn
                + " AND rev.revStatus = " + REVIEW_STATUS_PUBLISHED;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    Review r = new Review();
                    Customer cus = new Customer();
                    cus.setCusID(rs.getInt("cusId"));
                    r.setCusId(cus);
                    r.setBooIsbn13(rs.getString(ISBN_13));
                    r.setRevNote(rs.getFloat("revNote"));
                    r.setRevComment(rs.getString("revComment"));
                    System.out.println("///////////////////////////////////////"+r.getCusId().getCusID());
                    listReview.add(r);

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return listReview;
    }

    public Vector<Book> findByColumnSearch(String column, String term) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" LIKE ")
                .append("'%" + term + "%'")
                .append(" AND booStatus=0");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

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

    public Collection findAllNewBookYear(int itemsPerPage, int countFrom) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        StringBuilder query = new StringBuilder();
        query.append("DECLARE @itemsPerPage int, @countFrom int ")
                .append("SET @itemsPerPage = ? ")
                .append("SET @countFrom = ? ")
                .append(" SELECT * FROM " + TABLE + " boo ")
                .append(" JOIN Writing wri ")
                .append(" ON wri.booIsbn13=boo.booIsbn13 ")
                .append(" JOIN Author aut ")
                .append(" ON aut.autId=wri.autId ")
                .append("WHERE YEAR(booPublishYear) = YEAR(GETDATE()) AND " + PUBLICATION_YEAR + " < GETDATE() AND booStatus=0")
                .append(" ORDER BY boo.booTitle ASC OFFSET @countFrom ROWS ")
                .append(" FETCH NEXT @itemsPerPage ROWS ONLY");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, itemsPerPage);
            pstmt.setInt(2, countFrom);

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
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

    public Collection findAllPrice(Float min, Float max, int itemsPerPage, int countFrom) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;
        String priceTTC = "CAST((SELECT booPriceHT*(1+vatRate/100))AS DECIMAL(9,2))";
        String max1 = String.valueOf(max);
        String min1 = String.valueOf(min);

        String query = "DECLARE @itemsPerPage int, @countFrom int  "
                + "SET @itemsPerPage = ? "
                + "SET @countFrom = ? "
                + " SELECT * FROM " + TABLE + " boo "
                + " JOIN Writing wri "
                + " ON wri.booIsbn13=boo.booIsbn13 "
                + " JOIN Author aut "
                + " ON aut.autId=wri.autId "
                + " JOIN Vat vat "
                + " ON boo.vatCode = vat.vatCode "
                + " WHERE  CAST((SELECT boo.booPriceHT*(1+vat.vatRate/100))AS DECIMAL(9,2)) <=" + max1 + "  AND CAST((SELECT boo.booPriceHT*(1+vat.vatRate/100))AS DECIMAL(9,2))>= " + min1 + " and boo.booStatus=0"
                + " ORDER BY boo.booTitle ASC OFFSET @countFrom ROWS "
                + " FETCH NEXT @itemsPerPage ROWS ONLY";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            pstmt.setInt(1, itemsPerPage);
            pstmt.setInt(2, countFrom);

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
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
                    System.out.println("fezqkkkkkkkkkkkkkkkkkkkkkk" + bookList);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return bookList;
    }

    public Collection findAllReviewPagi(int review, int itemsPerPage, int countFrom) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "DECLARE @itemsPerPage int, @countFrom int  "
                + " SET @itemsPerPage = ? "
                + " SET @countFrom = ? "
                + " SELECT boo.booIsbn13, aut.autFirstName, aut.autLastName, boo.vatCode, boo.booSubtitle, boo.booTitle, boo.booPriceHT, boo.booFrontCover, boo.booQuantity, boo.booStatus  "
                + " FROM " + TABLE + " boo JOIN review rev "
                + " ON rev.booIsbn13=boo.booIsbn13 "
                + " JOIN Writing wri "
                + " ON wri.booIsbn13=boo.booIsbn13 "
                + " JOIN Author aut "
                + " ON aut.autId=wri.autId "
                + " WHERE boo.booStatus=0 AND rev.revStatus = " + REVIEW_STATUS_PUBLISHED
                + " GROUP BY boo.booIsbn13, aut.autFirstName, aut.autLastName, boo.vatCode,boo.booSubtitle, boo.booTitle, boo.booPriceHT, boo.booFrontCover, boo.booQuantity,boo.booStatus  "
                + " HAVING ROUND(AVG(rev.revNote),0)=" + review
                + " ORDER BY boo.booTitle ASC OFFSET @countFrom ROWS "
                + " FETCH NEXT @itemsPerPage ROWS ONLY";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            pstmt.setInt(1, itemsPerPage);
            pstmt.setInt(2, countFrom);

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
                    book.setBooIsbn13(rs.getString(ISBN_13));
                    vat.setVatCode(rs.getInt(VAT_CODE));
                    book.setVatCode(vat);
                    book.setBooTitle(rs.getString(TITLE));
                    book.setBooSubtitle(rs.getString(SUBTITLE));
                    book.setBooPriceHT(rs.getFloat(PRICE_HT));
                    book.setBooQuantity(rs.getInt(QUANTITY));
                    book.setBooStatus(rs.getInt(STATUS));
                    book.setBooFrontCover(rs.getString(FRONT_COVER));

                    bookList.add(book);
                    System.out.println("fezqkkkkkkkkkkkkkkkkkkkkkk" + bookList);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return bookList;
    }

    public Vector<Author> findAllAuthors(String isbn) {
        Vector<Author> listAuthor = new Vector<Author>();

        String query = "SELECT boo.booIsbn13, aut.autLastName, aut.autFirstName "
                + "FROM " + TABLE + " boo "
                + " JOIN Writing wri "
                + " ON wri.booIsbn13=boo.booIsbn13 "
                + " JOIN Author aut "
                + " ON aut.autId=wri.autId "
                + " WHERE wri.booIsbn13 =" + isbn;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    Author a = new Author();

                    a.setAutFirstName(rs.getString("autLastName"));
                    a.setAutLastName(rs.getString("autFirstName"));

                    listAuthor.add(a);
                    System.out.println(listAuthor);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return listAuthor;
    }
     	    
///// WILL
    public Book rechercheLivre(String isbn) throws SQLException {

        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ISBN_13)
                .append(" = ?");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.setString(1, isbn);

            ResultSet rs = pstmt.executeQuery();
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
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return book;
    }

    public Collection<Book> rechercheTousLivres() throws SQLException {

        Collection<Book> listBooks = new ArrayList();

        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY " + TITLE;

        try (Connection cnt = mc.getConnection();
                PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();

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

                listBooks.add(book);
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        System.out.println(listBooks);
        return listBooks;

    }

    
    ///////////////////////////////////////////////////WILL///////////////////////////////////////////
    //// By subtheme
    public Collection findBySubTheme(int id) {
        Vector<Book> bookList = new Vector<Book>();
        Book book = null;
        Vat vat = null;
        Editor editor = null;
        BookLanguage language = null;
        Forma format = null;
        Vector<Author> author = null;

        String query = "SELECT * FROM " + TABLE + " boo "
                + " JOIN Writing wri"
                + " ON wri.booIsbn13=boo.booIsbn13"
                + " JOIN Author aut "
                + " ON aut.autId=wri.autId "
                + " JOIN Association ass "
                + " ON boo.booIsbn13=ass.booIsbn13 "
                + " JOIN subTheme sub "
                + " ON sub.subId=ass.subId "
                + " WHERE sub.subId=" + id;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    book = new Book();
                    vat = new Vat();
                    editor = new Editor();
                    language = new BookLanguage();
                    format = new Forma();
                    author = new Vector<Author>();

                    Author a = new Author();
                    a.setAutFirstName(rs.getString("autFirstName"));
                    a.setAutLastName(rs.getString("autLastName"));
                    author.add(a);
                    book.setAuthorList(author);
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
