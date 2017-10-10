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
public class Theme {

    private int theId;
    private String theName;
    private String theDescription;
    private int status;

    //Constructor
    public Theme() {
    }

    public Theme(String theName) {
        this.theName = theName;
    }

    

    public Theme(int theId) {
        this.theId = theId;
    }
    
    

    public Theme(int theId, String theName, String theDescription) {
        this.theId = theId;
        this.theName = theName;
        this.theDescription = theDescription;
    }

    public Theme(int theId, String theName, String theDescription, int status) {
        this.theId = theId;
        this.theName = theName;
        this.theDescription = theDescription;
        this.status = status;
    }

   
    
    

    public Theme(String theName, String theDescription) {
        this.theName = theName;
        this.theDescription = theDescription;
    }

    
    
    
    //Setters
    public void setTheId(int theId) {
        this.theId = theId;
    }

    public void setTheName(String theName) {
        this.theName = theName;
    }

    public void setTheDescription(String theDescription) {
        this.theDescription = theDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    

    //Getters
    public int getTheId() {
        return theId;
    }

    public String getTheName() {
        return theName;
    }

    public String getTheDescription() {
        return theDescription;
    }
    
    
    //Vecteur

    
    public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getTheId());
        v.add(this.getTheName());
        v.add(this.getTheDescription());
        v.add(this.getStatus());
      
        return v;
    }
    
    public Vector getName(){
        
        Vector v= new Vector();
        v.add(this.getTheName());
        return v;
    }
    
    
    
    
     // toString
    @Override
    public String toString() {
        return "\n"
                +  theName 
                
                ;
    }
    
    
    

}
