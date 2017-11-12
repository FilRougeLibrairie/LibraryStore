
package entity.webPage;

import entity.Author;
import entity.Book;
import entity.Editor;
import entity.Offer;
import entity.Vat;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class OfferElement implements Serializable{
    
    
    private Book book;
    private List<Author> listAuthors;
    private Editor editor;
    private Vat vat;
    private Offer offer;
    private List<Offer> listOffer;
    private List<Book> listBook;

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }
    
    
    public List<Book> getListBooks() {
   
        return listBook;
    }
    
    
    
    public int getOffId() {
        return offer.getOffId();
    }

    public String getOffName() {
        return offer.getOffName();
    }

    public String getOffText() {
        return offer.getOffText();
    }

    public String getOffDateStart() {
        return offer.getOffDateStart();
    }

    public String getOffDateEnd() {
        return offer.getOffDateEnd();
    }

    public Float getOffDiscount() {
        return offer.getOffDiscount();
    }

    public String getOffPicture() {
        return offer.getOffPicture();
    }

    public List<Offer> getListOffer() {
        return listOffer;
    }

    public void setListOffer(List<Offer> listOffer) {
        this.listOffer = listOffer;
    }
    
    
    public String getBookIsbn(){
        return book.getBooIsbn13();
    }
    
    public String getBookTitle(){
        return book.getBooTitle();
    }
    
    public String getImageURL(){
        return book.getBooFrontCover();
    }
    
    public float getPriceHT(){
        return book.getBooPriceHT();
    }
    
    public String getPriceTTC(){
        
        Float response=this.getPriceHT()*(1+this.getVatRate()/100);
        DecimalFormat priceTTC = new DecimalFormat ( ) ;
        priceTTC.setMaximumFractionDigits (2); 
        priceTTC.setMinimumFractionDigits (2);
        priceTTC.setDecimalSeparatorAlwaysShown (true);
        String p=priceTTC.format(response);
    
        return p;
    }

    public float getVatRate(){
        return vat.getVatRate();
    }
    
    
    
    public String getEditorName(){
        return editor.getEdiName();
    }
    

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Author> getListAuthors() {
   
        return listAuthors;
    }
    
    public String getAuthor(){
        
        List author = new ArrayList();
        List<Author> authors =this.getListAuthors();
        for(Author a : authors){
            a.getAutFirstName();
            a.getAutLastName();
            author.add(a);
        } 
        String r=null;
        
        if(author.size()!=1){
        r=author.get(0).toString() + " , " +author.get(1) ;
        }
        else{
        r=author.get(0).toString();
        }
        return r;
    }
    

    public void setListAuthors(List<Author> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    
    
    
    
    @Override
    public String toString() {
        return "OfferElement{" + "book=" + book + ", listAuthors=" + listAuthors + ", editor=" + editor + ", vat=" + vat + ", offer=" + offer + ", listOffer=" + listOffer + '}';
    }

    
    
    
    
}
