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
public class Association {

    private String booIsbn13;
    private int subId;

    //Constructor
    public Association() {
    }

    //Setters
    public void setBooIsbn13(String booIsbn13) {
        this.booIsbn13 = booIsbn13;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    //Getters
    public String getBooIsbn13() {
        return booIsbn13;
    }

    public int getSubId() {
        return subId;
    }

}
