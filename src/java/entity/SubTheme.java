
package entity;

import java.util.Vector;


public class SubTheme {

    private int subId;
    private Theme theId;
    private String subName;
    private String subDescription;
    private Vector<Book> bookList;
    private int subStatus;
            

    //Constructor
    public SubTheme() {
    }

    public SubTheme(int subId, Theme theId, String subName, String subDescription) {
        this.subId = subId;
        this.theId = theId;
        this.subName = subName;
        this.subDescription = subDescription;
    }

    public SubTheme(int subId, Theme theId, String subName, String subDescription, int subStatus) {
        this.subId = subId;
        this.theId = theId;
        this.subName = subName;
        this.subDescription = subDescription;
        this.bookList = bookList;
        this.subStatus = subStatus;
    }
    
    

    public SubTheme(int subId, Theme theId, String subName, String subDescription, Vector<Book> bookList, int subStatus) {
        this.subId = subId;
        this.theId = theId;
        this.subName = subName;
        this.subDescription = subDescription;
        this.bookList = bookList;
        this.subStatus = subStatus;
    }

    public SubTheme(String subName) {
        this.subName = subName;
    }

    public SubTheme(int subId) {
        this.subId = subId;
    }
    
    
    
    //Setters
    public void setSubId(int subId) {
        this.subId = subId;
    }

    public void setTheId(Theme theId) {
        this.theId = theId;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    public void setBookList(Vector<Book> bookList) {
        this.bookList = bookList;
    }

    public int getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(int subStatus) {
        this.subStatus = subStatus;
    }

    
    
    
    //Getters
    public int getSubId() {
        return subId;
    }

    public Theme getTheId() {
        return theId;
    }

    public String getSubName() {
        return subName;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public Vector<Book> getBookList() {
        return bookList;
    }
    
    public void addBook(Book book){
        bookList.add(book);
    }
    
    public void removeBook(Book book){
        bookList.remove(book);
    }
    
    
    public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getSubId());
        v.add(this.getTheId());
        v.add(this.getSubName());
        v.add(this.getSubDescription());
        v.add(this.getSubStatus());
      
        return v;
    }
    
    public Vector getName() {
        Vector v= new Vector();
       
        v.add(this.getSubName());
        return v;
    }
    
    public Vector getVectorId(){
        Vector v= new Vector();
        v.add(this.getSubId());
      
        return v;
        
    }
    
    //to string

    @Override
    public String toString() {
        return "\n"+subId + " "
                +  subName 
//      return  subName
                 ;
    }
    
    
    
    

}
