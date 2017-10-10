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
public class Possession {

    private String forName;
    private String booIsbn13;

    //Constructor
    public Possession() {
    }

    //Setters
    public void setForName(String forName) {
        this.forName = forName;
    }

    public void setBooIsbn13(String booIsbn13) {
        this.booIsbn13 = booIsbn13;
    }

    //Getters
    public String getForName() {
        return forName;
    }

    public String getBooIsbn13() {
        return booIsbn13;
    }

}
