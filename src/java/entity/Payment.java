/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Tofi
 */
public class Payment {

    private int payId;
    private Purchase purId;
    private Boolean payValidate;
    private String payChoice;
    private String payDate;
    private String payCardType;
    private String payOwnerName;

    //Constructor
    public Payment() {
    }

    public String getPayCardType() {
        return payCardType;
    }

    public void setPayCardType(String payCardType) {
        this.payCardType = payCardType;
    }

    public String getPayOwnerName() {
        return payOwnerName;
    }

    public void setPayOwnerName(String payOwnerName) {
        this.payOwnerName = payOwnerName;
    }

    //Setters
    public void setPayId(int payId) {
        this.payId = payId;
    }

    public void setPurId(Purchase purId) {
        this.purId = purId;
    }

   

    public void setPayChoice(String payChoice) {
        this.payChoice = payChoice;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    //Getters
    public int getPayId() {
        return payId;
    }

    public Purchase getPurId() {
        return purId;
    }

    

    public String getPayChoice() {
        return payChoice;
    }

    public String getPayDate() {
        return payDate;
    }

    public Boolean getPayValidate() {
        return payValidate;
    }

    public void setPayValidate(Boolean payValidate) {
        this.payValidate = payValidate;
    }
    
    
    
    

}
