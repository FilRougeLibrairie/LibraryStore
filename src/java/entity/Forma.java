
package entity;

import java.util.Vector;


public class Forma {
    
    private String forName;
    private int forId;
    private int forStatus;
    
    //Constructor

    public Forma() {
    }

    public Forma(String forName) {
        this.forName = forName;
    }

    public Forma(int forId,String forName ) {
        this.forId = forId;
        this.forName = forName;
    }

    public Forma(String forName, int forId, int forStatus) {
        this.forName = forName;
        this.forId = forId;
        this.forStatus = forStatus;
    }

    
    
   
    //Setters

    public void setForName(String forName) {
        this.forName = forName;
    }

    public void setForId(int forId) {
        this.forId = forId;
    }

    public void setForStatus(int forStatus) {
        this.forStatus = forStatus;
    }
    
    
    
    //Getters

    public String getForName() {
        return forName;
    }

    public int getForId() {
        return forId;
    }

    public int getForStatus() {
        return forStatus;
    }
    
    public Vector getVectorName() {
        Vector v= new Vector();
       
        v.add(this.getForName());
        
        return v;
    }
    
    
    
      public Vector getVector() {
        Vector v= new Vector();
       v.add(this.getForId());
        v.add(this.getForName());
        v.add(this.getForStatus()); 
        return v;
    }

    @Override
    public String toString() {
        return forName;
    }
    
   
    
    
    
}
