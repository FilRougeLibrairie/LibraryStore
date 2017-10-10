/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.UUID;
import java.util.Vector;

/**
 *
 * @author Tofi
 */
public class Purchase {

    private int purId;
    private String internId;
    private Customer customer;
    private ShippingCost shippingCostId;
    private Address addDelivery;
    private Address addInvoice;
    private String purIP;
    private String shippingDate;
    private int shippingNumber;
    Vector<OrderStatus> orderstatusList;
    

    //Constructor
    public Purchase() {
        internId = generateInternalId();
    }
    
    

    //Setters
    public void setPurId(int purId) {
        this.purId = purId;
    }

    public void setCusId(Customer cusId) {
        this.customer = cusId;
    }

    public void setShippingCostId(ShippingCost shippingCostId) {
        this.shippingCostId = shippingCostId;
    }

    public void setAddDeliveryId(Address addDeliveryId) {
        this.addDelivery = addDeliveryId;
    }

    public void setAddInvoiceId(Address addInvoiceId) {
        this.addInvoice = addInvoiceId;
    }

    public void setPurIP(String purIP) {
        this.purIP = purIP;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setShippingNumber(int shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

    public void setUuid(String uuid) {
        this.internId = uuid;
    }
    
    private String generateInternalId(){
        return UUID.randomUUID().toString();
    }
    
    

    //Getters
    public int getPurId() {
        return purId;
    }

    public Customer getCusId() {
        return customer;
    }

    public ShippingCost getShippingCostId() {
        return shippingCostId;
    }

    public Address getAddDeliveryId() {
        return addDelivery;
    }

    public Address getAddInvoiceId() {
        return addInvoice;
    }

    public String getPurIP() {
        return purIP;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public int getShippingNumber() {
        return shippingNumber;
    }

    public Vector<OrderStatus> getOrderstatusList() {
        return orderstatusList;
    }

    public void setOrderstatusList(Vector<OrderStatus> orderstatusList) {
        this.orderstatusList = orderstatusList;
    }
    
    public void addOrderStatus(OrderStatus orderStatus){
        orderstatusList.add(orderStatus);
    }
    
   public void removeOrderStatus(OrderStatus orderStatus){
        orderstatusList.remove(orderStatus);
    }

    public String getUuid() {
        return internId;
    }
   
    @Override
    public String toString() {
        return "Purchase Id :" + purId + "\n"
                + "Internal Id : " + internId + "\n"
                + "customer : " + customer + "\n"
                + "Shipping Cost : " + shippingCostId + "\n"
                + "Delivery Address Id : " + addDelivery + "\n"
                + "Invoice Address Id : " + addInvoice + "\n"
                + "Purchase IP : " + purIP + "\n"
                + "Shipping Date : " + shippingDate + "\n"
                + "Shipping Number : " + shippingNumber;
    }

}
