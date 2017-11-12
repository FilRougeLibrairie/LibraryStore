package accessBD;

//import names.SQLNames.OfferNames;
import entity.Offer;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;
import javax.naming.NamingException;

public class OfferDAO implements Serializable {

    private MyConnexion mc;

    private final String TABLE = "Offer";

    private final String ID = "offId";
    private final String NAME = "offName";
    private final String TEXT = "offText";
    private final String START = "offDateStart";
    private final String END = "offDateEnd";
    private final String DISCOUNT = "offDiscount";
    private final String PICTURE = "offPicture";
    private final String STATUS = "offStatus";
    

    private String COLUMNS_CREATE = NAME + ", " + TEXT
            + ", " + START + ", " + END + ", " + DISCOUNT + ", " + PICTURE + " , " + STATUS;

    public OfferDAO() throws NamingException {
        mc = new MyConnexion();
    }

    public Vector findAll() {
        Vector<Offer> offerList = new Vector<Offer>();
        Offer offer = null;

        String query = "SELECT * FROM " + TABLE;
        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    offer = new Offer();
                    offer.setOffId(rs.getInt(ID));
                    offer.setOffName(rs.getString(NAME));
                    offer.setOffText(rs.getString(TEXT));
                    offer.setOffDateStart(rs.getString(START));
                    offer.setOffDateEnd(rs.getString(END));
                    offer.setOffDiscount(rs.getFloat(DISCOUNT));
                    offer.setOffPicture(rs.getString(PICTURE));
                    offer.setOffStatus(rs.getInt(STATUS));
                    offerList.add(offer);
                }
            } else {
                throw new SQLException("ResultSet was emplty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return offerList;
    }

    public Object find(int id) {
        Offer offer = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + " WHERE ")
                .append(ID)
                .append(" = ")
                .append("'" + id + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    offer.setOffId(rs.getInt(ID));
                    offer.setOffName(rs.getString(NAME));
                    offer.setOffText(rs.getString(TEXT));
                    offer.setOffDateStart(rs.getString(START));
                    offer.setOffDateEnd(rs.getString(END));
                    offer.setOffDiscount(rs.getFloat(DISCOUNT));
                    offer.setOffPicture(rs.getString(PICTURE));

                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return offer;
    }

    public Vector<Offer> findByColumn(String column, String term) {

        Vector<Offer> offerList = new Vector<Offer>();
        Offer offer = null;

        StringBuffer query = new StringBuffer();
        query.append("SELECT * FROM " + TABLE + "WHERE ")
                .append(column)
                .append(" = ")
                .append("'" + term + "'");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    offer.setOffId(rs.getInt(ID));
                    offer.setOffName(rs.getString(NAME));
                    offer.setOffText(rs.getString(TEXT));
                    offer.setOffDateStart(rs.getString(START));
                    offer.setOffDateEnd(rs.getString(END));
                    offer.setOffDiscount(rs.getFloat(DISCOUNT));
                    offer.setOffPicture(rs.getString(PICTURE));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return offerList;
    }

    public Offer findCurrentOfferByBook(String isbn13) {
        Offer currentOffer = null;
        StringBuilder query = new StringBuilder();
        query.append("DECLARE ")
                .append("@currentTime DATETIME = GETDATE(), ")
                .append("@book_Isbn varchar(13) = '" + isbn13 + "' ")
                .append("SELECT * ")
                .append("FROM Offer offe ")
                .append("JOIN Have hav ")
                .append("ON offe.offId = Hav.offId ")
                .append("JOIN Book book ")
                .append("ON hav.booIsbn13 = book.booIsbn13 ")
                .append("WHERE book.booIsbn13 = @book_Isbn ")
                .append("AND offe.offDateStart < @currentTime ")
                .append("AND offe.offDateEnd > @currentTime ")
                .append("ORDER BY offDateEnd DESC ");

        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query.toString())) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    currentOffer = new Offer();
                    currentOffer.setOffId(rs.getInt(ID));
                    currentOffer.setOffName(rs.getString(NAME));
                    currentOffer.setOffText(rs.getString(TEXT));
                    currentOffer.setOffDateStart(rs.getString(START));
                    currentOffer.setOffDateEnd(rs.getString(END));
                    currentOffer.setOffDiscount(rs.getFloat(DISCOUNT));
                    currentOffer.setOffPicture(rs.getString(PICTURE));
                }
            } else {
                throw new SQLException("ResultSet was empty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());
        }
        return currentOffer;
    }

   ////////////////////////////////////CHRYS/////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////
    //offre en cours
    public Collection findAllEventStatus1() {
        Vector<Offer> offerList = new Vector<Offer>();
        Offer offer = null;

        String query = "SELECT * FROM " + TABLE + " WHERE " + STATUS + "=0 and offDateStart<GETDATE() AND offDateEnd>GETDATE()";
        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    offer = new Offer();
                    offer.setOffId(rs.getInt(ID));
                    offer.setOffName(rs.getString(NAME));
                    offer.setOffText(rs.getString(TEXT));
                    offer.setOffDateStart(rs.getString(START));
                    offer.setOffDateEnd(rs.getString(END));
                    offer.setOffDiscount(rs.getFloat(DISCOUNT));
                    offer.setOffPicture(rs.getString(PICTURE));
                    offer.setOffStatus(rs.getInt(STATUS));
                    offerList.add(offer);
                }
            } else {
                throw new SQLException("ResultSet was emplty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return offerList;
    }

    // Offre en cours
    public Offer findAllEventByBook(String id) {
        Offer offerdiscount =new Offer();
        Offer offer = null;

        String query = "SELECT boo.booIsbn13, vat.vatRate,boo.booPriceHT, ofe.offName, autLastName , autFirstName, booTitle, booSubtitle, offDateStart, offDateEnd, offDiscount "
                + "FROM Offer ofe "
                + "JOIN Have hav "
                + "ON ofe.offId = hav.offId "
                + "JOIN Book boo "
                + "ON boo.booIsbn13 = hav.booIsbn13 "
                + "JOIN Writing wri "
                + "ON boo.booIsbn13 = wri.booIsbn13 "
                + "JOIN Author aut "
                + "ON wri.autId = aut.autId "
                + "JOIN vat vat "
                + "ON vat.vatCode=boo.vatCode "
                + "WHERE offDateStart<GETDATE() AND offDateEnd>GETDATE() AND boo.booIsbn13=" +id;
        
        try (Connection cnt = mc.getConnection(); PreparedStatement pstmt = cnt.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    offer = new Offer();
                    
                    offer.setOffDiscount(rs.getFloat(DISCOUNT));
                    offerdiscount.setOffDiscount(offer.getOffDiscount());
                    System.out.println(offerdiscount);
                }
            } else {
                throw new SQLException("ResultSet was emplty");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR Retrieving Object : " + ex.getMessage());

        }
        return offerdiscount;

    }
}
