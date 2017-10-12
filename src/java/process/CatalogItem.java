package process;

import entity.Author;
import entity.Book;
import entity.Editor;
import entity.Vat;
import java.io.Serializable;
import java.util.List;


public class CatalogItem implements Serializable {
    
    private Book book;
    private List<Author> listAuthors;
    private Editor editor;
    private Vat vat;

    
    
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

    public float getVatRate(){
        return vat.getVatRate();
    }
    
    public String getEditorName(){
        return editor.getEdiName();
    }
    
    // GETTERS SETTERS

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Author> getListAuthors() {
        return listAuthors;
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

    @Override
    public String toString() {
        return "CatalogItem{" + "book=" + book + '}';
    }
    
    
    

}
