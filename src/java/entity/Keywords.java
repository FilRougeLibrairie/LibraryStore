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
public class Keywords {

    private String keyName;

    //Constructor
    public Keywords() {
    }
    
     public Keywords(String keyName) {
        this.keyName = keyName;
    }

    //Setters
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    //Getters
    public String getKeyName() {
        return keyName;
    }
    
    //Vector
     public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.keyName);
      
        return v;
    }
}
