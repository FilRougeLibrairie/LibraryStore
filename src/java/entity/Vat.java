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
public class Vat {

    private int vatCode;
    private Float vatRate;
    private String vatName;
    private int vatStatus;

    //Constructor
    public Vat() {
    }

    public Vat(int vatCode, Float vatRate, String vatName, int vatStatus) {
        this.vatCode = vatCode;
        this.vatRate = vatRate;
        this.vatName = vatName;
        this.vatStatus = vatStatus;
    }
    
    

    //Setters
    public void setVatCode(int vatCode) {
        this.vatCode = vatCode;
    }

    public void setVatRate(Float vatRate) {
        this.vatRate = vatRate;
    }

    public void setVatName(String vatName) {
        this.vatName = vatName;
    }

    public void setVatStatus(int vatStatus) {
        this.vatStatus = vatStatus;
    }

    

    
    
    //Getters
    public int getVatCode() {
        return vatCode;
    }

    public Float getVatRate() {
        return vatRate;
    }

    public String getVatName() {
        return vatName;
    }
    
    public int getVatStatus() {
        return vatStatus;
    }
    
    //vector
    
    public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getVatCode());
        v.add(this.getVatRate());
        v.add(this.getVatName());
        v.add(this.getVatStatus());
        
        
      
        return v;
    }

    @Override
    public String toString() {
        return "Vat{" + "vatCode=" + vatCode + ", vatRate=" + vatRate + ", vatName=" + vatName + ", vatStatus=" + vatStatus + '}';
    }

    

    
}
