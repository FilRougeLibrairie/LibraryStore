package process;

import accessBD.BookDAO;
import entity.Author;
import entity.Book;
import entity.Editor;
import entity.Forma;
import entity.Offer;
import entity.Vat;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import javax.naming.NamingException;
import utils.PriceCalculation;

public class ItemPanier implements Serializable {

    private String isbn;
    private int quantity;
    private int stock;

    private BookDAO bookDAO;
    private Book book;
    private Collection<Author> listAuthors;
    private Editor editor;
    private Vat vat;
    private Offer offer;
    private Forma format;

    public ItemPanier(String ref, int quantity) {
        this.isbn = ref;
        this.quantity = quantity;
    }

    public String getRef() {
        return isbn;
    }

    public void setRef(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isBuyable(){
        return (this.stock > 0)? true : false;
    }

    public void delta(int quantity) {
        this.quantity += quantity;
        if(this.quantity > this.stock){
            this.quantity = this.stock;
        }
    }

    public String getTitle() {
        return this.book.getBooTitle();
    }

    public String getSubTitle() {
        return this.book.getBooSubtitle();
    }

    public String getImageUrl() {
        return this.book.getBooFrontCover();
    }

    public String getPublishDate() throws ParseException {
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/YYYY");
        String reformattedStr = newFormat.format(oldFormat.parse(this.book.getBooPublishYear()));
        return reformattedStr;
    }

    public Collection<Author> getAuthorsList() {
        return listAuthors;
    }

    public String getEditorName() {
        return editor.getEdiName();
    }
    
    public String getFormat(){
        return format.getForName();
    }

    public int getStock() throws NamingException {
        bookDAO = new BookDAO();
        Book book = bookDAO.find(this.isbn);
        this.stock = book.getBooQuantity();
        return this.stock;
    }

    public String getOfferName() {
        return this.offer.getOffName();
    }

    public boolean isDiscounted() {
        return (offer != null && offer.getOffDiscount() > 0) ? true : false;
    }

    public float getDiscount() {
        return (offer != null) ? offer.getOffDiscount() : 0;
    }

    public Float getVat() {
        return vat.getVatRate();
    }

    public float getUnitPriceTTC() {
        float price = PriceCalculation.calculatePriceTTC(this.book.getBooPriceHT(), this.vat.getVatRate());
        return PriceCalculation.getRoundedPrice(price);
    }

    public float getUnitPriceWithDiscount() {
        float unitdiscount = 0;
        if (offer != null) {
            unitdiscount = PriceCalculation.calculateDiscount(this.book.getBooPriceHT(), this.offer.getOffDiscount());
        } else {
            unitdiscount = PriceCalculation.calculateDiscount(this.book.getBooPriceHT(), 0);
        }
        float price = PriceCalculation.calculatePriceTTC(unitdiscount, this.vat.getVatRate());
        return PriceCalculation.getRoundedPrice(price);
    }
    
    protected float getTotalPriceHTWithDiscount(){
        float unitdiscount = 0;
        if (offer != null) {
            unitdiscount = PriceCalculation.calculateDiscount(this.book.getBooPriceHT(), this.offer.getOffDiscount());
        } else {
            unitdiscount = PriceCalculation.calculateDiscount(this.book.getBooPriceHT(), 0);
        }
        float roundedPrice = PriceCalculation.getRoundedPrice(unitdiscount);
        return roundedPrice * this.quantity;
    }

    public float getTotalPriceTTC() {
        float price = PriceCalculation.calculatePriceTTC(this.book.getBooPriceHT(), this.vat.getVatRate()) * this.quantity;
        return PriceCalculation.getRoundedPrice(price);
    }

    public float getTotalPriceTTCWithDiscount() {
        float unitdiscount = 0;
        if (offer != null) {
            unitdiscount = PriceCalculation.calculateDiscount(this.book.getBooPriceHT(), this.offer.getOffDiscount());
        } else {
            unitdiscount = PriceCalculation.calculateDiscount(this.book.getBooPriceHT(), 0);
        }
        float unitTTC = PriceCalculation.calculatePriceTTC(unitdiscount, this.vat.getVatRate());
        float roundedPrice = PriceCalculation.getRoundedPrice(unitTTC);
        return roundedPrice * this.quantity;
    }

    ////////////////// Set objects from DAO
    protected void setBook(Book book) {
        this.book = book;
        this.stock = book.getBooQuantity();
    }

    protected void setListAuthors(Collection<Author> listAuthors) {
        this.listAuthors = listAuthors;
    }

    protected void setEditor(Editor editor) {
        this.editor = editor;
    }

    protected void setOffer(Offer offer) {
        this.offer = offer;
    }

    protected void setVat(Vat vat) {
        this.vat = vat;
    }
    
    protected void setFormat(Forma format) {
        this.format = format;
    }

}
