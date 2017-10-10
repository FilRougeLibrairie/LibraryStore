
package accessBD;


import names.SQLNames.VATNames;
import entity.Vat;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class VatDAO  implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Vat";
    private final String CODE = VATNames.CODE;
    private final String RATE = VATNames.RATE;
    private final String NAME = VATNames.NAME;
    private final String STATUS = VATNames.STATUS;

    private String COLUMNS_CREATE =  RATE + ", " + NAME;

    //Constructor
    public VatDAO() throws NamingException {
         mc= new MyConnexion();
    }

     
    public void create(Vat obj) {
        Vat vat = (Vat) obj;
        String query = "IF NOT EXISTS(SELECT * FROM vat WHERE vatRate= "+vat.getVatRate()+")"
                + "INSERT INTO VAT (" + COLUMNS_CREATE+ ")"
                + "VALUES (?, ?,?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setFloat(1, vat.getVatRate());
            pstmt.setString(2, vat.getVatName());
            pstmt.setInt(3, vat.getVatStatus());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR Create Vat Object : " + ex.getErrorCode() + " / " + ex.getMessage());
            
        }
    }

     
    public void update(Vat obj) {
        Vat vat = (Vat) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(RATE).append(" = ?, ");
        query.append(NAME).append(" = ?, ");
        query.append(STATUS).append(" = ? ");

        query.append("WHERE " + CODE + " = '")
                .append(vat.getVatCode())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setFloat(1, vat.getVatRate());
            pstmt.setString(2, vat.getVatName());
            pstmt.setInt(3, vat.getVatStatus());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            

        }
    }
    
    public Boolean answer(Vat obj){
        Boolean answer=true;
        Vat vat = (Vat) obj;
        String query = "SELECT * FROM vat WHERE vatRate= '"+vat.getVatRate()+"'";

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

     
    public void delete(Vat obj) {
        int vatId = ((Vat) obj).getVatCode();
       
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(CODE)
                .append(" = ")
                .append("'" + vatId + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
    }

     
    public Vector<Vat> findAll() {
        Vector<Vat> vatList = new Vector<Vat>();
        Vat vat = null;
        String query = "SELECT * FROM VAT";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    vat = new Vat();
                    vat.setVatCode(rs.getInt(CODE));
                    vat.setVatRate(rs.getFloat(RATE));
                    vat.setVatName(rs.getString(NAME));
                    vat.setVatStatus(rs.getInt(STATUS));
                    vatList.add(vat);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return vatList;
    }

     
    public Vector<Vat> findByColumn(String column, String term) {
        Vector<Vat> vatList = new Vector<Vat>();
        Vat vat = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "'");

        

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    vat = new Vat();
                    vat.setVatCode(rs.getInt(CODE));
                    vat.setVatRate(rs.getFloat(RATE));
                    vat.setVatName(rs.getString(NAME));
                    vat.setVatStatus(rs.getInt(STATUS));
                    vatList.add(vat);
                }
            } else {
                throw new SQLException("ResultSet Vat was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return vatList;
    }

     
    public Vat find(int id) {
        Vat vat = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(CODE)
                .append(" = ")
                .append(id);

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    vat = new Vat();
                    vat.setVatCode(rs.getInt(CODE));
                    vat.setVatRate(rs.getFloat(RATE));
                    vat.setVatName(rs.getString(NAME));
                    vat.setVatStatus(rs.getInt(STATUS));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return vat;
    }

     
    public Vat find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
