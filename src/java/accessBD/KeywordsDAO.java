package accessBD;


//import names.SQLNames.KeywordsNames;
import entity.Keywords;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

public class KeywordsDAO  implements Serializable {

    private MyConnexion mc;
    private final String NAME = "keyName";

    private String COLUMNS = NAME;

    public KeywordsDAO() throws NamingException {
         mc= new MyConnexion();
    }

     
    public void create(Object obj) {
        Keywords kw = (Keywords) obj;
        
        
        String query ="IF NOT EXISTS(SELECT * FROM keywords WHERE keyName= '"+kw.getKeyName()+"')"
                +"INSERT INTO KEYWORDS VALUES ('"+kw.getKeyName()+"')";

     
        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

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

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
          pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Done");
           
        }
    }
    
        public Boolean answer(Keywords obj){
        Boolean answer=true;
        Keywords kw = (Keywords) obj;
        String query = "SELECT * FROM keywords WHERE keyName= '"+kw.getKeyName()+"'";

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
    
    
    
    

     
    public void update(Object obj) {
        Keywords kw = (Keywords) obj;
        StringBuilder query = new StringBuilder("UPDATE KEYWORDS SET ");
        query.append(NAME).append(" = ? ");

        query.append("WHERE " + NAME + " = '")
                .append(kw.getKeyName())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

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

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

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

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

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

        System.out.println();

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

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
        
        
        
}
        
        
        
    


    
