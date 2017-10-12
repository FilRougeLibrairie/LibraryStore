package process;

import entity.ItemCart;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class GestionPanier implements Serializable {

    HashMap<String, ItemCart> map;

    public GestionPanier() {
        this.map = new HashMap();
    }

    public void add(String ref, int qty) {
        if (this.map.containsKey(ref)) {
            ItemCart i = this.map.get(ref);
            i.delta(qty);
            if (i.getQty() < 1) {
                del(ref);
            }
        } else {
            this.map.put(ref, new ItemCart(ref, qty));
        }
    }

    public void add(String ref) {
        add(ref, +1);
    }

    public void dec(String ref) {
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

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Collection<ItemCart> list() {
        return this.map.values();
    }

    public float getTotalHT() {
        return -1;
    }

    public float getTotalTTC() {
        return -1;
    }

}