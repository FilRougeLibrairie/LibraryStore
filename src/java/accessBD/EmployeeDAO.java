package accessBD;


import names.SQLNames.EmployeeNames;
import entity.AccessProfile;
import entity.Employee;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class EmployeeDAO  implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Employee";
    
    private final String ID = EmployeeNames.ID;
    private final String LAST_NAME = EmployeeNames.LAST_NAME;
    private final String FIRST_NAME = EmployeeNames.FIRST_NAME;
    private final String LOGIN = EmployeeNames.LOGIN;
    private final String PASSWORD = EmployeeNames.PASSWORD;
    private final String SALT = EmployeeNames.SALT;
    private final String DATE_START = EmployeeNames.DATE_START;
    private final String DATE_END = EmployeeNames.DATE_END;
    private final String STATUS = EmployeeNames.STATUS;
    private final String PROFILE = EmployeeNames.ACCESS_PROFILE;
    private final String COMMENT = EmployeeNames.COMMENT;
    
    private String COLUMNS_CREATE = LAST_NAME + ", " + FIRST_NAME + ", " + LOGIN + ", "
            + PASSWORD + ", " + SALT + ", " + DATE_START + ", " + DATE_END + ", "
            + STATUS + ", " + PROFILE + ", " + COMMENT;
    
    
    // Constructor
    public EmployeeDAO() throws NamingException{
         mc= new MyConnexion();
    }

     
    public void create(Employee obj) {
              Employee emp = (Employee) obj;
        String query = "IF NOT EXISTS (SELECT * FROM " + TABLE + " WHERE " + ID + " = '" + emp.getEmpId() + "')"
                + "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setString(1, emp.getEmpLastName());
            pstmt.setString(2, emp.getEmpFirstName());
            pstmt.setString(3, emp.getEmpLogin());
            pstmt.setString(4, emp.getEmpPassword());
            pstmt.setString(5, emp.getEmpSalt());
            pstmt.setDate(6, emp.getEmpDateStart());
            pstmt.setDate(7, emp.getEmpDateEnd());
            pstmt.setInt(8, emp.getEmpStatus());
            pstmt.setInt(9, emp.getAccProfileCode().getAccProfileCode());
            pstmt.setString(10, emp.getEmpComment());
            
            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());
            
        }
    }

     
    public void update(Employee obj) {
       Employee emp = (Employee) obj;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(LAST_NAME).append(" = ?, ");
        query.append(FIRST_NAME).append(" = ?, ");
        query.append(LOGIN).append(" = ?, ");
        query.append(PASSWORD).append(" = ?, ");
        query.append(SALT).append(" = ?, ");
        query.append(DATE_START).append(" = ?, ");
        query.append(DATE_END).append(" = ?, ");
        query.append(STATUS).append(" = ?, ");
        query.append(PROFILE).append(" = ?, ");
        query.append(COMMENT).append(" = ? ");

        query.append("WHERE " + ID + " = '")
                .append(emp.getEmpId())
                .append("'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setString(1, emp.getEmpLastName());
            pstmt.setString(2, emp.getEmpFirstName());
            pstmt.setString(3, emp.getEmpLogin());
            pstmt.setString(4, emp.getEmpPassword());
            pstmt.setString(5, emp.getEmpSalt());
            pstmt.setDate(6, emp.getEmpDateStart());
            pstmt.setDate(7, emp.getEmpDateEnd());
            pstmt.setInt(8, emp.getEmpStatus());
            pstmt.setInt(9, emp.getAccProfileCode().getAccProfileCode());
            pstmt.setString(10, emp.getEmpComment());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());
            

        }
    }
    
     
    public void delete(Employee obj) {
         int empId = ((Employee) obj).getEmpId();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + empId + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            
        }
    }

     
    public Employee find(int id) {
        Employee employee = null;
        AccessProfile acc = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    employee = new Employee();
                    employee.setEmpId(rs.getInt(ID));
                    employee.setEmpLastName(rs.getString(LAST_NAME));
                    employee.setEmpFirstName(rs.getString(FIRST_NAME));
                    employee.setEmpLogin(rs.getString(LOGIN));
                    employee.setEmpPassword(rs.getString(PASSWORD));
                    employee.setEmpSalt(rs.getString(SALT));
                    employee.setEmpDateStart(rs.getString(DATE_START));
                    employee.setEmpDateEnd(rs.getString(DATE_END));
                    employee.setEmpStatus(rs.getInt(STATUS));
                    acc = new AccessProfile();
                    acc.setAccProfileCode(rs.getInt(PROFILE));
                    employee.setAccProfileCode(acc);
                    employee.setEmpComment(rs.getString(COMMENT));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return employee;
    }

     
    public Vector<Employee> findAll() {
        Vector<Employee> employeeList = new Vector<Employee>();
        Employee employee = null;
        AccessProfile acc = null;    
        String query = "SELECT * FROM " + TABLE;

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    employee = new Employee();
                    employee.setEmpId(rs.getInt(ID));
                    employee.setEmpLastName(rs.getString(LAST_NAME));
                    employee.setEmpFirstName(rs.getString(FIRST_NAME));
                    employee.setEmpLogin(rs.getString(LOGIN));
                    employee.setEmpPassword(rs.getString(PASSWORD));
                    employee.setEmpSalt(rs.getString(SALT));
                    employee.setEmpDateStart(rs.getString(DATE_START));
                    employee.setEmpDateEnd(rs.getString(DATE_END));
                    employee.setEmpStatus(rs.getInt(STATUS));
                    acc = new AccessProfile();
                    acc.setAccProfileCode(rs.getInt(PROFILE));
                    employee.setAccProfileCode(acc);
                    employee.setEmpComment(rs.getString(COMMENT));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return employeeList;
    }

     
    public Employee find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public Vector<Employee> findByColumn(String column, String term) {
         Vector<Employee> employeeList = new Vector<Employee>();
        Employee employee = null;
        AccessProfile acc = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" LIKE ")
                .append("'" + term + "%'");

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    employee = new Employee();
                    employee.setEmpId(rs.getInt(ID));
                    employee.setEmpLastName(rs.getString(LAST_NAME));
                    employee.setEmpFirstName(rs.getString(FIRST_NAME));
                    employee.setEmpLogin(rs.getString(LOGIN));
                    employee.setEmpPassword(rs.getString(PASSWORD));
                    employee.setEmpSalt(rs.getString(SALT));
                    employee.setEmpDateStart(rs.getString(DATE_START));
                    employee.setEmpDateEnd(rs.getString(DATE_END));
                    employee.setEmpStatus(rs.getInt(STATUS));
                    acc = new AccessProfile();
                    acc.setAccProfileCode(rs.getInt(PROFILE));
                    employee.setAccProfileCode(acc);
                    employee.setEmpComment(rs.getString(COMMENT));
                    employeeList.add(employee);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return employeeList;
    }
    
     public Vector<Employee> findByColumn(String column, int term) {
         Vector<Employee> employeeList = new Vector<Employee>();
        Employee employee = null;
        AccessProfile acc = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append(term);

        try (Connection cnt = mc.getConnection();PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    employee = new Employee();
                    employee.setEmpId(rs.getInt(ID));
                    employee.setEmpLastName(rs.getString(LAST_NAME));
                    employee.setEmpFirstName(rs.getString(FIRST_NAME));
                    employee.setEmpLogin(rs.getString(LOGIN));
                    employee.setEmpPassword(rs.getString(PASSWORD));
                    employee.setEmpSalt(rs.getString(SALT));
                    employee.setEmpDateStart(rs.getString(DATE_START));
                    employee.setEmpDateEnd(rs.getString(DATE_END));
                    employee.setEmpStatus(rs.getInt(STATUS));
                    acc = new AccessProfile();
                    acc.setAccProfileCode(rs.getInt(PROFILE));
                    employee.setAccProfileCode(acc);
                    employee.setEmpComment(rs.getString(COMMENT));
                    employeeList.add(employee);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
            

        }
        return employeeList;
    }
}
