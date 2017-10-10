package accessBD;

import names.SQLNames.DeterminateNames;
import names.SQLNames.OrderStatusNames;
import names.SQLNames.PurchaseNames;
import names.SQLNames.StatusDisplayNames;
import entity.OrderStatus;
import entity.Purchase;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

public class OrderStatusDAO  implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "OrderStatus";

    private final String CODE = OrderStatusNames.CODE;
    private final String NAME = OrderStatusNames.NAME;

    private String COLUMNS_CREATE = CODE + ", " + NAME;

    public OrderStatusDAO() throws NamingException{
         mc= new MyConnexion();
    }

     
    public void create(Object obj) {
        OrderStatus ord = (OrderStatus) obj;
        String query = "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setInt(1, ord.getStaCode());
            pstmt.setString(2, ord.getStaName());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());

        }
    }

    public void createNewPurchaseStatus(Purchase purchase, int orderStatusCode, String dateTime) {
        String query = "INSERT INTO Determinate (purId, staCode, detTime) "
                + "VALUES (?, ?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setInt(1, purchase.getPurId());
            pstmt.setInt(2, orderStatusCode);
            pstmt.setString(3, dateTime);

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());

        }
    }

     
    public void delete(Object obj) {
        int ordCode = ((OrderStatus) obj).getStaCode();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(CODE)
                .append(" = ")
                .append("'" + ordCode + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
    }

     
    public void update(Object obj) {
        OrderStatus ord = (OrderStatus) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(NAME).append(" = ? ");

        query.append("WHERE " + CODE + " = '")
                .append(ord.getStaCode())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, ord.getStaName());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());

        }
    }

     
    public Vector findAll() {
        Vector<OrderStatus> ordList = new Vector<OrderStatus>();
        OrderStatus ord = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY " + StatusDisplayNames.CODE;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    ord = new OrderStatus();

                    ord.setStaCode(rs.getInt(CODE));
                    ord.setStaName(rs.getString(NAME));
                    ordList.add(ord);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return ordList;
    }

     
    public OrderStatus find(int id) {
        OrderStatus ord = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(CODE)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    ord = new OrderStatus();

                    ord.setStaCode(rs.getInt(CODE));
                    ord.setStaName(rs.getString(NAME));

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return ord;
    }

     
    public Object find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Vector findByColumn(String column, String term) {
        Vector<OrderStatus> ordList = new Vector<OrderStatus>();
        OrderStatus ord = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    ord = new OrderStatus();

                    ord.setStaCode(rs.getInt(CODE));
                    ord.setStaName(rs.getString(NAME));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return ordList;
    }

    public Vector<OrderStatus> findOrderStatusByPurchaseId(int purchaseId) {
        Vector<OrderStatus> orderStatusList = new Vector<OrderStatus>();
        OrderStatus orderStatus;
        Purchase pur;
        StringBuffer query = new StringBuffer();
        query.append("DECLARE @purchaseId int ")
                .append("SET @purchaseId = ? ")
                .append("SELECT * ")
                .append("FROM OrderStatus os ")
                .append("JOIN Determinate det ")
                .append("ON os.staCode = det.staCode ")
                .append("JOIN Purchase pur ")
                .append("ON det.purId = pur.purId ")
                .append("WHERE pur.purId = @purchaseId ")
                .append("ORDER BY det.detTime DESC");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, purchaseId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    orderStatus = new OrderStatus();
                    pur = new Purchase();
                    orderStatus.setStaCode(rs.getInt(CODE));
                    orderStatus.setStaName(rs.getString(NAME));
                    pur.setPurId((rs.getInt("purId")));
                    orderStatus.setPurchase(pur);
                    orderStatus.setStatusDate(rs.getString("detTime"));
                    orderStatusList.add(orderStatus);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return orderStatusList;
    }

    public Vector<OrderStatus> findCurrentOrderStatusByPurchaseId(int purchaseId) {
        Vector<OrderStatus> orderStatusList = new Vector<OrderStatus>();
        OrderStatus orderStatus;
        Purchase pur;
        StringBuffer query = new StringBuffer();
        query.append("DECLARE @purchaseId int ")
                .append("SET @purchaseId = ? ")
                .append("SELECT * ")
                .append("FROM OrderStatus os ")
                .append("JOIN Determinate det ")
                .append("ON os.staCode = det.staCode ")
                .append("JOIN Purchase pur ")
                .append("ON det.purId = pur.purId ")
                .append("WHERE pur.purId = @purchaseId ")
                .append("ORDER BY det.detTime DESC");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, purchaseId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    orderStatus = new OrderStatus();
                    pur = new Purchase();
                    orderStatus.setStaCode(rs.getInt(CODE));
                    orderStatus.setStaName(rs.getString(NAME));
                    pur.setPurId((rs.getInt("purId")));
                    orderStatus.setPurchase(pur);
                    orderStatus.setStatusDate(rs.getString("detTime"));
                    orderStatusList.add(orderStatus);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return orderStatusList;
    }

    public Vector<OrderStatus> findAllOrderStatus(int purchaseId) {
        Vector<OrderStatus> osList = new Vector<OrderStatus>();
        OrderStatus os;
        Purchase pur;
        StringBuffer query = new StringBuffer();
        query.append("SELECT pur.purId, ord.staCode, ord.staName, det.detTime ")
                .append("FROM OrderStatus ord ")
                .append("JOIN Determinate det ")
                .append("ON ord.staCode = det.staCode ")
                .append("JOIN Purchase pur ")
                .append("ON det.purId = pur.purId ")
                .append("WHERE ")
                .append("pur." + PurchaseNames.ID)
                .append(" = ")
                .append("?");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, purchaseId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    os = new OrderStatus();
                    pur = new Purchase();
                    os.setPurchase(pur);
                    os.setStaCode(rs.getInt(OrderStatusNames.CODE));
                    os.setStaName(rs.getString(OrderStatusNames.NAME));
                    os.setStatusDate(rs.getString(DeterminateNames.DATE_TIME));
                    osList.add(os);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return osList;

    }

}
