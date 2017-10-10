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
public class OrderLine {

    private int ordLineId;
    private int ordLineQuantity;
    private Float ordBookPriceHT;
    private Float ordBookVAT;
    private Purchase purchase;
    private Book book;
    private boolean isNewToCart;

    //contstructor
    public OrderLine() {
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isIsNewToCart() {
        return isNewToCart;
    }

    public void setIsNewToCart(boolean isNewToCart) {
        this.isNewToCart = isNewToCart;
    }

    //Setters
    public void setOrdLineId(int ordLineId) {
        this.ordLineId = ordLineId;
    }

    public void setPurId(Purchase purId) {
        this.purchase = purId;
    }

    public void setBooIsbn13(Book booIsbn13) {
        this.book = booIsbn13;
    }

    public void setOrdLineQuantity(int ordLineQuantity) {
        this.ordLineQuantity = ordLineQuantity;
    }

    public void setOrdBookPriceHT(Float ordBookPriceHT) {
        this.ordBookPriceHT = ordBookPriceHT;
    }

    public void setOrdBookVAT(Float ordBookVAT) {
        this.ordBookVAT = ordBookVAT;
    }

    //Getters
    public int getOrdLineId() {
        return ordLineId;
    }

    public Purchase getPurId() {
        return purchase;
    }

    public Book getBooIsbn13() {
        return book;
    }

    public int getOrdLineQuantity() {
        return ordLineQuantity;
    }

    public Float getOrdBookPriceHT() {
        return ordBookPriceHT;
    }

    public Float getOrdBookVAT() {
        return ordBookVAT;
    }

    @Override
    public String toString() {
        return "OrderLine{" + "ordLineId=" + ordLineId + ", ordLineQuantity=" + ordLineQuantity + ", ordBookPriceHT=" + ordBookPriceHT + ", ordBookVAT=" + ordBookVAT + ", purId=" + purchase + ", booIsbn13=" + book + '}';
    }
    
    

}
