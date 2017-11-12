
package accessBD;


import entity.Theme;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;


public class ThemeDAO  implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Theme";

    public final String ID = "theId";
    public final String NAME = "theName";
    public final String DESCRIPTION = "theDescription";
    public final String STATUS = "theStatus";
    
    
    private String COLUMNS_CREATE = NAME + ", " + DESCRIPTION;

    public ThemeDAO() throws NamingException{
         mc= new MyConnexion();
    }

     
    public void create(Object obj) {
        Theme the = (Theme) obj;
        String query = "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setString(1, the.getTheName());
            pstmt.setString(2, the.getTheDescription());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
            
        }
    }

    
    
    
    public Boolean answer(Theme obj){
        Boolean answer=true;
        Theme theme = (Theme) obj;
        String query = "SELECT * FROM theme WHERE theName= '"+theme.getTheName()+"'";

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
     
    public void delete(Object obj) {
        int theId = ((Theme) obj).getTheId();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + theId + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
    }

     
    public void update(Object obj) {
        Theme the = (Theme) obj;
        StringBuffer query = new StringBuffer("UPDATE " + TABLE + " SET ");
        query.append(NAME).append(" =?, ");
        query.append(DESCRIPTION).append(" =? ");

        query.append("WHERE " + ID + " = '")
                .append(the.getTheId())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.setString(1, the.getTheName());
            pstmt.setString(2, the.getTheDescription());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            
        }
    }

    
     public void updateNoTheme(Object obj) {
        Theme the = (Theme) obj;
        StringBuffer query = new StringBuffer("UPDATE " + TABLE + " SET ");
        query.append(ID).append(" =? ");

        query.append("WHERE " + ID + " = ")
                .append(the.getTheId())
                .append("");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.setInt(1, 0);
            

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            
        }
    }
    
  
  
    
    public Theme findByIsbn (String isbn){
        //Vector <Theme> vecThemeList = new Vector <Theme>();
        Theme theme = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT the.theID, theName, theDescription ")
                .append("FROM Theme the ")
                .append("JOIN SubTheme sub ")
                .append("ON the.theId = sub.theId ")
                .append("JOIN Association ass ")
                .append("ON sub.subId = ass.subId ")
                .append("WHERE ass.booIsbn13 ")
                .append(" = ")
                .append("'" + isbn + "'");
                

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    theme= new Theme();
                    theme.setTheId(rs.getInt(ID));
                    theme.setTheName(rs.getString(NAME));
                    theme.setTheDescription(rs.getString(DESCRIPTION));
                    //vecThemeList.add(theme);
                }
            } else {
                throw new SQLException("ERROR SQL Theme Object");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Theme Object : " + ex.getMessage());
            

        }
        return theme;
        
        
    }
       
    public Vector findAll() {
        Vector<Theme> themeList = new Vector<Theme>();
        Theme theme = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY "+NAME+"";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    
                    
                    themeList.add(new Theme(rs.getInt(ID), rs.getString(NAME), rs.getString(DESCRIPTION), rs.getInt(STATUS)));
  
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
        return themeList;
    }
    
    
    
    
    
    public Vector findAllOrdId() {
        Vector<Theme> themeList = new Vector<Theme>();
        Theme theme = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY "+ ID+"";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    
                    
                    themeList.add(new Theme(rs.getInt(ID), rs.getString(NAME), rs.getString(DESCRIPTION), rs.getInt(STATUS)));
  
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
        return themeList;
    }
    
    
    
    
    
    
    
    
    
    

     
    public Theme find(int id) {
        Theme theme = new Theme();
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("" + id + "");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    theme.setTheId(rs.getInt(ID));
                    theme.setTheName(rs.getString(NAME));
                    theme.setTheDescription(rs.getString(DESCRIPTION));
                    theme.setStatus(rs.getInt((STATUS)));
                    
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
        return theme;
    }

    
    
    
    
    
    
     
    public Object find(String name) {
    
       
        Theme theme = new Theme();

        String query = "SELECT * FROM " + TABLE + " WHERE " + NAME + " = '"+ name +"'" ;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    theme.setTheId(rs.getInt(ID));
                    theme.setTheName(rs.getString(NAME));
                    theme.setTheDescription(rs.getString(DESCRIPTION));
                }
                
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
        return theme;
    }
    
    
    
     
    public Vector<Theme> findByColumn(String column, String term) {

        Vector<Theme> themeList = new Vector<Theme>();
        Theme theme;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append( column )
                .append(" = ")
                .append("'" + term + "'");

        System.out.println();

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    theme = new Theme();
                    theme.setTheId(rs.getInt(ID));
                    theme.setTheName(rs.getString(NAME));
                    theme.setTheDescription(rs.getString(DESCRIPTION));
                    theme.setStatus(rs.getInt(STATUS));
                    themeList.add(theme);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
        return themeList;
    }
    
    
    
    
    public Vector<Theme> findByName(String column, String term) {

        Vector<Theme> themeList = new Vector<Theme>();
        Theme theme;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append( column )
                .append(" = ")
                .append("'" + term + "'");

        System.out.println();

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    theme = new Theme();
                    theme.setTheId(rs.getInt(ID));
                    theme.setTheName(rs.getString(NAME));
                    theme.setTheDescription(rs.getString(DESCRIPTION));
                    themeList.add(theme);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
        return themeList;
    }
    
    
    
    
    
    
    
    public Vector<Theme> findByColumn(String column, int term) {

        Vector<Theme> themeList = new Vector<Theme>();
        Theme theme = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + "WHERE ")
                .append( column )
                .append(" = ")
                .append(" "+ term +" ");

        System.out.println();

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    theme = new Theme();
                    theme.setTheId(rs.getInt(ID));
                    theme.setTheName(rs.getString(NAME));
                    theme.setTheDescription(rs.getString(DESCRIPTION));
                    themeList.add(theme);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
        return themeList;
    }
    
    public List<Theme> listeDesThemes() throws SQLException {
        List<Theme> listeThemes = new ArrayList();

        Theme theme = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY " + NAME;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                theme = new Theme();
                theme.setTheId(rs.getInt(ID));
                theme.setTheName(rs.getString(NAME));
                theme.setTheDescription(rs.getString(DESCRIPTION));
                theme.setStatus(rs.getInt(STATUS));
                listeThemes.add(theme);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return listeThemes;
    }
}
