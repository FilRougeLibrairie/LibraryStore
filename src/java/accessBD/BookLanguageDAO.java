
package accessBD;

import names.SQLNames.BookLanguageNames;
import entity.BookLanguage;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;



public class BookLanguageDAO  implements Serializable{

    private MyConnexion mc;
    private final String TABLE = "BookLanguage";
    private final String CODE = BookLanguageNames.CODE;
    private final String NAME = BookLanguageNames.NAME;
    private final String STATUS = BookLanguageNames.STATUS;

    private String COLUMNS_CREATE =NAME + ", " + STATUS;;

    //Constructor
    public BookLanguageDAO() throws NamingException{
         mc= new MyConnexion();
    }

     
    public void create(BookLanguage obj) {
        BookLanguage bookLang = (BookLanguage) obj;
        String query = "IF NOT EXISTS(SELECT * FROM "+ TABLE +" WHERE "+NAME+"= '"+bookLang.getBooLangName()+"')"
                + "INSERT INTO "+TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setString(1, bookLang.getBooLangName());
            pstmt.setInt(2, bookLang.getBooLangStatus());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
            
        }
    }
    
    public Boolean answer(BookLanguage obj){
        Boolean answer=true;
        BookLanguage bookLang = (BookLanguage) obj;
        String query = "SELECT * FROM bookLanguage WHERE bookLangName= '"+bookLang.getBooLangName()+"'";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {
                answer=true;
            } else {
                answer=false;
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return answer;
        
        
    }   
        
    

     
    public void update(BookLanguage obj) {
        BookLanguage bookLang = (BookLanguage) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(NAME).append(" = ? ");

        query.append("WHERE " + CODE + " = '")
                .append(bookLang.getBooLangCode())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, bookLang.getBooLangName());
            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            
        }
    }

     
    public void delete(BookLanguage obj) {
        int bookLangId = ((BookLanguage) obj).getBooLangCode();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(CODE)
                .append(" = ")
                .append("'" + bookLangId + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
    }

     
    public Vector<BookLanguage> findAll() {
        Vector<BookLanguage> bookLangList = new Vector<BookLanguage>();
        BookLanguage bookLang = null;
        String query = "SELECT * FROM " + TABLE;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    bookLang = new BookLanguage();
                    bookLang.setBooLangCode(rs.getInt(CODE));
                    bookLang.setBooLangName(rs.getString(NAME));
                    bookLangList.add(bookLang);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return bookLangList;
    }

     
    public Vector<BookLanguage> findByColumn(String column, String term) {
        Vector<BookLanguage> bookLangList = new Vector<BookLanguage>();
        BookLanguage bookLang = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "'");

        System.out.println();

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    bookLang = new BookLanguage();
                    bookLang.setBooLangCode(rs.getInt(CODE));
                    bookLang.setBooLangName(rs.getString(NAME));
                    bookLang.setBooLangStatus(rs.getInt(STATUS));
                    bookLangList.add(bookLang);
                    
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return bookLangList;
    }

     
    public BookLanguage find(int id) {
        BookLanguage bookLang = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(CODE)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    bookLang = new BookLanguage();
                    bookLang.setBooLangCode(rs.getInt(BookLanguageNames.CODE));
                    bookLang.setBooLangName(rs.getString(BookLanguageNames.NAME));
                }
            } else {
                throw new SQLException("ResultSet book lang was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return bookLang;
    }

     
    public BookLanguage find(String name) {
        BookLanguage bookLang = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(NAME)
                .append(" = ")
                .append("'" + name + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    bookLang = new BookLanguage();
                    bookLang.setBooLangCode(rs.getInt(CODE));
                    bookLang.setBooLangName(rs.getString(NAME));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return bookLang;
        
        
      
        
    }

}
