/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * @author Tofi
 */
public class Review {

    private int revId;
    private Customer customer;
    private String booIsbn13;
    private OrderLine orderLine;
    private Float revNote;
    private String revComment;
    private java.sql.Date revDate;
    private String revIP;
    private int revStatus;
    private Book book;

    
    // constructeur

    public Review(int revId) {
        this.revId = revId;
    }

    public Review() {
    }
    
     public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getRevId());
     
        return v;
    }
    

    //Setters
    public void setRevId(int revId) {
        this.revId = revId;
    }

    public void setCusId(Customer cusId) {
        this.customer = cusId;
    }

    public void setBooIsbn13(String booIsbn13) {
        this.booIsbn13 = booIsbn13;
    }

    public void setOrdLineId(OrderLine ordLineId) {
        this.orderLine = ordLineId;
    }

    public void setRevNote(Float revNote) {
        this.revNote = revNote;
    }

    public void setRevComment(String revComment) {
        this.revComment = revComment;
    }

    public void setRevDate(String revDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date reviewDate = sdf.parse(revDate);
            java.sql.Date sqlRevDate = new java.sql.Date(reviewDate.getTime());
            this.revDate = sqlRevDate;
        } catch (ParseException ex) {
            System.out.println("Error formating DATE" + ex.getMessage());
        }
    }

    public void setRevIP(String revIP) {
        this.revIP = revIP;
    }

    public void setRevStatus(int revStatus) {
        this.revStatus = revStatus;
    }
    
    
    
    //Getters
    public int getRevId() {
        return revId;
    }

    public Customer getCusId() {
        return customer;
    }

    public String getBooIsbn13() {
        return booIsbn13;
    }

    public OrderLine getOrdLineId() {
        return orderLine;
    }

    public Float getRevNote() {
        return revNote;
    }

    public String getRevComment() {
        return revComment;
    }

    public java.sql.Date getRevDate() {
        return new java.sql.Date((revDate.getTime()));
    }

    public String getRevIP() {
        return revIP;
    }

    public int getRevStatus() {
        return revStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Review : " + revId + "\n"
                + "Customer Id : " + customer + "\n"
                + "ISBN13 : " + booIsbn13 + "\n"
                + "Orderline Id : " + orderLine + "\n"
                + "Score : " + revNote + "\n"
                + "Comment : " + revComment + "\n"
                + "Date : " + revDate + "\n"
                + "IP adress : " + revIP + "\n"
                + "Review Status : " + revStatus;
    }
    
    


    
    
    
}
