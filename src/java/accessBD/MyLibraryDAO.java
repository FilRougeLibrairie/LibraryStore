
package accessBD;


import names.SQLNames.MyLibraryNames;
import entity.MyLibrary;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class MyLibraryDAO  implements Serializable{
    
    private MyConnexion mc;
    private final String TABLE = "MyLibrary";
   
    private final String ID = MyLibraryNames.ID;
    private final String NAME = MyLibraryNames.NAME;
    private final String LOGO = MyLibraryNames.LOGO;
    private final String EMAIL = MyLibraryNames.EMAIL; 
    private final String PHONE = MyLibraryNames.PHONE;
    private final String SIRET = MyLibraryNames.SIRET;
    private final String CGU = MyLibraryNames.CGU;
    private final String STREET_NUMBER = MyLibraryNames.STREET_NUMBER;
    private final String STREET_NAME = MyLibraryNames.STREET_NAME;
    private final String COMPLEMENT = MyLibraryNames.COMPLEMENT;
    private final String ZIP_CODE = MyLibraryNames.ZIP_CODE;
    private final String CITY = MyLibraryNames.CITY;
    
    private String COLUMNS_CREATE = NAME + ", " + LOGO + ", " + EMAIL + ", "
            + PHONE + ", " + SIRET + ", " + CGU + ", " + STREET_NUMBER + ", "
            + STREET_NAME + ", " + COMPLEMENT + ", " + ZIP_CODE+ "," + CITY;
    
    //Constructor

    public MyLibraryDAO() throws NamingException {
         mc= new MyConnexion();
    }
    
      
    public void create(MyLibrary obj) {
              MyLibrary mylib = (MyLibrary) obj;
        String query = "IF NOT EXISTS (SELECT * FROM " + TABLE + " WHERE " + ID + " = '" + mylib.getMyLibId() + "')"
                + "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setString(1, mylib.getMyLibName());
            pstmt.setString(2, mylib.getMyLibLogo());
            pstmt.setString(3, mylib.getMyLibEmail());
            pstmt.setString(4, mylib.getMyLibPhone());
            pstmt.setString(5, mylib.getMyLibSiret());
            pstmt.setString(6, mylib.getMyLibCGU());
            pstmt.setString(7, mylib.getMyLibAddNumber());
            pstmt.setString(8, mylib.getMyLibAddStreetName());
            pstmt.setString(9, mylib.getMyLibAddComplement());
            pstmt.setString(10, mylib.getMyLibAddZipCode());
            pstmt.setString(11, mylib.getMyLibAddZipCode());
            
            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
            
        }
    }

     
    public void update(MyLibrary obj) {
       MyLibrary mylib = (MyLibrary) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(NAME).append(" = ?, ");
        query.append(LOGO).append(" = ?, ");
        query.append(EMAIL).append(" = ?, ");
        query.append(PHONE).append(" = ?, ");
        query.append(SIRET).append(" = ?, ");
        query.append(CGU).append(" = ?, ");
        query.append(STREET_NUMBER).append(" = ?, ");
        query.append(STREET_NAME).append(" = ?, ");
        query.append(COMPLEMENT).append(" = ?, ");
        query.append(ZIP_CODE).append(" = ?, ");
        query.append(CITY).append(" = ? ");

        query.append("WHERE " + ID + " = '")
                .append(mylib.getMyLibId())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, mylib.getMyLibName());
            pstmt.setString(2, mylib.getMyLibLogo());
            pstmt.setString(3, mylib.getMyLibEmail());
            pstmt.setString(4, mylib.getMyLibPhone());
            pstmt.setString(5, mylib.getMyLibSiret());
            pstmt.setString(6, mylib.getMyLibCGU());
            pstmt.setString(7, mylib.getMyLibAddNumber());
            pstmt.setString(8, mylib.getMyLibAddStreetName());
            pstmt.setString(9, mylib.getMyLibAddComplement());
            pstmt.setString(10, mylib.getMyLibAddZipCode());
            pstmt.setString(11, mylib.getMyLibAddCity());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            

        }
    }
    
     
    public void delete(MyLibrary obj) {
         int myLibId = ((MyLibrary) obj).getMyLibId();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + myLibId + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
    }

     
    public MyLibrary find(int id) {
        MyLibrary myLibrary = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    myLibrary = new MyLibrary();
                    myLibrary.setMyLibId(rs.getInt(ID));
                    myLibrary.setMyLibName(rs.getString(NAME));
                    myLibrary.setMyLibLogo(rs.getString(LOGO));
                    myLibrary.setMyLibEmail(rs.getString(EMAIL));
                    myLibrary.setMyLibPhone(rs.getString(PHONE));
                    myLibrary.setMyLibSiret(rs.getString(SIRET));
                    myLibrary.setMyLibCGU(rs.getString(CGU));
                    myLibrary.setMyLibAddNumber(rs.getString(STREET_NUMBER));
                    myLibrary.setMyLibAddStreetName(rs.getString(STREET_NAME));
                    myLibrary.setMyLibAddComplement(rs.getString(COMPLEMENT));
                    myLibrary.setMyLibAddZipCode(rs.getString(ZIP_CODE));
                    myLibrary.setMyLibAddCity(rs.getString(CITY));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return myLibrary;
    }

     
    public Vector<MyLibrary> findAll() {
        Vector<MyLibrary> myLibraryList = new Vector<MyLibrary>();
        MyLibrary myLibrary = null; 
        String query = "SELECT * FROM " + TABLE;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    myLibrary = new MyLibrary();
                    myLibrary.setMyLibId(rs.getInt(ID));
                    myLibrary.setMyLibName(rs.getString(NAME));
                    myLibrary.setMyLibLogo(rs.getString(LOGO));
                    myLibrary.setMyLibEmail(rs.getString(EMAIL));
                    myLibrary.setMyLibPhone(rs.getString(PHONE));
                    myLibrary.setMyLibSiret(rs.getString(SIRET));
                    myLibrary.setMyLibCGU(rs.getString(CGU));
                    myLibrary.setMyLibAddNumber(rs.getString(STREET_NUMBER));
                    myLibrary.setMyLibAddStreetName(rs.getString(STREET_NAME));
                    myLibrary.setMyLibAddComplement(rs.getString(COMPLEMENT));
                    myLibrary.setMyLibAddZipCode(rs.getString(ZIP_CODE));
                    myLibrary.setMyLibAddCity(rs.getString(CITY));
                    myLibraryList.add(myLibrary);
                    
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return myLibraryList;
    }

     
    public MyLibrary find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Vector<MyLibrary> findByColumn(String column, String term) {
         Vector<MyLibrary> myLibraryList = new Vector<MyLibrary>();
        MyLibrary myLibrary = null;
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
                    myLibrary = new MyLibrary();
                    myLibrary.setMyLibId(rs.getInt(ID));
                    myLibrary.setMyLibName(rs.getString(NAME));
                    myLibrary.setMyLibLogo(rs.getString(LOGO));
                    myLibrary.setMyLibEmail(rs.getString(EMAIL));
                    myLibrary.setMyLibPhone(rs.getString(PHONE));
                    myLibrary.setMyLibSiret(rs.getString(SIRET));
                    myLibrary.setMyLibCGU(rs.getString(CGU));
                    myLibrary.setMyLibAddNumber(rs.getString(STREET_NUMBER));
                    myLibrary.setMyLibAddStreetName(rs.getString(STREET_NAME));
                    myLibrary.setMyLibAddComplement(rs.getString(COMPLEMENT));
                    myLibrary.setMyLibAddZipCode(rs.getString(ZIP_CODE));
                    myLibrary.setMyLibAddCity(rs.getString(CITY));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return myLibraryList;
    }
}
