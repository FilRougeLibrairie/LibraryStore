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
public class Author {

    private int autId;
    private Vector<Book> bookList;
    private String autLastName;
    private String autFirstName;
    private String autBiography;
    private int autStatusCode;

    //Constructor
    public Author() {
    }

    public Author(int autId, String autLastName, String autFirstName, String autBiography, int autStatusCode) {
        this.autId = autId;
        this.autLastName = autLastName;
        this.autFirstName = autFirstName;
        this.autBiography = autBiography;
        this.autStatusCode = autStatusCode;
    }

    public Author(int autId) {
        this.autId = autId;
    }

    public Author(String autLastName) {
        this.autLastName = autLastName;
    }
    
    

    //Setters
    public void setAutId(int autId) {
        this.autId = autId;
    }
    
     public Vector<Book> getBookList() {
        return bookList;
    }

    public void setAutLastName(String autLastName) {
        this.autLastName = autLastName.trim();
    }

    public void setAutFirstName(String autFirstName) {
        this.autFirstName = autFirstName;
    }

    public void setAutBiography(String autBiography) {
        this.autBiography = autBiography;
    }

    public void setAutStatusCode(int autStatusCode) {
        this.autStatusCode = autStatusCode;
    }

    //Getters
    public int getAutId() {
        return autId;
    }
    
    public void setBookList(Vector<Book> booIsbn13) {
        this.bookList = booIsbn13;
    }

    public String getAutLastName() {
        return autLastName;
    }

    public String getAutFirstName() {
        return autFirstName;
    }

    public String getAutBiography() {
        return autBiography;
    }

    public int getAutStatusCode() {
        return autStatusCode;
    }
    
    // Add / Remove Object into Vector
    public void addBook(Book book){
        bookList.add(book);
    }
    
    public void removeBook(Book book){
        bookList.remove(book);
    }
    
    public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getAutId());
        v.add(this.getAutLastName());
        v.add(this.getAutFirstName());
        v.add(this.getAutBiography());
        v.add(this.getAutStatusCode());
      
        return v;
    }
    
    
    public Vector getVectorName() {
        Vector v= new Vector();
       
        
        v.add(this.getAutLastName());
        
        
      
        return v;
    }
    
    
    

    @Override
    public String toString() {
        return autLastName + " " +  autFirstName ;
    }
    
    
    
}


