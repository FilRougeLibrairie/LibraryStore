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
public class Determinate {
    
    private Vector <OrderStatus> orderStatusList;
    private Vector <Purchase> PurchaseList;
    private String detTime;
    
    //Constructor

    public Determinate() {
    }
    
    public void addOrderStatus(OrderStatus orderStatus){
        orderStatusList.add(orderStatus);
    }

    public void removeOrderStatus(OrderStatus orderStatus){
        orderStatusList.remove(orderStatus);
    }
    
    public void addPurchaseList(Purchase purchase) {
        PurchaseList.add(purchase);
    }
    
    public void removePurchaseList(Purchase purchase) {
        PurchaseList.remove(purchase);
    }

    public Vector<OrderStatus> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(Vector<OrderStatus> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public Vector<Purchase> getPurchaseList() {
        return PurchaseList;
    }

    public void setPurchaseList(Vector<Purchase> PurchaseList) {
        this.PurchaseList = PurchaseList;
    }

    public String getDetTime() {
        return detTime;
    }

    public void setDetTime(String detTime) {
        this.detTime = detTime;
    }

    @Override
    public String toString() {
        return "Determinate{" + "orderStatusList=" + orderStatusList + ", PurchaseList=" + PurchaseList + ", detTime=" + detTime + '}';
    }

    
}
