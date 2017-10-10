
package entity;

import java.util.Vector;


public class BookLanguage {
    
    private int booLangCode;
    private String booLangName;
    private int booLangStatus;
    
    //Constructor

    public BookLanguage() {
    }

    public BookLanguage(int booLangCode, String booLangName) {
        this.booLangCode = booLangCode;
        this.booLangName = booLangName;
    }

    public BookLanguage(int booLangCode, String booLangName, int booLangStatus) {
        this.booLangCode = booLangCode;
        this.booLangName = booLangName;
        this.booLangStatus = booLangStatus;
    }
    
    
    
    
    //Setters
    
    public void setBooLangCode(int booLangCode) {
        this.booLangCode = booLangCode;
    }

    public void setBooLangName(String booLangName) {
        this.booLangName = booLangName;
    }

    

    public void setBooLangStatus(int booLangStatus) {
        this.booLangStatus = booLangStatus;
    }
    
    
    //Getters
    
    public int getBooLangStatus() {
        return booLangStatus;
    }

    public int getBooLangCode() {
        return booLangCode;
    }

    public String getBooLangName() {
        return booLangName;
    }

    public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getBooLangCode());
        v.add(this.getBooLangName());
        v.add(this.getBooLangStatus());
      
        return v;
    }
    
    
    public Vector getVectorName() {
        Vector v= new Vector();
       
       
        v.add(this.getBooLangName());
      
        return v;
    }
    
    
    
    // to string
    @Override
    public String toString() {
        return booLangName ;
    }
    
    
    
}
