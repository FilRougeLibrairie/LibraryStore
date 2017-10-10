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
public class Have {
    
    private int offId;
    private String booIsbn13;
    
    //Constructor

    public Have() {
    }
    
    //Setters

    public void setOffId(int offId) {
        this.offId = offId;
    }

    public void setBooIsbn13(String booIsbn13) {
        this.booIsbn13 = booIsbn13;
    }
    
    //Getters

    public int getOffId() {
        return offId;
    }

    public String getBooIsbn13() {
        return booIsbn13;
    }
    
    
    
}
