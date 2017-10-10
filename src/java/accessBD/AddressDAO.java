
package accessBD;


import names.SQLNames.AddressNames;
import entity.Address;
import entity.Customer;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class AddressDAO implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Address";

    private final String ID = AddressNames.ID;
    private final String CUST_RESIDENCE_ID = AddressNames.CUST_RESIDENCE_ID;
    private final String CUST_CHARGE_ID = AddressNames.CUST_CHARGE_ID;
    private final String LABEL = AddressNames.LABEL;
    private final String FIRST_NAME = AddressNames.FIRST_NAME;
    private final String LAST_NAME = AddressNames.LAST_NAME;
    private final String COMPANY = AddressNames.COMPANY;
    private final String STREET_NUMBER = AddressNames.STREET_NUMBER;
    private final String STREET_TYPE = AddressNames.STREET_TYPE;
    private final String STREET_NAME = AddressNames.STREET_NAME;
    private final String COMPLEMENT = AddressNames.COMPLEMENT;
    private final String ZIP_CODE = AddressNames.ZIP_CODE;
    private final String CITY = AddressNames.CITY;
    private final String SECURITY_CODE = AddressNames.SECURITY_CODE;
    private final String PHONE = AddressNames.PHONE;

    private String COLUMNS_CREATE = CUST_RESIDENCE_ID + ", " + CUST_CHARGE_ID + ", "
            + LABEL + ", " + FIRST_NAME + ", " + LAST_NAME + ", " + COMPANY + ", "
            + STREET_NUMBER + ", " + STREET_TYPE + ", " + STREET_NAME + ", "
            + COMPLEMENT + ", " + ZIP_CODE + ", " + CITY + ", "
            + SECURITY_CODE + ", " + PHONE;

    public AddressDAO() throws NamingException {
         mc= new MyConnexion();
    }

 
    public void create(Object obj) {
        Address addr = (Address) obj;
        String query = "IF NOT EXISTS (SELECT * FROM " + TABLE + " WHERE " + ID + " = '" + addr.getAddId() + "')"
                + "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            pstmt.setInt(1, addr.getCusResidId().getCusID());
            pstmt.setInt(2, addr.getCusChargeId().getCusID());
            pstmt.setString(3, addr.getAddLabel());
            pstmt.setString(4, addr.getAddFirstName());
            pstmt.setString(5, addr.getAddLastName());
            pstmt.setString(6, addr.getAddCompany());
            pstmt.setString(7, addr.getAddNumber());
            pstmt.setString(8, addr.getAddStreetType());
            pstmt.setString(9, addr.getAddStreetName());
            pstmt.setString(10, addr.getAddComplement());
            pstmt.setString(11, addr.getAddZipCode());
            pstmt.setString(12, addr.getAddCity());
            pstmt.setString(13, addr.getAddSecurityCode());
            pstmt.setString(14, addr.getAddPhone());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
        }
    }

    
    public void delete(Object obj) {
        int addrId = ((Address) obj).getAddId();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + addrId + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
    }

    
    public void update(Object obj) {
        Address addr = (Address) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(CUST_RESIDENCE_ID).append(" = ?, ");
        query.append(CUST_CHARGE_ID).append(" = ?, ");
        query.append(LABEL).append(" = ?, ");
        query.append(FIRST_NAME).append(" = ?, ");
        query.append(LAST_NAME).append(" = ?, ");
        query.append(COMPANY).append(" = ?, ");
        query.append(STREET_NUMBER).append(" = ?, ");
        query.append(STREET_TYPE).append(" = ?, ");
        query.append(STREET_NAME).append(" = ?, ");
        query.append(COMPLEMENT).append(" = ?, ");
        query.append(ZIP_CODE).append(" = ?, ");
        query.append(CITY).append(" = ?, ");
        query.append(SECURITY_CODE).append(" = ?, ");
        query.append(PHONE).append(" = ? ");

        query.append("WHERE " + ID + " = '")
                .append(addr.getAddId())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, addr.getCusResidId().getCusID());
            pstmt.setInt(2, addr.getCusChargeId().getCusID());
            pstmt.setString(3, addr.getAddLabel());
            pstmt.setString(4, addr.getAddFirstName());
            pstmt.setString(5, addr.getAddLastName());
            pstmt.setString(6, addr.getAddCompany());
            pstmt.setString(7, addr.getAddNumber());
            pstmt.setString(8, addr.getAddStreetType());
            pstmt.setString(9, addr.getAddStreetName());
            pstmt.setString(10, addr.getAddComplement());
            pstmt.setString(11, addr.getAddZipCode());
            pstmt.setString(12, addr.getAddCity());
            pstmt.setString(13, addr.getAddSecurityCode());
            pstmt.setString(14, addr.getAddPhone());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
        }

    }

     
    public Vector findAll() {
        Vector<Address> addressList = new Vector<Address>();
        Address address = null;
        Customer cus = null;

        String query = "SELECT * FROM " + TABLE;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    address = new Address();
                    cus = new Customer();

                    address.setAddId(rs.getInt(ID));
                    cus.setCusID(rs.getInt(CUST_RESIDENCE_ID));
                    address.setCusResidId(cus);
                    address.setCusChargeId(cus);
                    address.setAddLabel(rs.getString(LABEL));
                    address.setAddFirstName(rs.getString(FIRST_NAME));
                    address.setAddLastName(rs.getString(LAST_NAME));
                    address.setAddCompany(rs.getString(COMPANY));
                    address.setAddNumber(rs.getString(STREET_NUMBER));
                    address.setAddStreetType(rs.getString(STREET_TYPE));
                    address.setAddStreetName(rs.getString(STREET_NAME));
                    address.setAddComplement(rs.getString(COMPLEMENT));
                    address.setAddZipCode(rs.getString(ZIP_CODE));
                    address.setAddCity(rs.getString(CITY));
                    address.setAddSecurityCode(rs.getString(SECURITY_CODE));
                    address.setAddPhone(rs.getString(PHONE));
                    addressList.add(address);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return addressList;
    }

     
    public Address find(int id) {
        Address address = null;
        Customer cus = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    address = new Address();
                    cus = new Customer();

                    address.setAddId(rs.getInt(ID));
                    cus.setCusID(rs.getInt(CUST_RESIDENCE_ID));
                    address.setCusResidId(cus);
                    address.setCusChargeId(cus);
                    address.setAddLabel(rs.getString(LABEL));
                    address.setAddFirstName(rs.getString(FIRST_NAME));
                    address.setAddLastName(rs.getString(LAST_NAME));
                    address.setAddCompany(rs.getString(COMPANY));
                    address.setAddNumber(rs.getString(STREET_NUMBER));
                    address.setAddStreetType(rs.getString(STREET_TYPE));
                    address.setAddStreetName(rs.getString(STREET_NAME));
                    address.setAddComplement(rs.getString(COMPLEMENT));
                    address.setAddZipCode(rs.getString(ZIP_CODE));
                    address.setAddCity(rs.getString(CITY));
                    address.setAddSecurityCode(rs.getString(SECURITY_CODE));
                    address.setAddPhone(rs.getString(PHONE));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return address;
    }

     
    public Object find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Vector findByColumn(String column, String term) {
        Vector<Address> addressList = new Vector<Address>();
        Customer cus = null;
        Address address = null;

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
                    address = new Address();
                    cus = new Customer();

                    address.setAddId(rs.getInt(ID));
                    cus.setCusID(rs.getInt(CUST_RESIDENCE_ID));
                    address.setCusResidId(cus);
                    address.setCusChargeId(cus);
                    address.setAddLabel(rs.getString(LABEL));
                    address.setAddFirstName(rs.getString(FIRST_NAME));
                    address.setAddLastName(rs.getString(LAST_NAME));
                    address.setAddCompany(rs.getString(COMPANY));
                    address.setAddNumber(rs.getString(STREET_NUMBER));
                    address.setAddStreetType(rs.getString(STREET_TYPE));
                    address.setAddStreetName(rs.getString(STREET_NAME));
                    address.setAddComplement(rs.getString(COMPLEMENT));
                    address.setAddZipCode(rs.getString(ZIP_CODE));
                    address.setAddCity(rs.getString(CITY));
                    address.setAddSecurityCode(rs.getString(SECURITY_CODE));
                    address.setAddPhone(rs.getString(PHONE));
                    addressList.add(address);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return addressList;
    }

    
    public Vector<Address> findByCustomerId(int customerId) {
        Vector<Address> addressList = new Vector<Address>();
        Customer cus = null;
        Address address = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(CUST_CHARGE_ID)
                .append(" = ")
                .append(customerId);

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    address = new Address();
                    cus = new Customer();

                    address.setAddId(rs.getInt(ID));
                    cus.setCusID(rs.getInt(CUST_RESIDENCE_ID));
                    address.setCusResidId(cus);
                    address.setCusChargeId(cus);
                    address.setAddLabel(rs.getString(LABEL));
                    address.setAddFirstName(rs.getString(FIRST_NAME));
                    address.setAddLastName(rs.getString(LAST_NAME));
                    address.setAddCompany(rs.getString(COMPANY));
                    address.setAddNumber(rs.getString(STREET_NUMBER));
                    address.setAddStreetType(rs.getString(STREET_TYPE));
                    address.setAddStreetName(rs.getString(STREET_NAME));
                    address.setAddComplement(rs.getString(COMPLEMENT));
                    address.setAddZipCode(rs.getString(ZIP_CODE));
                    address.setAddCity(rs.getString(CITY));
                    address.setAddSecurityCode(rs.getString(SECURITY_CODE));
                    address.setAddPhone(rs.getString(PHONE));
                    addressList.add(address);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return addressList;
    }
    
}
