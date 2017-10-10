package accessBD;


import entity.Having;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class WritingDAO  implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Writing";
    private final String ID = "booIsbn13";
    private final String AUT = "autId";

    private String COLUMNS_CREATE = ID + ", " + AUT;

     
    public void create(Having obj) {
        Having wri = (Having) obj;
        StringBuilder query = new StringBuilder("INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?)");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, wri.getBooIsbn13());
            pstmt.setInt(2, wri.getAutId());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR Create Writing Object : " + ex.getMessage());

        }
    }

     
    public void delete(Having obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public void update(Having obj) {

        Having writing = (Having) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(AUT).append(" = ? ");

        query.append("WHERE " + AUT + " = '")
                .append(writing.getAutId())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, "1");

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());

        }

    }

     
    public Vector<Having> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Having find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Having find(String name) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

     
    public Vector<Having> findByColumn(String column, String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
