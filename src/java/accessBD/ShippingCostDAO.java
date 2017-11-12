
package accessBD;



import entity.ShippingCost;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class ShippingCostDAO  implements Serializable {

    private MyConnexion mc;
     
   
    public ShippingCostDAO() throws NamingException{
         mc= new MyConnexion();
    }

     
    public void create(ShippingCost obj) {
        
        String COLUMNS_CREATE = "shipId,shipName,shipCost";
        String TABLE = "ShippingCost";
        String ID = "shipId";
        
        ShippingCost shipcost = (ShippingCost) obj;
        String query = "IF NOT EXISTS (SELECT * FROM " + TABLE + " WHERE " + ID + " = '" + shipcost.getShipId() + "')"
                + "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setString(1, shipcost.getShipName());
            pstmt.setFloat(2, shipcost.getShipCost());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
            
        }
    }

     
    public void update(ShippingCost obj) {
        String TABLE = "ShippingCost";
        String ID = "shipId";
        String NAME = "shipName";
        String COST = "shipCost";
        
        ShippingCost shipcost = (ShippingCost) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(NAME).append(" = ?, ");
        query.append(COST).append(" = ? ");

        query.append("WHERE " + ID + " = '")
                .append(shipcost.getShipId())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, shipcost.getShipName());
            pstmt.setFloat(2, shipcost.getShipCost());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            

        }
    }

     
    

     
    public Vector<ShippingCost> findAll() {
        String TABLE = "ShippingCost";
        String ID = "shipId";
        String NAME = "shipName";
        String COST = "shipCost";
        
        
        Vector<ShippingCost> ShippingCostList = new Vector<ShippingCost>();
        ShippingCost shipcost = null;
        String query = "SELECT * FROM " + TABLE + " ORDER BY " + "shipCost";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    shipcost = new ShippingCost();
                    shipcost.setShipId(rs.getInt(ID));
                    shipcost.setShipName(rs.getString(NAME));
                    shipcost.setShipCost(rs.getFloat(COST));
                    ShippingCostList.add(shipcost);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return ShippingCostList;
    }

     
    public Vector<ShippingCost> findByColumn(String column, String term) {
        
        String TABLE = "ShippingCost";
        String ID = "shipId";
        String NAME = "shipName";
        String COST = "shipCost";
        
        
        Vector<ShippingCost> ShippingCostList = new Vector<ShippingCost>();
        ShippingCost shipcost = null;
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
                    shipcost = new ShippingCost();
                    shipcost.setShipId(rs.getInt(ID));
                    shipcost.setShipName(rs.getString(NAME));
                    shipcost.setShipCost(rs.getFloat(COST));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return ShippingCostList;
    }

     
    public ShippingCost find(int id) {
        
        String TABLE = "ShippingCost";
        String ID = "shipId";
        String NAME = "shipName";
        String COST = "shipCost";
        
        ShippingCost shipcost = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    shipcost = new ShippingCost();
                    shipcost.setShipId(rs.getInt(ID));
                    shipcost.setShipName(rs.getString(NAME));
                    shipcost.setShipCost(rs.getFloat(COST));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return shipcost;
    }

     
    public ShippingCost find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
