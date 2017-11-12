package accessBD;

//import names.SQLNames.ReviewNames;
import entity.Customer;
import entity.OrderLine;
import entity.Review;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

public class ReviewDAO implements Serializable {

    private MyConnexion mc;
    private final String TABLE = "Review";

    private final String ID = "revId";
    private final String CUSTOMER_ID = "cusId";
    private final String BOOK_ISBN_13 = "booIsbn13";
    private final String ORDERLINE_ID = "ordLineId";
    private final String NOTE = "revNote";
    private final String COMMENT = "revComment";
    private final String DATE = "revDate";
    private final String IP = "revIP";
    private final String STATUS = "revStatus";
    
    private final int REVIEW_STATUS_PUBLISHED = 3;

    private String COLUMNS_CREATE = CUSTOMER_ID + ", " + BOOK_ISBN_13 + ", " + ORDERLINE_ID + ", "
            + NOTE + ", " + COMMENT + ", " + DATE + ", " + IP + ", "
            + STATUS;

    //Constructor
    public ReviewDAO() throws NamingException {
        mc = new MyConnexion();
    }

    public void create(Review obj) {
        Review rev = (Review) obj;
        String query = "IF NOT EXISTS (SELECT * FROM " + TABLE + " WHERE " + ID + " = '" + rev.getRevId() + "')"
                + "INSERT INTO " + TABLE + " (" + COLUMNS_CREATE + ")"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query);) {

            pstmt.setInt(1, rev.getCusId().getCusID());
            pstmt.setString(2, rev.getBooIsbn13());
            pstmt.setInt(3, rev.getOrdLineId().getOrdLineId());
            pstmt.setFloat(4, rev.getRevNote());
            pstmt.setString(5, rev.getRevComment());
            pstmt.setDate(6, rev.getRevDate());
            pstmt.setString(7, rev.getRevIP());
            pstmt.setInt(8, rev.getRevStatus());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("ERROR SAVING Object : " + ex.getErrorCode() + " / " + ex.getMessage());

        }
    }

    public void update(Review review) {
        Review rev = review;
        StringBuilder query = new StringBuilder("UPDATE " + TABLE + " SET ");
        query.append(CUSTOMER_ID).append(" = ?, ");
        query.append(BOOK_ISBN_13).append(" = ?, ");
        query.append(ORDERLINE_ID).append(" = ?, ");
        query.append(NOTE).append(" = ?, ");
        query.append(COMMENT).append(" = ?, ");
        query.append(DATE).append(" = ?, ");
        query.append(IP).append(" = ?, ");
        query.append(STATUS).append(" = ? ");

        query.append("WHERE " + ID + " = ")
                .append(rev.getRevId());

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, rev.getCusId().getCusID());
            pstmt.setString(2, rev.getBooIsbn13());
            pstmt.setInt(3, rev.getOrdLineId().getOrdLineId());
            pstmt.setFloat(4, rev.getRevNote());
            pstmt.setString(5, rev.getRevComment());
            pstmt.setDate(6, rev.getRevDate());
            pstmt.setString(7, rev.getRevIP());
            pstmt.setInt(8, rev.getRevStatus());

            int result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR UPDATING Object : " + ex.getMessage());

        }
    }

    public void delete(Review obj) {
        int revId = ((Review) obj).getRevId();
        StringBuffer query = new StringBuffer();
        query.append("DELETE FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + revId + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
    }

    public Review find(int id) {
        Review review = null;
        Customer cus = null;
        OrderLine ord = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    review = new Review();
                    review.setRevId(rs.getInt(ID));
                    cus = new Customer();
                    cus.setCusID(rs.getInt(CUSTOMER_ID));
                    review.setCusId(cus);
                    review.setBooIsbn13(rs.getString(BOOK_ISBN_13));
                    ord = new OrderLine();
                    ord.setOrdLineId(rs.getInt(ORDERLINE_ID));
                    review.setOrdLineId(ord);
                    review.setRevNote(rs.getFloat(NOTE));
                    review.setRevComment(rs.getString(COMMENT));
                    review.setRevDate(rs.getString(DATE));
                    review.setRevIP(rs.getString(IP));
                    review.setRevStatus(rs.getInt(STATUS));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return review;
    }

    public Vector<Review> findAll() {
        Vector<Review> reviewList = new Vector<Review>();
        Review review = null;
        Customer cus = null;
        OrderLine ord = null;
        String query = "SELECT * FROM " + TABLE;

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    review = new Review();
                    review.setRevId(rs.getInt(ID));
                    cus = new Customer();
                    cus.setCusID(rs.getInt(CUSTOMER_ID));
                    review.setCusId(cus);
                    review.setBooIsbn13(rs.getString(BOOK_ISBN_13));
                    ord = new OrderLine();
                    ord.setOrdLineId(rs.getInt(ORDERLINE_ID));
                    review.setOrdLineId(ord);
                    review.setRevNote(rs.getFloat(NOTE));
                    review.setRevComment(rs.getString(COMMENT));
                    review.setRevDate(rs.getString(DATE));
                    review.setRevIP(rs.getString(IP));
                    review.setRevStatus(rs.getInt(STATUS));
                    reviewList.add(review);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return reviewList;
    }

    public Review find(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vector<Review> findByColumn(String column, String term) {
        Vector<Review> reviewList = new Vector<Review>();
        Review review = null;
        Customer cus = null;
        OrderLine ord = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    review = new Review();
                    review.setRevId(rs.getInt(ID));
                    cus = new Customer();
                    cus.setCusID(rs.getInt(CUSTOMER_ID));
                    review.setCusId(cus);
                    review.setBooIsbn13(rs.getString(BOOK_ISBN_13).trim());
                    ord = new OrderLine();
                    ord.setOrdLineId(rs.getInt(ORDERLINE_ID));
                    review.setOrdLineId(ord);
                    review.setRevNote(rs.getFloat(NOTE));
                    review.setRevComment(rs.getString(COMMENT).trim());
                    review.setRevDate(rs.getString(DATE).trim());
                    review.setRevIP(rs.getString(IP).trim());
                    review.setRevStatus(rs.getInt(STATUS));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return reviewList;
    }

    public int countReviewsByStatus(int statusCode) {
        int numberOfReviews = 0;
        StringBuilder query = new StringBuilder();
        query.append("SELECT " + STATUS + " FROM Review WHERE " + STATUS + " = ?");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, statusCode);

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    numberOfReviews += 1;
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return numberOfReviews;
    }

    public Vector<Review> findByIsbn(String term) {
        Vector<Review> reviewList = new Vector<Review>();
        Review review = null;
        Customer cus = null;
        OrderLine ord = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append("booIsbn13")
                .append(" = ")
                .append("'" + term + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    review = new Review();
                    review.setRevId(rs.getInt(ID));
                    cus = new Customer();
                    cus.setCusID(rs.getInt(CUSTOMER_ID));
                    review.setCusId(cus);
                    review.setBooIsbn13(rs.getString(BOOK_ISBN_13));
                    ord = new OrderLine();
                    ord.setOrdLineId(rs.getInt(ORDERLINE_ID));
                    review.setOrdLineId(ord);
                    review.setRevNote(rs.getFloat(NOTE));
                    review.setRevComment(rs.getString(COMMENT));
                    review.setRevDate(rs.getString(DATE));
                    review.setRevIP(rs.getString(IP));
                    review.setRevStatus(rs.getInt(STATUS));
                    reviewList.add(review);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return reviewList;
    }

    public Vector<Review> findByCustomer(int customerId) {
        Vector<Review> reviewList = new Vector<Review>();
        Review review = null;
        Customer cus = null;
        OrderLine ord = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append("cusId")
                .append(" = ?");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    review = new Review();
                    review.setRevId(rs.getInt(ID));
                    cus = new Customer();
                    cus.setCusID(rs.getInt(CUSTOMER_ID));
                    review.setCusId(cus);
                    review.setBooIsbn13(rs.getString(BOOK_ISBN_13));
                    ord = new OrderLine();
                    ord.setOrdLineId(rs.getInt(ORDERLINE_ID));
                    review.setOrdLineId(ord);
                    review.setRevNote(rs.getFloat(NOTE));
                    review.setRevComment(rs.getString(COMMENT));
                    review.setRevDate(rs.getString(DATE));
                    review.setRevIP(rs.getString(IP));
                    review.setRevStatus(rs.getInt(STATUS));
                    reviewList.add(review);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return reviewList;
    }

    public Vector<Review> findByColumn(String column, int term) {
        Vector<Review> reviewList = new Vector<Review>();
        Review review = null;
        Customer cus = null;
        OrderLine ord = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append("?");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, term);

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    review = new Review();
                    review.setRevId(rs.getInt(ID));
                    cus = new Customer();
                    cus.setCusID(rs.getInt(CUSTOMER_ID));
                    review.setCusId(cus);
                    review.setBooIsbn13(rs.getString(BOOK_ISBN_13));
                    ord = new OrderLine();
                    ord.setOrdLineId(rs.getInt(ORDERLINE_ID));
                    review.setOrdLineId(ord);
                    review.setRevNote(rs.getFloat(NOTE));
                    review.setRevComment(rs.getString(COMMENT));
                    review.setRevDate(rs.getString(DATE));
                    review.setRevIP(rs.getString(IP));
                    review.setRevStatus(rs.getInt(STATUS));
                    reviewList.add(review);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return reviewList;
    }

    public Vector<Review> findByCriteria(String column, int term, String criteria) {
        Vector<Review> reviewList = new Vector<Review>();
        Review review = null;
        Customer cus = null;
        OrderLine ord = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT " + criteria + " FROM " + TABLE + " WHERE ")
                .append(column)
                .append(" = ")
                .append(term);

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    review = new Review();
                    review.setRevId(rs.getInt(ID));
                    cus = new Customer();
                    cus.setCusID(rs.getInt(CUSTOMER_ID));
                    review.setCusId(cus);
                    review.setBooIsbn13(rs.getString(BOOK_ISBN_13));
                    ord = new OrderLine();
                    ord.setOrdLineId(rs.getInt(ORDERLINE_ID));
                    review.setOrdLineId(ord);
                    review.setRevNote(rs.getFloat(NOTE));
                    review.setRevComment(rs.getString(COMMENT));
                    review.setRevDate(rs.getString(DATE));
                    review.setRevIP(rs.getString(IP));
                    review.setRevStatus(rs.getInt(STATUS));
                    reviewList.add(review);
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return reviewList;
    }

    public Review findReview(int customerId, String isbn) {
        Review review = null;
        Customer cus = null;
        OrderLine ord = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE cusId = ? AND booIsbn13 = ? AND rev.revStatus = " + REVIEW_STATUS_PUBLISHED);

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            pstmt.setInt(1, customerId);
            pstmt.setString(2, isbn);
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    review = new Review();
                    review.setRevId(rs.getInt(ID));
                    cus = new Customer();
                    cus.setCusID(rs.getInt(CUSTOMER_ID));
                    review.setCusId(cus);
                    review.setBooIsbn13(rs.getString(BOOK_ISBN_13));
                    ord = new OrderLine();
                    ord.setOrdLineId(rs.getInt(ORDERLINE_ID));
                    review.setOrdLineId(ord);
                    review.setRevNote(rs.getFloat(NOTE));
                    review.setRevComment(rs.getString(COMMENT));
                    review.setRevDate(rs.getString(DATE));
                    review.setRevIP(rs.getString(IP));
                    review.setRevStatus(rs.getInt(STATUS));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return review;
    }

}
