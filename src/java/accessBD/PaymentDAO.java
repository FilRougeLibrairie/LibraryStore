
package accessBD;


//import names.SQLNames.PaymentNames;
import entity.Payment;
import entity.Purchase;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class PaymentDAO  implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Payment";

    private final String ID = "payId";
    private final String PURCHASE_ID = "purId";
    private final String VALIDATION = "payValidate";
    private final String CHOICE = "payChoice";
    private final String DATE = "payDate";
    private final String CARD_TYPE = "payCardType";
    private final String OWNER_NAME = "payOwnerName";

    private String COLUMNS_CREATE = PURCHASE_ID + ", " + VALIDATION + ", " + CHOICE + ", "
            + DATE + ", " + CARD_TYPE + ", " + OWNER_NAME;

    public PaymentDAO() throws NamingException{

         mc= new MyConnexion();
    }

     
    public void create(Object obj) {
        Payment pay = (Payment) obj;
        String query = "IF NOT EXISTS (SELECT * FROM " + TABLE + " WHERE " + ID + " = '" + pay.getPayId() + "')"
                + "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setInt(1, pay.getPurId().getPurId());
            pstmt.setBoolean(2, pay.getPayValidate());
            pstmt.setString(3, pay.getPayChoice());
            pstmt.setString(4, pay.getPayDate());
            pstmt.setString(5, pay.getPayCardType());
            pstmt.setString(6, pay.getPayOwnerName());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());

        }
    }

     
    public void delete(Object obj) {
        int payId = ((Payment) obj).getPayId();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append(payId);

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
    }

     
    public void update(Object obj) {
        Payment pay = (Payment) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(PURCHASE_ID).append(" = ?, ");
        query.append(VALIDATION).append(" = ?, ");
        query.append(CHOICE).append(" = ?, ");
        query.append(DATE).append(" = ? ");

        query.append("WHERE " + ID + " = ")
                .append(pay.getPayId());

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, pay.getPurId().getPurId());
            pstmt.setBoolean(2, pay.getPayValidate());
            pstmt.setString(3, pay.getPayChoice());
            pstmt.setString(4, pay.getPayDate());
            pstmt.setString(5, pay.getPayCardType());
            pstmt.setString(6, pay.getPayOwnerName());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());

        }
    }

     
    public Payment find(int id) {
        Payment payment = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append(id);

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    payment = new Payment();
                    payment.setPayId(rs.getInt(ID));
                    Purchase pur = new Purchase();
                    pur.setPurId(rs.getInt(PURCHASE_ID));
                    payment.setPurId(pur);
                    payment.setPayValidate(rs.getBoolean(VALIDATION));
                    payment.setPayChoice(rs.getString(CHOICE));
                    payment.setPayDate(rs.getString(ID));
                    payment.setPayCardType(rs.getString(CARD_TYPE));
                    payment.setPayOwnerName(rs.getString(OWNER_NAME));

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return payment;
    }

     
    public Object find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Vector findAll() {
        Vector<Payment> paymentList = new Vector<Payment>();
        Payment payment = null;

        String query = "SELECT * FROM " + TABLE;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    payment = new Payment();
                    payment.setPayId(rs.getInt(ID));
                    Purchase pur = new Purchase();
                    pur.setPurId(rs.getInt(PURCHASE_ID));
                    payment.setPurId(pur);
                    payment.setPayValidate(rs.getBoolean(VALIDATION));
                    payment.setPayChoice(rs.getString(CHOICE));
                    payment.setPayDate(rs.getString(ID));
                    payment.setPayCardType(rs.getString(CARD_TYPE));
                    payment.setPayOwnerName(rs.getString(OWNER_NAME));
                    paymentList.add(payment);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return paymentList;
    }

     
    public Vector<Payment> findByColumn(String column, String term) {

        Vector<Payment> paymentList = new Vector<Payment>();
        Payment payment = null;

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
                    payment = new Payment();
                    payment.setPayId(rs.getInt(ID));
                    Purchase pur = new Purchase();
                    pur.setPurId(rs.getInt(PURCHASE_ID));
                    payment.setPurId(pur);
                    payment.setPayValidate(rs.getBoolean(VALIDATION));
                    payment.setPayChoice(rs.getString(CHOICE));
                    payment.setPayDate(rs.getString(ID));
                    payment.setPayCardType(rs.getString(CARD_TYPE));
                    payment.setPayOwnerName(rs.getString(OWNER_NAME));
                    paymentList.add(payment);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return paymentList;
    }
}
