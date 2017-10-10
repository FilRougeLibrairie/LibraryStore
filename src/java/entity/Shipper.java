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
public class Shipper {

    private int shipperId;
    private String ShipperName;

    //Constructor
    public Shipper() {
    }

    //Setters
    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public void setShipperName(String ShipperName) {
        this.ShipperName = ShipperName;
    }

    //Getters
    public int getShipperId() {
        return shipperId;
    }

    public String getShipperName() {
        return ShipperName;
    }

    @Override
    public String toString() {
        return "Shipper{" + "shipperId=" + shipperId + ", ShipperName=" + ShipperName + '}';
    }
    
    

}
