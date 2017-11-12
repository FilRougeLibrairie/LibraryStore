package process;

import accessBD.ShippingCostDAO;
import entity.ShippingCost;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.NamingException;

/**
 *
 * @author ggarvanese
 */
public class beanShipping implements Serializable {

    ShippingCostDAO shipDAO;
    Collection<ShippingCost> shipList;

    public beanShipping() throws NamingException {
        shipDAO = new ShippingCostDAO();
        shipList = new ArrayList<ShippingCost>();
    }

    public Collection<ShippingCost> getList() {
        shipList = shipDAO.findAll();
        return shipList;
    }

    public int getSize() {
        return shipList.size();
    }

    public ShippingCost getShipTypeById(int shipId) {
        if (!this.shipList.isEmpty()) {
            for (ShippingCost s : this.shipList) {
                if (s.getShipId() == shipId) {
                    return s;
                }
            }
        }
        return null;
    }
}
