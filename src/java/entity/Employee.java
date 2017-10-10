
package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author Tofi
 */
public class Employee {

    private int empId;
    private String empFirstName;
    private String empLastName;
    private String empLogin;
    private String empPassword;
    private String empSalt;
    private java.sql.Date empDateStart;
    private java.sql.Date empDateEnd;
    private AccessProfile accProfileCode;
    private int empStatus;
    private String empComment;

    //Constructor
    public Employee() {
    }

    //Setters
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public void setEmpLogin(String empLogin) {
        this.empLogin = empLogin;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public void setEmpSalt(String empSalt) {
        this.empSalt = empSalt;
    }

    public void setEmpDateStart(String empDateStart) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date start = sdf.parse(empDateStart);
            java.sql.Date sqlStartD = new java.sql.Date(start.getTime());
            this.empDateStart = sqlStartD;
        } catch (ParseException ex) {
            System.out.println("Error formating DATE" + ex.getMessage());
        }
    }

    public void setEmpDateEnd(String empDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (empDateEnd != null) {
            try {
                java.util.Date end = sdf.parse(empDateEnd);
                java.sql.Date sqlEndD = new java.sql.Date(end.getTime());
                this.empDateEnd = sqlEndD;
            } catch (ParseException ex) {
                System.out.println("Error formating DATE" + ex.getMessage());
            }
        }
    }

    public void setEmpStatus(int empStatus) {
        this.empStatus = empStatus;
    }

    public void setAccProfileCode(AccessProfile accProfileCode) {
        this.accProfileCode = accProfileCode;
    }

    public void setEmpComment(String empComment) {
        this.empComment = empComment;
    }

    //Getters
    public int getEmpId() {
        return empId;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public String getEmpLogin() {
        return empLogin;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public String getEmpSalt() {
        return empSalt;
    }

    public java.sql.Date getEmpDateStart() {
        return new java.sql.Date(empDateStart.getTime());
    }

    public java.sql.Date getEmpDateEnd() {
        if (empDateEnd== null) {
            return null;
        } else {
            return new java.sql.Date(empDateEnd.getTime());
        }
    }

    public int getEmpStatus() {
        return empStatus;
    }

    public AccessProfile getAccProfileCode() {
        return accProfileCode;
    }

    public String getEmpComment() {
        return empComment;
    }

    @Override
    public String toString() {
        return "***** Employee ID : " + empId + "\n"
                + "empFirstName=" + empFirstName + "\n"
                + "empLastName=" + empLastName + "\n"
                + "empLogin=" + empLogin + "\n"
                + "empPassword=" + empPassword + "\n"
                + "empSalt=" + empSalt + "\n"
                + "empDateStart=" + empDateStart + "\n"
                + "empDateEnd=" + empDateEnd + "\n"
                + "empStatus=" + empStatus + "\n"
                + "empProfileCode=" + accProfileCode.getAccProfileCode() + "\n"
                + "empComment=" + empComment + "\n"
                + "AccessProfile : " + accProfileCode + "\n"
                + "***************";
    }

}
