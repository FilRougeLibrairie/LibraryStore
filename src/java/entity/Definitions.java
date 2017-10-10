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
public class Definitions {
    
    private String keyName;
    private String booIsbn13;
    
    
    //Constructor

    public Definitions() {
    }
    
    //Setters

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public void setBooIsbn13(String booIsbn13) {
        this.booIsbn13 = booIsbn13;
    }
    
    //Getters

    public String getKeyName() {
        return keyName;
    }

    public String getBooIsbn13() {
        return booIsbn13;
    }
    
}
