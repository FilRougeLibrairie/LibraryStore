package process;

import accessBD.AuthorDAO;
import accessBD.BookDAO;
import accessBD.EditorDAO;
import accessBD.FormatsDAO;
import accessBD.OfferDAO;
import accessBD.VatDAO;
import entity.Book;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.naming.NamingException;
import utils.PriceCalculation;

public class beanPanier implements Serializable {

    HashMap<String, ItemPanier> map;
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private EditorDAO editorDAO;
    private VatDAO vatDAO;
    private OfferDAO offerDAO;
    private FormatsDAO formatDAO;
    private float shipCost;

    public beanPanier() {
        this.map = new HashMap();
    }

    public void add(String ref, int quantity) throws NamingException {
        if (this.map.containsKey(ref)) {
            ItemPanier i = this.map.get(ref);
            i.delta(quantity);
            if (i.getQuantity() < 1) {
                del(ref);
            }
        } else {
            createNewItem(ref, quantity);
        }
    }

    public void add(String ref) throws NamingException {
        add(ref, +1);
    }

    public void dec(String ref) throws NamingException {
        add(ref, -1);
    }

    public void del(String ref) {
        this.map.remove(ref);
    }

    public void clear() {
        this.map.clear();
    }

    public int getSize() {
        return this.map.size();
    }

    public int getTotalItemsCount() {
        int itemCount = 0;
        Set<HashMap.Entry<String, ItemPanier>> entrees = map.entrySet();
        Iterator it = entrees.iterator();
        while (it.hasNext()) {
            HashMap.Entry<String, ItemPanier> entree = (HashMap.Entry) it.next();
            if (entree.getValue().isBuyable()) {
                itemCount += entree.getValue().getQuantity();
            }
        }
        return itemCount;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Collection<ItemPanier> list() {
        return this.map.values();
    }

    public float getTotalHT() {
        float totalHT = 0;
        Set<HashMap.Entry<String, ItemPanier>> entrees = map.entrySet();
        Iterator it = entrees.iterator();
        while (it.hasNext()) {
            HashMap.Entry<String, ItemPanier> entree = (HashMap.Entry) it.next();
            if (entree.getValue().isBuyable()) {
                totalHT += entree.getValue().getTotalPriceHTWithDiscount();
            }
        }
        return PriceCalculation.getRoundedPrice(totalHT);
    }

    public float getTotalTTC() {
        float totalTTC = 0;
        Set<HashMap.Entry<String, ItemPanier>> entrees = map.entrySet();
        Iterator it = entrees.iterator();
        while (it.hasNext()) {
            HashMap.Entry<String, ItemPanier> entree = (HashMap.Entry) it.next();
            if (entree.getValue().isBuyable()) {
                totalTTC += entree.getValue().getTotalPriceTTCWithDiscount();
            }
        }
        return PriceCalculation.getRoundedPrice(totalTTC);
    }
    
    public float getTotalWithShipCost(){
        return PriceCalculation.getRoundedPrice(this.shipCost + this.getTotalTTC());
    }

    public float getTotalVAT() {
        return PriceCalculation.getRoundedPrice(getTotalTTC() - getTotalHT());
    }

    private void createNewItem(String isbn, int quantity) throws NamingException {
        this.bookDAO = new BookDAO();
        this.authorDAO = new AuthorDAO();
        this.editorDAO = new EditorDAO();
        this.offerDAO = new OfferDAO();
        this.vatDAO = new VatDAO();
        this.formatDAO = new FormatsDAO();
        ItemPanier item = new ItemPanier(isbn, quantity);

        Book book = bookDAO.find(isbn);
        item.setBook(book);
        item.setEditor(editorDAO.findById(book.getEdiId().getEdiId()));
        item.setVat(vatDAO.find(book.getVatCode().getVatCode()));
        item.setListAuthors(authorDAO.findAuthorByBook(isbn));
        item.setOffer(offerDAO.findCurrentOfferByBook(isbn));
        item.setFormat(formatDAO.find(book.getFormat().getForId()));

        this.map.put(isbn, item);

    }

    public void setShippingCost(float shipCost) {
        this.shipCost = shipCost;
    }

}
