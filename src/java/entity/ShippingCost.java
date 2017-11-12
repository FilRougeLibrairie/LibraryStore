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
public class ShippingCost {

    private int shipId;
    private String shipName;
    private Float shipCost;

    //Constructor
    public ShippingCost() {
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

   

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Float getShipCost() {
        return shipCost;
    }

    public void setShipCost(Float shipCost) {
        this.shipCost = shipCost;
    }

    @Override
    public String toString() {
        return "ShippingCost{" + "shipId=" + shipId + ", shipName=" + shipName + ", shipCost=" + shipCost + '}';
    }
}
