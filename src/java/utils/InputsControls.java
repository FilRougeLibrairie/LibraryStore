package utils;

/**
 * The {@code InputsControls} class is used to check user inputs using
 * regex.<br>
 * Phone and zipCode regex are formatted to check French formats.<br>
 * Static methods return a boolean value.
 *
 * @author ggarvanese
 */

public class InputsControls {

    private static final String ZIPCODE_MASK = "^[0-9]{5}$";
    private static final String PHONE_MASK = "^(0033|\\+33|0)[1-9]([- \\.\\/]{0,1}[0-9]{2}){4}$";
    private static final String MOBILE_PHONE_MASK = "^0[67][.\\- *]?[0-9]{2}[.\\- *]?[0-9]{2}[.\\- *]?[0-9]{2}[.\\- *]?[0-9]{2}$";
    private static final String MAIL_MASK = "^[a-zA-Z0-9][-_\\.a-zA-Z0-9]*@[a-z0-9][-\\.a-zA-z0-9]*\\.[a-zA-Z]{2,6}$";
    private static final String BIRTHDAY_MASK = "^[0-3][0-9]\\/[0-1][0-9]\\/[1-2][0-9]{3}$";
    private static final String CB_VISA_MASTERCARD_MASK = "^[0-9]{15,19}$";
    private static final String CREDIT_CARD_SECURITY_NUMBER = "^[0-9]{3}$";

    public static boolean isZipCodeOk(String zipCode) {
        if (!zipCode.isEmpty() && !(zipCode.matches(ZIPCODE_MASK))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPhoneOk(String phone) {
        if (!(phone.matches(PHONE_MASK))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isMobilePhoneOk(String mobilePhone) {
        if (!(mobilePhone.matches(MOBILE_PHONE_MASK))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isMailOk(String mail) {
        if (!mail.isEmpty() && mail != null && !(mail.matches(MAIL_MASK))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isBirthdayOk(String birthday) {
        if (!(birthday.matches(BIRTHDAY_MASK))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isCreditCardOk(String creditCardNumber) {
        if (!(creditCardNumber.matches(CB_VISA_MASTERCARD_MASK))) {
            return false;
        } else {
            return true;
        }
    }
  
    public static boolean isCreditCardSecurityNumberOk(String creditCardSecurityNumber) {
        if (!(creditCardSecurityNumber.matches(CREDIT_CARD_SECURITY_NUMBER))) {
            return false;
        } else {
            return true;
        }
    }

    

}
