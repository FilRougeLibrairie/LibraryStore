package accessBD;


import names.SQLNames.AuthorNames;
import names.SQLNames.BookNames;
import entity.Author;
import entity.Book;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

public class AuthorDAO  implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Author";
    private final String ID = AuthorNames.ID;
    private final String LAST_NAME = AuthorNames.LAST_NAME;
    private final String FIRST_NAME = AuthorNames.FIRST_NAME;
    private final String BIOGRAPHY = AuthorNames.BIOGRAPHY;
    private final String STATUS_CODE = AuthorNames.STATUS_CODE;

    private String COLUMNS_CREATE = LAST_NAME + ", " + FIRST_NAME + ", " + BIOGRAPHY + ", "
            + STATUS_CODE;

    public AuthorDAO() throws NamingException {
         mc= new MyConnexion();
    }

     
    public void create(Object obj) {
        Author aut = (Author) obj;
        String query
                = "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            pstmt.setString(1, aut.getAutLastName());
            pstmt.setString(2, aut.getAutFirstName());
            pstmt.setString(3, aut.getAutBiography());
            pstmt.setInt(4, aut.getAutStatusCode());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());

        }
    }

     
    public void delete(Object obj) {
        int autId = ((Author) obj).getAutId();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + autId + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
    }

    public Boolean answer(Author obj) {
        Boolean answer = true;
        Author a = (Author) obj;
        String query = "SELECT * FROM author WHERE autLastName= '" + a.getAutLastName() + "' AND autFirstName ='" + a.getAutFirstName() + "'";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

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
        Author aut = (Author) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");

        query.append(LAST_NAME).append(" = ?, ");
        query.append(FIRST_NAME).append(" = ?, ");
        query.append(BIOGRAPHY).append(" = ?, ");
        query.append(STATUS_CODE).append(" = ? ");

        query.append("WHERE " + ID + " = '")
                .append(aut.getAutId())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, aut.getAutLastName());
            pstmt.setString(2, aut.getAutFirstName());
            pstmt.setString(3, aut.getAutBiography());
            pstmt.setInt(4, aut.getAutStatusCode());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());

        }
    }

     
    public Author find(int id) {
        Author author = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(ID));
                    author.setAutLastName(rs.getString(LAST_NAME));
                    author.setAutFirstName(rs.getString(FIRST_NAME));
                    author.setAutBiography(rs.getString(BIOGRAPHY));
                    author.setAutStatusCode(rs.getInt(STATUS_CODE));

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return author;
    }

    public Vector<String> findBooksByAuthor(String authorName) {
        Vector<String> isbnList = new Vector<String>();
        String isbn = null;
        StringBuffer query = new StringBuffer();

        query.append("DECLARE @authorName varchar(50) ")
                .append("SET @authorName = ? ")
                .append("SELECT book.booIsbn13 ")
                .append("FROM Author aut ")
                .append("JOIN Writing wri ")
                .append("ON aut.autId = wri.autId ")
                .append("JOIN Book book ")
                .append("ON wri.booIsbn13 = book.booIsbn13 ")
                .append("WHERE aut.autLastName LIKE @authorName + '%' ")
                .append("OR aut.autFirstName LIKE @authorName + '%' ");
       

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

             pstmt.setString(1, authorName);
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    isbn = rs.getString(BookNames.ISBN_13);
                    isbnList.add(isbn);
                }
            } else {
                throw new SQLException("");
            }

        } catch (SQLException ex) {
            //  System.out.println("ERROR Retrieving Author Object : " + ex.getMessage());

        }
        return isbnList;

    }

    public Vector<Author> findAuthorByBook(String isbn) {
        Vector<Author> vecAuthorList = new Vector<Author>();
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
                    vecAuthorList.add(author);
                }
            } else {
                throw new SQLException("");
            }

        } catch (SQLException ex) {
            //  System.out.println("ERROR Retrieving Author Object : " + ex.getMessage());

        }
        return vecAuthorList;

    }

     
    public Author find(String name) {

        Author author = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + name + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(ID));
                    author.setAutLastName(rs.getString(LAST_NAME));
                    author.setAutFirstName(rs.getString(FIRST_NAME));
                    author.setAutBiography(rs.getString(BIOGRAPHY));
                    author.setAutStatusCode(rs.getInt(STATUS_CODE));

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return author;

    }

     
    public Vector findAll() {
        Vector<Author> authorList = new Vector<Author>();
        Author author = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY " + LAST_NAME;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(ID));
                    author.setAutLastName(rs.getString(LAST_NAME));
                    author.setAutFirstName(rs.getString(FIRST_NAME));
                    author.setAutBiography(rs.getString(BIOGRAPHY));
                    author.setAutStatusCode(rs.getInt(STATUS_CODE));

                    authorList.add(author);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }

        return authorList;
    }

    
    
    
    
     public Vector findAllWithoutInactif() {
        Vector<Author> authorList = new Vector<Author>();
        Author author = null;

        String query = "SELECT * FROM " + TABLE+ " WHERE " +STATUS_CODE+ " = 0 ORDER BY "+LAST_NAME;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(ID));
                    author.setAutLastName(rs.getString(LAST_NAME));
                    author.setAutFirstName(rs.getString(FIRST_NAME));
                    author.setAutBiography(rs.getString(BIOGRAPHY));
                    author.setAutStatusCode(rs.getInt(STATUS_CODE));
                    
                    
                    authorList.add(author);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        
        return authorList;
    }

    
    
    
    
    
    
    
    
    
    
      public Vector findAllOrderID() {
        Vector<Author> authorList = new Vector<Author>();
        Author author = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY " + ID;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(ID));
                    author.setAutLastName(rs.getString(LAST_NAME));
                    author.setAutFirstName(rs.getString(FIRST_NAME));
                    author.setAutBiography(rs.getString(BIOGRAPHY));
                    author.setAutStatusCode(rs.getInt(STATUS_CODE));

                    authorList.add(author);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }

        return authorList;
    }

     
    public Vector<Author> findByColumn(String column, String term) {

        Vector<Author> authorList = new Vector<Author>();
        Author author = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "' ORDER BY autId");

        System.out.println();

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(ID));
                    author.setAutLastName(rs.getString(LAST_NAME));
                    author.setAutFirstName(rs.getString(FIRST_NAME));
                    author.setAutBiography(rs.getString(BIOGRAPHY));
                    author.setAutStatusCode(rs.getInt(STATUS_CODE));
                    authorList.add(author);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return authorList;
    }

    public Vector<Author> findByColumnName(String column, String term) {

        Vector<Author> authorList = new Vector<Author>();
        Author author = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "' ORDER BY autLastName");

        System.out.println();

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(ID));
                    author.setAutLastName(rs.getString(LAST_NAME));
                    author.setAutFirstName(rs.getString(FIRST_NAME));
                    author.setAutBiography(rs.getString(BIOGRAPHY));
                    author.setAutStatusCode(rs.getInt(STATUS_CODE));
                    authorList.add(author);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return authorList;
    }
    
    
    public Vector<Author> findByColumnNameAndSurname(String column, String term, String column2, String term2) {

        Vector<Author> authorList = new Vector<Author>();
        Author author = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "' AND ")
                .append(column2)
                .append(" = ")
                .append("'" + term2 + "'")
                ;

        System.out.println();

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    author = new Author();
                    author.setAutId(rs.getInt(ID));
                    author.setAutLastName(rs.getString(LAST_NAME));
                    author.setAutFirstName(rs.getString(FIRST_NAME));  
                    author.setAutBiography(rs.getString(BIOGRAPHY));
                    author.setAutStatusCode(rs.getInt(STATUS_CODE));
                    authorList.add(author);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return authorList;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
