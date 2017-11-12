package process;

import accessBD.AddressDAO;
import accessBD.BookDAO;
import accessBD.OrderLineDAO;
import accessBD.OrderStatusDAO;
import accessBD.PaymentDAO;
import accessBD.PurchaseDAO;
import entity.Address;
import entity.Book;
import entity.Customer;
import entity.OrderLine;
import entity.Payment;
import entity.Purchase;
import entity.ShippingCost;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import javax.naming.NamingException;

/**
 *
 * @author ggarvanese
 */
public class beanOrder implements Serializable {

    private OrderLineDAO orderLineDAO;
    private PurchaseDAO purchaseDAO;
    private PaymentDAO paymentDAO;
    private OrderStatusDAO orderStatusDAO;
    private BookDAO bookDAO;
    private AddressDAO addressDAO;
    private Collection<ItemPanier> itemList;
    private Customer customer;
    private int invoiceAdressId;
    private int shippingAdressId;
    private int shippingCostId;

    public final int ORDER_STATUS_PROCESS = 1;
    public final int ORDER_STATUS_PAYED = 2;
    public final String PAYMENT_TYPE_CB = "carte bancaire";

    public beanOrder(Collection<ItemPanier> panier, Customer customer) {
        this.itemList = (Collection) panier;
        this.customer = customer;
    }

    public Collection<Address> getAddressList() throws NamingException {
        addressDAO = new AddressDAO();
        Collection addressList = addressDAO.findByCustomerId(customer.getCusID());
        return addressList;
    }

    public int createNewOrder(int customerId, int shipAddressId, int invoiceAddressId, int shipCostId, String ipAddress) throws NamingException {
        purchaseDAO = new PurchaseDAO();
        Customer cus = new Customer();
        Address shipAd = new Address();
        Address invoiceAd = new Address();
        ShippingCost shipCost = new ShippingCost();
        Purchase purchase = new Purchase();

        String internId = UUID.randomUUID().toString();
        purchase.setUuid(internId);
        purchase.setShippingDate(this.getDate().toString());

        cus.setCusID(customerId);
        shipAd.setAddId(shipAddressId);
        invoiceAd.setAddId(invoiceAddressId);
        shipCost.setShipId(shipCostId);

        purchase.setCusId(cus);
        purchase.setAddDeliveryId(shipAd);
        purchase.setAddInvoiceId(invoiceAd);
        purchase.setShippingCostId(shipCost);
        purchase.setPurIP(ipAddress);

        purchaseDAO.create(purchase);

        return findPurchaseId(internId);
    }

    public void saveCart(int purchaseId) throws NamingException, OutOfStockException {
        Book book = new Book();
        Purchase purchase = new Purchase();
        orderLineDAO = new OrderLineDAO();
        bookDAO = new BookDAO();
        OrderLine orderLine = new OrderLine();

        for (ItemPanier item : itemList) {
            book = bookDAO.find(item.getRef());
            purchase.setPurId(purchaseId);
            orderLine.setPurId(purchase);
            orderLine.setBooIsbn13(book);
            orderLine.setOrdLineQuantity(item.getQuantity());
            orderLine.setOrdBookPriceHT(item.getUnitPriceWithDiscount());
            orderLine.setOrdBookVAT(item.getVat());

            book.setBooQuantity(book.getBooQuantity() - orderLine.getOrdLineQuantity());

            if (book.getBooQuantity() >= 0) {
                orderLineDAO.create(orderLine);
                bookDAO.update(book);
            } else {
                throw new OutOfStockException(book.getBooIsbn13());
            }
        }
    }

    public void checkStocks(int purchaseId) throws OutOfStockException, NamingException {
        bookDAO = new BookDAO();
        OrderLine orderLine = new OrderLine();
        Book book;
        for (ItemPanier item : itemList) {
            book = bookDAO.find(item.getRef());
            orderLine.setBooIsbn13(book);
            orderLine.setOrdLineQuantity(item.getQuantity());
            book.setBooQuantity(book.getBooQuantity() - orderLine.getOrdLineQuantity());

            if (book.getBooQuantity() < 0) {
                throw new OutOfStockException(book.getBooIsbn13());
            }
        }
    }
    
    public void setOrderStatus(int purchaseId, int orderStatusCode) throws NamingException {
        orderStatusDAO = new OrderStatusDAO();
        Purchase purchase = new Purchase();
        purchase.setPurId(purchaseId);
        orderStatusDAO.createNewPurchaseStatus(purchase, orderStatusCode, this.getDateTime().toString());
    }

    public void setPaymentStatus(int purchaseId, String paymentType) throws NamingException {
        paymentDAO = new PaymentDAO();
        Payment pay = new Payment();
        Purchase purchase = new Purchase();
        purchase.setPurId(purchaseId);
        pay.setPurId(purchase);
        pay.setPayDate(this.getDateTime().toString());
        pay.setPayChoice(paymentType);
        pay.setPayValidate(Boolean.TRUE);
        paymentDAO.create(pay);
    }

    private java.sql.Timestamp getDateTime() {
        java.util.Date date = new java.util.Date();
        return new java.sql.Timestamp(date.getTime());
    }

    private Date getDate() {
        return Date.valueOf(LocalDate.now());
    }

    private int findPurchaseId(String internId) {
        return purchaseDAO.findPurchaseIdByInternalUUId(internId);
    }

    public int getInvoiceAdressId() {
        return invoiceAdressId;
    }

    public void setInvoiceAdressId(int invoiceAdressId) {
        this.invoiceAdressId = invoiceAdressId;
    }

    public int getShippingAdressId() {
        return shippingAdressId;
    }

    public void setShippingAdressId(int shippingAdressId) {
        this.shippingAdressId = shippingAdressId;
    }

    public int getShippingCostId() {
        return shippingCostId;
    }

    public void setShippingCostId(int shippingCostId) {
        this.shippingCostId = shippingCostId;
    }

}
