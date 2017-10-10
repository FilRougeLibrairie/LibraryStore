
package entity;


public class Having {
    
    private String booIsbn13;
    private int autId;
    
    //Constructor

    public Having() {
    }

    public Having(String booIsbn13, int autId) {
        this.booIsbn13 = booIsbn13;
        this.autId = autId;
    }
    
   
    
    
    //Setters

    public void setBooIsbn13(String booIsbn13) {
        this.booIsbn13 = booIsbn13;
    }

    public void setAutId(int autId) {
        this.autId = autId;
    }
    
    //Getters

    public String getBooIsbn13() {
        return booIsbn13;
    }

    public int getAutId() {
        return autId;
    }
    

    
}
