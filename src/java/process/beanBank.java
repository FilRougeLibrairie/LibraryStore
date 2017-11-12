package process;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import utils.InputsControls;

/**
 *
 * @author PC
 */
public class beanBank implements Serializable {

    private String numeroCB;
    private String cvv;
    private Date expiration;
    private String month;
    private String year;

    public String getNumeroCB() {
        return numeroCB;
    }

    public void setNumeroCB(String numeroCB) throws UserInputException {
        if (InputsControls.isCreditCardOk(numeroCB)) {
            this.numeroCB = numeroCB;
        } else {
            throw new UserInputException(numeroCB);
        }
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) throws UserInputException {
        if (InputsControls.isCreditCardSecurityNumberOk(cvv)) {
            this.cvv = cvv;
        } else {
            throw new UserInputException(cvv);
        }
    }

    public Date getExpirationDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
        String strDate = this.year + "-" + this.month;
        Date date = sdf.parse(strDate);
                
        return date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) throws UserInputException {
        if (InputsControls.isMonthOk(month)) {
            this.month = month;
        } else {
            throw new UserInputException(month);
        }
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) throws UserInputException {
       if (InputsControls.isYearOk(year)) {
            this.year = year;
        } else {
            throw new UserInputException(year);
        }
    }

}
