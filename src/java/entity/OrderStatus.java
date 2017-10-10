/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author Tofi
 */
public class OrderStatus {

    private int staCode;
    private String staName;
    Vector<Purchase> purchaseList;
    Purchase purchase;
    String StatusDate;

    //Constructor
    public OrderStatus() {
    }
    
    
    public Vector getVector(){
     Vector v = new Vector();
        v.add(this.StatusDate);
        v.add(this.staName);
        return v;
}

    //Setters
    public void setStaCode(int staCode) {
        this.staCode = staCode;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    //Getters
    public int getStaCode() {
        return staCode;
    }

    public String getStaName() {
        return staName;
    }

    public Vector<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(Vector<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public void addPurchase(Purchase purchase) {
        purchaseList.add(purchase);
    }

    public void removePurchase(Purchase purchase) {
        purchaseList.remove(purchase);
    }

    public String getStatusDate() {
        return StatusDate;
    }

    public void setStatusDate(String StatusDate) {
        this.StatusDate = StatusDate;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "OrderStatus{" + "staCode=" + staCode + ", staName=" + staName + ", purchaseList=" + purchaseList + ", purchase=" + purchase + ", StatusDate=" + StatusDate + '}';
    }

}
