

package entity;

import java.util.Vector;


public class Editor {

    private int ediId;
    private String ediName;
    private String ediPresentation;
    private int ediStatusCode;
    private Vector<Book> bookList;
    
    
       public Editor(int ediId, String ediName, String ediPresentation, int ediStatusCode) {
        this.ediId = ediId;
        this.ediName = ediName;
        this.ediPresentation = ediPresentation;
        this.ediStatusCode = ediStatusCode;
    }
    
    //Constructor
    public Editor() {
    }

    //Setters
    
    public void setEdiId(int ediId) {
        this.ediId = ediId;
    }

    public void setEdiName(String ediName) {
        this.ediName = ediName;
    }

    public void setEdiPresentation(String ediPresentation) {
        this.ediPresentation = ediPresentation;
    }

    public void setEdiStatusCode(int ediStatusCode) {
        this.ediStatusCode = ediStatusCode;
    }

     public Vector<Book> getBookList() {
        return bookList;
    }

    public Vector getVector() {
        Vector v= new Vector();
       
        v.add(this.getEdiId());
        v.add(this.getEdiName());
        v.add(this.getEdiPresentation());
        v.add(this.getEdiStatusCode());
      
        return v;
    }
    
    //Getters
    
    public int getEdiId() {
        return ediId;
    }

    public String getEdiName() {
        return ediName;
    }

    
    public String getEdiPresentation() {
        return ediPresentation;
    }

    public int getEdiStatusCode() {
        return ediStatusCode;
    }
    
    public String EdiStatusCode() {
        String Statut=null;
        if(ediStatusCode == 0){
            Statut = "Actif";
        }else if(ediStatusCode == 1){
            Statut = "Inactif";
        }
        return Statut;
    }
    
    //To String
    
    public String toString() {
        return ediName ;
    }

    


}
