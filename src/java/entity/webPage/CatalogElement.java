package entity.webPage;

import entity.Author;
import entity.Book;
import entity.BookLanguage;
import entity.Editor;
import entity.Forma;
import entity.Offer;
import entity.Review;
import entity.Vat;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CatalogElement implements Serializable {

    private Book book;
    private List<Author> listAuthors;
    private Editor editor;
    private Vat vat;
    private Forma forma;
    private BookLanguage bookLanguage;
    private Offer discount;
    private List<Review> listReview;
    private Author author;

    public CatalogElement() {

    }

    public String getBookIsbn() {
        return book.getBooIsbn13();
    }

    public String getBookTitle() {
        return book.getBooTitle();
    }

    public String getImageURL() {
        return book.getBooFrontCover();
    }

    public float getPriceHT() {
        return book.getBooPriceHT();
    }

    public String getBooResume() {
        return book.getBooResume();
    }

    public int getBooPageNumber() {
        return book.getBooPageNumber();
    }

    public int getBooQuantity() {
        return book.getBooQuantity();
    }

    public String getBooPublishYear() {
        return book.getBooPublishYear();
    }

    public String getBooLangName() {
        return bookLanguage.getBooLangName();
    }

    public String getFormat() {
        return forma.getForName();
    }

    public String getPriceTTC() {

        Float response = this.getPriceHT() * (1 + this.getVatRate() / 100);
        DecimalFormat priceTTC = new DecimalFormat();
        priceTTC.setMaximumFractionDigits(2);
        priceTTC.setMinimumFractionDigits(2);
        priceTTC.setDecimalSeparatorAlwaysShown(true);
        String p = priceTTC.format(response);

        return p;
    }

    public float getVatRate() {
        return vat.getVatRate();
    }

    public String getEditorName() {
        return editor.getEdiName();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setListAuthos(List<Author> listReview) {
        this.listAuthors = listAuthors;
    }

    public List<Author> getListAuthors() {

        return listAuthors;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    
    
    
//    public String getAuthor() {
//
//        List author = new ArrayList();
//        List<Author> authors = this.getListAuthors();
//        for (Author a : authors) {
//            a.getAutFirstName();
//            a.getAutLastName();
//            author.add(a);
//        }
//
//        String r = null;
//
//        if (author.size() > 1) {
//            r = author.get(0).toString() + " , " + author.get(1);
//        }
//
//        if (author.size() == 1) {
//            r = author.get(0).toString();
//        } else {
//r=null;
//        }
//        return r;
//    }
    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public void setBookLanguage(BookLanguage bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public void setDiscount(Offer discount) {
        this.discount = discount;
    }

    public Float getDiscount() {
        return discount.getOffDiscount();
    }

    public Float getPriceTTCWithoutDiscount() {

        Float priceHT = this.getPriceHT();
        Float discount = this.getDiscount();

        Float price100 = priceHT * (1 - (discount / 100));

        return ((int) (price100 * 100) / 100.00f);

    }

    // getReview
    public void setListReview(List<Review> listReview) {
        this.listReview = listReview;
    }

    public List<Review> getListReview() {
        return listReview;
    }

    public Float getReviewByBook() {
        Float somme = 0f;
        int i = 0;
        List<Review> rev = this.getListReview();
        for (Review review : rev) {
            review.getRevNote();
            somme = review.getRevNote() + somme;
            i = i + 1;
        }
        Float moyenne = somme / i;

        return moyenne;

    }

// Author
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

    @Override
    public String toString() {
        return book + " " + vat + " " + listAuthors;
    }
}
