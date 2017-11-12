package accessBD;

import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Serializable;
import java.sql.Connection;
import javax.naming.NamingException;

public class CustomerDAO implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Customer";

    private final String ID = "cusId";
    private final String GENDER = "cusGender";
    private final String FIRST_NAME = "cusFirstName";
    private final String LAST_NAME = "cusLastName";
    private final String COMPANY = "cusOrganisationName";
    private final String EMAIL = "cusEmail";
    private final String PHONE = "cusPhoneNumber";
    private final String BIRTHDAY = "cusDateOfBirth";
    private final String PASSWORD = "cusPassword";
    private final String SALT = "cusSalt";
    private final String IP = "cusIP";
    private final String STATUS = "cusStatus";
    private final String COMMENT = "cusComment";
    private final String CLEARPASSWORD = "cusClearPassword";

    private String COLUMNS_CREATE = GENDER + ", " + FIRST_NAME + ", " + LAST_NAME + ", "
            + COMPANY + ", " + EMAIL + ", " + PHONE + ", " + BIRTHDAY + ", "
            + PASSWORD + ", " + SALT + ", " + IP + ", " + STATUS + ", " + COMMENT + ", " + CLEARPASSWORD;

    public CustomerDAO() throws NamingException {
        mc = new MyConnexion();
    }

    public void create(Object obj) {
        Customer cus = (Customer) obj;
        String query = "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setString(1, cus.getCusGender());
            pstmt.setString(2, cus.getCusFirstName());
            pstmt.setString(3, cus.getCusLastName());
            pstmt.setString(4, cus.getCusOrganisationName());
            pstmt.setString(5, cus.getCusEmail());
            pstmt.setString(6, cus.getCusPhoneNumber());
            pstmt.setString(7, cus.getCusDateOfBirth());
            pstmt.setString(8, cus.getCusPassword());
            pstmt.setString(9, cus.getCusSalt());
            pstmt.setString(10, cus.getCusIP());
            pstmt.setInt(11, cus.getCusStatus());
            pstmt.setString(12, cus.getCusComment());
            pstmt.setString(13, cus.getCusClearPassword());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
        }
    }
    
     public void createJsp(Customer cus) {
      
        String query = "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setString(1, cus.getCusGender());
            pstmt.setString(2, cus.getCusFirstName());
            pstmt.setString(3, cus.getCusLastName());
            pstmt.setString(4, cus.getCusOrganisationName());
            pstmt.setString(5, cus.getCusEmail());
            pstmt.setString(6, cus.getCusPhoneNumber());
            pstmt.setString(7, cus.getCusDateOfBirth().toString());
            pstmt.setInt(8,cus.getCusStatus());
            pstmt.setString(9, cus.getCusClearPassword());
         
            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
        }
    }


    public void delete(Object obj) {
        int cusId = ((Customer) obj).getCusID();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + cusId + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
    }

    public void update(Object obj) {
        Customer cus = (Customer) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(FIRST_NAME).append(" = ?, ");
        query.append(LAST_NAME).append(" = ?, ");
        query.append(EMAIL).append(" = ?, ");
        query.append(PHONE).append(" = ?, ");
        query.append(GENDER).append(" = ?, ");
        query.append(BIRTHDAY).append(" = ?, ");
        query.append(COMPANY).append(" = ?, ");
        query.append(PASSWORD).append(" = ?, ");
        query.append(SALT).append(" = ?, ");
        query.append(STATUS).append(" = ?, ");
        query.append(IP).append(" = ?, ");
        query.append(COMMENT).append(" = ? ");

        query.append("WHERE " + ID + " = '")
                .append(cus.getCusID())
                .append("'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, cus.getCusFirstName());
            pstmt.setString(2, cus.getCusLastName());
            pstmt.setString(3, cus.getCusEmail());
            pstmt.setString(4, cus.getCusPhoneNumber());
            pstmt.setString(5, cus.getCusGender());
            pstmt.setString(6, cus.getCusDateOfBirth());
            pstmt.setString(7, cus.getCusOrganisationName());
            pstmt.setString(8, cus.getCusPassword());
            pstmt.setString(9, cus.getCusSalt());
            pstmt.setInt(10, cus.getCusStatus());
            pstmt.setString(11, cus.getCusIP());
            pstmt.setString(12, cus.getCusComment());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());

        }
    }

    public Customer find(int id) {
        Customer customer = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ?");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    customer = new Customer();
                    customer.setCusID(rs.getInt(ID));
                    customer.setCusGender(rs.getString(GENDER));
                    customer.setCusFirstName(rs.getString(FIRST_NAME));
                    customer.setCusLastName(rs.getString(LAST_NAME));
                    customer.setCusOrganisationName(rs.getString(COMPANY));
                    customer.setCusEmail(rs.getString(EMAIL));
                    customer.setCusPhoneNumber(rs.getString(PHONE));
                    customer.setCusDateOfBirth(rs.getString(BIRTHDAY));
                    customer.setCusPassword(rs.getString(PASSWORD));
                    customer.setCusSalt(rs.getString(SALT));
                    customer.setCusIP(rs.getString(IP));
                    customer.setCusStatus(rs.getInt(STATUS));
                    customer.setCusComment(rs.getString(COMMENT));
                    customer.setCusComment(rs.getString(CLEARPASSWORD));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public Customer find(String email) {
        Customer customer = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(EMAIL)
                .append(" = ?");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    customer = new Customer();
                    customer.setCusID(rs.getInt(ID));
                    customer.setCusGender(rs.getString(GENDER));
                    customer.setCusFirstName(rs.getString(FIRST_NAME));
                    customer.setCusLastName(rs.getString(LAST_NAME));
                    customer.setCusOrganisationName(rs.getString(COMPANY));
                    customer.setCusEmail(rs.getString(EMAIL));
                    customer.setCusPhoneNumber(rs.getString(PHONE));
                    customer.setCusDateOfBirth(rs.getString(BIRTHDAY));
                    customer.setCusPassword(rs.getString(PASSWORD));
                    customer.setCusSalt(rs.getString(SALT));
                    customer.setCusIP(rs.getString(IP));
                    customer.setCusStatus(rs.getInt(STATUS));
                    customer.setCusComment(rs.getString(COMMENT));
                    customer.setCusClearPassword(rs.getString(CLEARPASSWORD));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;

    }

    public Vector findAll() {
        Vector<Customer> customerList = new Vector<Customer>();
        Customer customer = null;

        String query = "SELECT * FROM " + TABLE + " ORDER BY " + LAST_NAME;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    customer = new Customer();
                    customer.setCusID(rs.getInt(ID));
                    customer.setCusGender(rs.getString(GENDER));
                    customer.setCusFirstName(rs.getString(FIRST_NAME));
                    customer.setCusLastName(rs.getString(LAST_NAME));
                    customer.setCusOrganisationName(rs.getString(COMPANY));
                    customer.setCusEmail(rs.getString(EMAIL));
                    customer.setCusPhoneNumber(rs.getString(PHONE));
                    customer.setCusDateOfBirth(rs.getString(BIRTHDAY));
                    customer.setCusPassword(rs.getString(PASSWORD));
                    customer.setCusSalt(rs.getString(SALT));
                    customer.setCusIP(rs.getString(IP));
                    customer.setCusStatus(rs.getInt(STATUS));
                    customer.setCusComment(rs.getString(COMMENT));
                    customerList.add(customer);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerList;
    }

    public Vector<Customer> findByColumn(String column, String term) {

        Vector<Customer> customerList = new Vector<Customer>();
        Customer customer = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" LIKE ")
                .append("'" + term + "%'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    customer = new Customer();
                    customer.setCusID(rs.getInt(ID));
                    customer.setCusGender(rs.getString(GENDER));
                    customer.setCusFirstName(rs.getString(FIRST_NAME));
                    customer.setCusLastName(rs.getString(LAST_NAME));
                    customer.setCusOrganisationName(rs.getString(COMPANY));
                    customer.setCusEmail(rs.getString(EMAIL));
                    customer.setCusPhoneNumber(rs.getString(PHONE));
                    customer.setCusDateOfBirth(rs.getString(BIRTHDAY));
                    customer.setCusPassword(rs.getString(PASSWORD));
                    customer.setCusSalt(rs.getString(SALT));
                    customer.setCusIP(rs.getString(IP));
                    customer.setCusStatus(rs.getInt(STATUS));
                    customer.setCusComment(rs.getString(COMMENT));
                    customerList.add(customer);
                }
            } else {
                throw new Exception("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerList;
    }

    public Vector<Customer> findByColumn(String column, int term) throws Exception {

        Vector<Customer> customerList = new Vector<Customer>();
        Customer customer = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ?");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.setInt(1, term);
            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    customer = new Customer();
                    customer.setCusID(rs.getInt(ID));
                    customer.setCusGender(rs.getString(GENDER));
                    customer.setCusFirstName(rs.getString(FIRST_NAME));
                    customer.setCusLastName(rs.getString(LAST_NAME));
                    customer.setCusOrganisationName(rs.getString(COMPANY));
                    customer.setCusEmail(rs.getString(EMAIL));
                    customer.setCusPhoneNumber(rs.getString(PHONE));
                    customer.setCusDateOfBirth(rs.getString(BIRTHDAY));
                    customer.setCusPassword(rs.getString(PASSWORD));
                    customer.setCusSalt(rs.getString(SALT));
                    customer.setCusIP(rs.getString(IP));
                    customer.setCusStatus(rs.getInt(STATUS));
                    customer.setCusComment(rs.getString(COMMENT));
                    customerList.add(customer);
                }
            } else {
                throw new Exception("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return customerList;
    }

}
