/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Tofi
 */
public class Customer {

    private int cusID;
    private String cusGender;
    private String cusFirstName;
    private String cusLastName;
    private String cusOrganisationName;
    private String cusEmail;
    private String cusPhoneNumber;
    private String cusDateOfBirth;
    private String cusPassword;
    private String cusSalt;
    private String cusIP;
    private int cusStatus;
    private String cusComment;
    private String cusClearPassword;

    //Constructor
    public Customer() {
    }

    //Setters

    public void setCusDateOfBirth(String cusDateOfBirth) {
        this.cusDateOfBirth = cusDateOfBirth;
    }

    public void setCusClearPassword(String cusClearPassword) {
        this.cusClearPassword = cusClearPassword;
    }
    
    
    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public void setCusGender(String cusGender) {
        this.cusGender = cusGender;
    }

    public void setCusFirstName(String cusFirstName) throws Exception {
        if (cusFirstName.length() < 2 || cusFirstName.isEmpty()) {
            throw new Exception("Le nom de famille est obligatoire");
        } else {
            this.cusFirstName = cusFirstName.substring(0, 1).toUpperCase() + cusFirstName.substring(1, cusFirstName.length()).toLowerCase();
        }
    }

    public void setCusLastName(String cusLastName) throws Exception {
        if (cusLastName.length() < 2 || cusLastName.isEmpty()) {
            throw new Exception("Le prénom est obligatoire");
        } else {
            this.cusLastName = cusLastName.substring(0, 1).toUpperCase() + cusLastName.substring(1, cusLastName.length());
        }
    }

    public void setCusOrganisationName(String cusOrganisationName) {
        this.cusOrganisationName = cusOrganisationName;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public void setCusPhoneNumber(String cusPhoneNumber) {
        this.cusPhoneNumber = cusPhoneNumber;
    }

    

    public void setCusPassword(String cusPassword) {
        this.cusPassword = cusPassword;
    }

    public String getCusSalt() {
        return cusSalt;
    }

    public void setCusSalt(String cusSalt) {
        this.cusSalt = cusSalt;
    }

    public void setCusIP(String cusIP) {
        this.cusIP = cusIP;
    }

    public void setCusStatus(int cusStatus) {
        this.cusStatus = cusStatus;
    }

    public void setCusComment(String cusComment) {
        this.cusComment = cusComment;
    }

    //Getters

    public String getCusClearPassword() {
        return cusClearPassword;
    }
    
    
    public int getCusID() {
        return cusID;
    }

    public String getCusGender() {
        return cusGender;
    }

    public String getCusFirstName() {
        return cusFirstName;
    }

    public String getCusLastName() {
        return cusLastName;
    }

    public String getCusOrganisationName() {
        return cusOrganisationName;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public String getCusPhoneNumber() {
        return cusPhoneNumber;
    }

    public String getCusDateOfBirth() {
        return cusDateOfBirth;
    }

    public String getCusPassword() {
        return cusPassword;
    }

    public String getCusIP() {
        return cusIP;
    }

    public int getCusStatus() {
        return cusStatus;
    }

    public String getCusComment() {
        return cusComment;
    }

    @Override
    public String toString() {
        return "Customer number :" + cusID + "\n"
                + "Gender : " + cusGender + "\n"
                + "FirstName : " + cusFirstName + "\n"
                + "LastName : " + cusLastName + "\n"
                + "OrganisationName : " + cusOrganisationName + "\n"
                + "Email : " + cusEmail + "\n"
                + "Phone Number : " + cusPhoneNumber + "\n"
                + "DateOfBirth : " + cusDateOfBirth + "\n"
                + "cusPassword : " + cusPassword + "\n"
                + "Salt : " + cusSalt + "\n"
                + "IP : " + cusIP + "\n"
                + "Status : " + cusStatus + "\n"
                + "Comment : " + cusComment;
    }
}
