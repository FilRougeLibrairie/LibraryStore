
package entity.webPage;

import entity.Author;
import entity.Book;
import java.io.Serializable;
import java.util.List;



public class AuthorElement implements Serializable{
    
    private Author author;
    private List<Book> listBook;

    public String getAuthor() {
        return author.getAutFirstName()+" "+author.getAutLastName();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }
    
    public int getAuthorId() {
        return author.getAutId();
    }
    
    public void setAuthorId(Author author) {
        this.author = author;
    }
    
}
