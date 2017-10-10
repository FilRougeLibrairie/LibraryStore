/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Vector;

/**
 *
 * @author Tofi
 */
public class MyLibrary {
    
    private int myLibId;
    private String myLibName;
    private String myLibLogo;
    private String myLibEmail; 
    private String myLibPhone;
    private String myLibSiret;
    private String myLibCGU;
    private String myLibAddNumber;
    private String myLibAddStreetName;
    private String myLibAddComplement;
    private String myLibAddZipCode;
    private String myLibAddCity;
    
    //Constructor

    public MyLibrary() {
    }

    public MyLibrary(int myLibId, String myLibName, String myLibLogo, String myLibEmail, String myLibPhone, String myLibSiret, String myLibCGU, String myLibAddNumber, String myLibAddStreetName, String myLibAddComplement, String myLibAddZipCode, String myLibAddCity) {
        this.myLibId = myLibId;
        this.myLibName = myLibName;
        this.myLibLogo = myLibLogo;
        this.myLibEmail = myLibEmail;
        this.myLibPhone = myLibPhone;
        this.myLibSiret = myLibSiret;
        this.myLibCGU = myLibCGU;
        this.myLibAddNumber = myLibAddNumber;
        this.myLibAddStreetName = myLibAddStreetName;
        this.myLibAddComplement = myLibAddComplement;
        this.myLibAddZipCode = myLibAddZipCode;
        this.myLibAddCity = myLibAddCity;
    }
    
    
    
    
    
     public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getMyLibAddCity());
        v.add(this.getMyLibAddComplement());
        v.add(this.getMyLibAddNumber());
        v.add(this.getMyLibAddStreetName());
        v.add(this.getMyLibAddZipCode());
        v.add(this.getMyLibCGU());
        v.add(this.getMyLibEmail());
        v.add(this.getMyLibId());
        v.add(this.getMyLibLogo());
        v.add(this.getMyLibName());
        v.add(this.getMyLibPhone());
        v.add(this.getMyLibSiret());
      
        return v;
    }
    
    
    
    
    
   //Setters

    public void setMyLibId(int myLibId) {
        this.myLibId = myLibId;
    }

    public void setMyLibName(String myLibName) {
        this.myLibName = myLibName;
    }

    public void setMyLibLogo(String myLibLogo) {
        this.myLibLogo = myLibLogo;
    }

    public void setMyLibEmail(String myLibEmail) {
        this.myLibEmail = myLibEmail;
    }

    public void setMyLibPhone(String myLibPhone) {
        this.myLibPhone = myLibPhone;
    }

    public void setMyLibSiret(String myLibSiret) {
        this.myLibSiret = myLibSiret;
    }

    public void setMyLibCGU(String myLibCGU) {
        this.myLibCGU = myLibCGU;
    }

    public void setMyLibAddNumber(String myLibAddNumber) {
        this.myLibAddNumber = myLibAddNumber;
    }

    public void setMyLibAddStreetName(String myLibAddStreetName) {
        this.myLibAddStreetName = myLibAddStreetName;
    }

    public void setMyLibAddComplement(String myLibAddComplement) {
        this.myLibAddComplement = myLibAddComplement;
    }

    public void setMyLibAddZipCode(String myLibAddZipCode) {
        this.myLibAddZipCode = myLibAddZipCode;
    }

    public void setMyLibAddCity(String myLibAddCity) {
        this.myLibAddCity = myLibAddCity;
    }
    
    
    
    //Getters

    public int getMyLibId() {
        return myLibId;
    }

    public String getMyLibName() {
        return myLibName;
    }

    public String getMyLibLogo() {
        return myLibLogo;
    }

    public String getMyLibEmail() {
        return myLibEmail;
    }

    public String getMyLibPhone() {
        return myLibPhone;
    }

    public String getMyLibSiret() {
        return myLibSiret;
    }

    public String getMyLibCGU() {
        return myLibCGU;
    }

    public String getMyLibAddNumber() {
        return myLibAddNumber;
    }

    public String getMyLibAddStreetName() {
        return myLibAddStreetName;
    }

    public String getMyLibAddComplement() {
        return myLibAddComplement;
    }

    public String getMyLibAddZipCode() {
        return myLibAddZipCode;
    }

    public String getMyLibAddCity() {
        return myLibAddCity;
    }
    
    
}
