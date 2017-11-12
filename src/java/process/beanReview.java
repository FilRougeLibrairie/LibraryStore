/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import accessBD.OrderLineDAO;
import accessBD.PurchaseDAO;
import accessBD.ReviewDAO;
import entity.Customer;
import entity.OrderLine;
import entity.Purchase;
import entity.Review;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import javax.naming.NamingException;

/**
 *
 * @author ggarvanese
 */
public class beanReview implements Serializable {

    public final int REVIEW_STATUS_PENDING = 2;

    OrderLineDAO orderLineDAO;
    ReviewDAO reviewDAO;
    PurchaseDAO purchaseDAO;

    int orderLineId;
    int customerId;
    String isbn;

    public beanReview() throws NamingException {
        orderLineDAO = new OrderLineDAO();
        reviewDAO = new ReviewDAO();
        purchaseDAO = new PurchaseDAO();
    }

    public boolean isBookPayed(int customerId, String isbn) {
        this.customerId = customerId;
        this.isbn = isbn;
        Collection<Purchase> listPurchase = purchaseDAO.findByCustomerId(this.customerId);
        if (!listPurchase.isEmpty()) {
            for (Purchase p : listPurchase) {
                Collection<OrderLine> listOrderLine = orderLineDAO.findByColumn("purId", p.getPurId());
                if (!listOrderLine.isEmpty()) {
                    for (OrderLine o : listOrderLine) {
                        if (this.isbn.equalsIgnoreCase(o.getBooIsbn13().getBooIsbn13())) {
                            this.orderLineId = o.getOrdLineId();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isBookedReviewed(int customerId, String isbn) {
        Collection<Review> listReview = reviewDAO.findByCustomer(customerId);
        if (!listReview.isEmpty()) {
            for (Review r : listReview) {
                if (isbn.equalsIgnoreCase(r.getBooIsbn13())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateReview(int customerId, String isbn, String revNote, String comment, int revStatus, String ipAddress) {
        Review review = new Review();
        Customer customer = new Customer();
        customer.setCusID(customerId);
        Date currentDate = Date.valueOf(LocalDate.now());
        Review currentReview = reviewDAO.findReview(customerId, isbn);

        review.setRevId(currentReview.getRevId());
        review.setOrdLineId(currentReview.getOrdLineId());
        review.setCusId(customer);
        review.setBooIsbn13(isbn);
        review.setRevNote(Float.valueOf(revNote));
        review.setRevComment(comment.trim());
        review.setRevDate(currentDate.toString());
        review.setRevIP(ipAddress.trim());
        review.setRevStatus(revStatus);

        reviewDAO.update(review);
    }

    public void saveReview(String revNote, String comment, int revStatus, String ipAddress) {
        OrderLine orderLine = new OrderLine();
        Customer customer = new Customer();
        Review review = new Review();
        orderLine.setOrdLineId(orderLineId);
        customer.setCusID(customerId);
        Date currentDate = Date.valueOf(LocalDate.now());

        review.setOrdLineId(orderLine);
        review.setCusId(customer);
        review.setBooIsbn13(this.isbn);
        review.setRevNote(Float.valueOf(revNote));
        review.setRevComment(comment.trim());
        review.setRevDate(currentDate.toString());
        review.setRevIP(ipAddress.trim());
        review.setRevStatus(revStatus);

        reviewDAO.create(review);
    }

    public Review getReview(int customerId, String isbn) {
        return reviewDAO.findReview(customerId, isbn);
    }

}
