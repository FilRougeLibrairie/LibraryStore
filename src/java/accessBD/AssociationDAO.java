
package accessBD;

import entity.Association;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class AssociationDAO implements Serializable{
    
    private MyConnexion mc;
    private final String TABLE = "Association";
    private final String ID = "booIsbn13";
    private final String SUBID = "subId";

    private String COLUMNS_CREATE =  ID + ", " + SUBID;
    
    
    
    //Constructor
    public AssociationDAO() throws NamingException  {
         mc= new MyConnexion();
    }
    
    
     
    public void create(Association obj) {
        Association asso = (Association) obj;
        StringBuilder query = new StringBuilder("INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?)");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, asso.getBooIsbn13());
            pstmt.setInt(2, asso.getSubId());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR Create Association Object : " + ex.getMessage());

        } }

     
    public void delete(Association obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public void update(Association obj) {
       
        Association assoc = (Association) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(SUBID).append(" = ? ");

        query.append("WHERE " + SUBID + " = '"+assoc.getSubId()+"'");
                

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, 38);

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            

        }
    
    
    }

     
    public Vector<Association> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Association find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Association find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Vector<Association> findByColumn(String column, String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
