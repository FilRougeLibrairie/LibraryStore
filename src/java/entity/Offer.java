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
public class Offer {

    private int offId;
    private String offName;
    private String offText;
    private String offDateStart;
    private String offDateEnd;
    private Float offDiscount;
    private String offPicture;
    private Vector<Book> booksList;
    private int offStatus;

    //Constructor
    public Offer() {
    }

    

    public Offer(int offId, String offName, String offText, String offDateStart, String offDateEnd, Float offDiscount, String offPicture, int offStatus) {
        this.offId = offId;
        this.offName = offName;
        this.offText = offText;
        this.offDateStart = offDateStart;
        this.offDateEnd = offDateEnd;
        this.offDiscount = offDiscount;
        this.offPicture = offPicture;
        this.offStatus = offStatus;
    }

    
    
    public Vector<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(Vector<Book> booksList) {
        this.booksList = booksList;
    }

    public int getOffStatus() {
        return offStatus;
    }

    public void setOffStatus(int offStatus) {
        this.offStatus = offStatus;
    }

   
    
    
    
    

    //Setters
    public void setOffId(int offId) {
        this.offId = offId;
    }

    public void setOffName(String offName) {
        this.offName = offName;
    }

    public void setOffText(String offText) {
        this.offText = offText;
    }

    public void setOffDateStart(String offDateStart) {
        this.offDateStart = offDateStart;
    }

    public void setOffDateEnd(String offDateEnd) {
        this.offDateEnd = offDateEnd;
    }

    public void setOffDiscount(Float offDiscount) {
        this.offDiscount = offDiscount;
    }

    public void setOffPicture(String offPicture) {
        this.offPicture = offPicture;
    }

    public void setBooks(Vector<Book> books) {
        this.booksList = books;
    }

    //Getters
    public int getOffId() {
        return offId;
    }

    public String getOffName() {
        return offName;
    }

    public String getOffText() {
        return offText;
    }

    public String getOffDateStart() {
        return offDateStart;
    }

    public String getOffDateEnd() {
        return offDateEnd;
    }

    public Float getOffDiscount() {
        return offDiscount;
    }

    public String getOffPicture() {
        return offPicture;
    }

    public Vector<Book> getBooks() {
        return booksList;
    }

    public void addBook(Book book) {
        booksList.add(book);
    }

    public void removeBook(Book book) {
        booksList.remove(book);
    }

     //Vector
     public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getOffId());
        v.add(this.getOffDateEnd());
        v.add(this.getOffDateStart());
        v.add(this.getOffDiscount());
        v.add(this.getOffName());
        v.add(this.getOffPicture());
        v.add(this.getOffText());
        v.add(this.getOffStatus());
        
      
        return v;
    }
    
     public Vector getName() {
        Vector v= new Vector();
       
        v.add(this.getOffId());
        v.add(this.getOffName());
        
      
        return v;
    }
    
    
    
    @Override
    public String toString() {
        return 
                 offName 
                
                ;
    }
    
    
   
    
    

}
