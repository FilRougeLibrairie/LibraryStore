package names;


public interface SQLNames {

    public static class AccessProfileNames {

        public static final String PROFILE_CODE = "accProfileCode";
        public static final String PROFILE_NAME = "accProfileName";
    }

    public static class AddressNames {

        public static final String ID = "addId";
        public static final String CUST_RESIDENCE_ID = "cusResidId";
        public static final String CUST_CHARGE_ID = "cusChargeId";
        public static final String LABEL = "addLabel";
        public static final String FIRST_NAME = "addFirstName";
        public static final String LAST_NAME = "addLastName";
        public static final String COMPANY = "addCompany";
        public static final String STREET_NUMBER = "addNumber";
        public static final String STREET_TYPE = "addStreetType";
        public static final String STREET_NAME = "addStreetName";
        public static final String COMPLEMENT = "addComplement";
        public static final String ZIP_CODE = "addZipCode";
        public static final String CITY = "addCity";
        public static final String SECURITY_CODE = "addSecurityCode";
        public static final String PHONE = "addPhone";

    }

    public static class AuthorNames {

        public static final String ID = "autId";
        public static final String LAST_NAME = "autLastName";
        public static final String FIRST_NAME = "autFirstName";
        public static final String BIOGRAPHY = "autBiography";
        public static final String STATUS_CODE = "autStatusCode";
    }

    public static class BookNames {

        public static final String ISBN_13 = "booIsbn13";
        public static final String VAT_CODE = "vatCode";
        public static final String EDITOR_ID = "ediId";
        public static final String TITLE = "booTitle";
        public static final String SUBTITLE = "booSubtitle";
        public static final String PUBLICATION_YEAR = "booPublishYear";
        public static final String PRICE_HT = "booPriceHT";
        public static final String RESUME = "booResume";
        public static final String QUANTITY = "booQuantity";
        public static final String STATUS = "booStatus";
        public static final String FRONT_COVER = "booFrontCover";
        public static final String PAGE_NUMBER = "booPageNumber";
        public static final String LANGUAGE_ID = "bookLangCode";
        public static final String FORMAT_ID = "forId";
    }

    public static class BookLanguageNames {

        public static final String CODE = "bookLangCode";
        public static final String NAME = "bookLangName";
        public static final String STATUS="bookLangStatus";
    }

    public static class CustomerNames {

        public static final String ID = "cusId";
        public static final String GENDER = "cusGender";
        public static final String FIRST_NAME = "cusFirstName";
        public static final String LAST_NAME = "cusLastName";
        public static final String COMPANY = "cusOrganisationName";
        public static final String EMAIL = "cusEmail";
        public static final String PHONE = "cusPhoneNumber";
        public static final String BIRTHDAY = "cusDateOfBirth";
        public static final String PASSWORD = "cusPassword";
        public static final String SALT = "cusSalt";
        public static final String IP = "cusIP";
        public static final String STATUS = "cusStatus";
        public static final String COMMENT = "cusComment";
    }

    public static class DeterminateNames {

        public static final String STATUS_CODE = "staCode";
        public static final String PURCHASE_ID = "purId";
        public static final String DATE_TIME = "detTime";
    }

    public static class EditorNames {

        public static final String ID = "ediId";
        public static final String NAME = "ediName";
        public static final String PRESENTATION = "ediPresentation";
        public static final String STATUS = "ediStatusCode";
    }

    public static class EmployeeNames {

        public static final String ID = "empId";
        public static final String FIRST_NAME = "empFirstName";
        public static final String LAST_NAME = "empLastName";
        public static final String LOGIN = "empLogin";
        public static final String PASSWORD = "empPassword";
        public static final String SALT = "empSalt";
        public static final String DATE_START = "empDateStart";
        public static final String DATE_END = "empDateEnd";
        public static final String ACCESS_PROFILE = "accProfileCode";
        public static final String STATUS = "empStatus";
        public static final String COMMENT = "empComment";
    }

    public static class FormatsNames {

        public static final String ID = "forId";
        public static final String NAME = "forName";
        public static final String STATUS = "forStatus";
    }

    public static class KeywordsNames {

        public static final String NAME = "keyName";
    }

    public static class MyLibraryNames {

        public static final String ID = "myLibId";
        public static final String NAME = "myLibName";
        public static final String LOGO = "myLibLogo";
        public static final String EMAIL = "myLibEmail";
        public static final String PHONE = "myLibPhone";
        public static final String SIRET = "myLibSiret";
        public static final String CGU = "myLibCGU";
        public static final String STREET_NUMBER = "myLibAddNumber";
        public static final String STREET_NAME = "myLibAddStreetName";
        public static final String COMPLEMENT = "myLibAddComplement";
        public static final String ZIP_CODE = "myLibAddZipCode";
        public static final String CITY = "myLibAddCity";
    }

    public static class OfferNames {

        public static final String ID = "offId";
        public static final String NAME = "offName";
        public static final String TEXT = "offText";
        public static final String START = "offDateStart";
        public static final String END = "offDateEnd";
        public static final String DISCOUNT = "offDiscount";
        public static final String PICTURE = "offPicture";
        public static final String STATUS = "offStatus";
    }

    public static class OrderLineNames {

        public static final String ID = "ordLineId";
        public static final String PURCHASE_ID = "purId";
        public static final String BOOK_ISBN_13 = "booIsbn13";
        public static final String QUANTITY = "ordLineQuantity";
        public static final String PRICE_HT = "ordBookPriceHT";
        public static final String VAT = "ordBookVAT";
    }

    public static class OrderStatusNames {

        public static final String CODE = "staCode";
        public static final String NAME = "staName";
    }

    public static class OrderStatusAttrNames {

        public static final String SUSPENDED = "Suspendu";
        public static final String ORDERED = "Payé";
        public static final String PAYED = "Expédié";
        public static final String SHIPPED = "Envoyé";
        public static final String RECEIVED = "Livré";
        public static final String RETURN = "Retour en cours";
        public static final String RETURN_ACCEPTED = "Retour Accepté";
        public static final String RETURN_REFUSED = "Retour refusé";
        public static final String REFUNDED = "Remboursé";
        public static final String NEW = "En cours";

    }

    public static class PaymentNames {

        public static final String ID = "payId";
        public static final String PURCHASE_ID = "purId";
        public static final String VALIDATION = "payValidate";
        public static final String CHOICE = "payChoice";
        public static final String DATE = "payDate";
        public static final String CARD_TYPE = "payCardType";
        public static final String OWNER_NAME = "payOwnerName";
    }

    public static class PurchaseNames {

        public static final String ID = "purId";
        public static final String CUSTOMER_ID = "cusId";
        public static final String SHIPPING_COST = "shippingCostId";
        public static final String ADDRESS_DELIVERY = "addDeliveryId";
        public static final String ADDRESS_INVOICE = "addInvoiceId";
        public static final String IP = "purIP";
        public static final String SHIPPING_DATE = "shippingDate";
        public static final String SHIPPING_NUMBER = "shippingNumber";
        public static final String INTERNAL_UUID = "purInternalId";
    }

    public static class ReviewNames {

        public static final String ID = "revId";
        public static final String CUSTOMER_ID = "cusId";
        public static final String BOOK_ISBN_13 = "booIsbn13";
        public static final String ORDERLINE_ID = "ordLineId";
        public static final String NOTE = "revNote";
        public static final String COMMENT = "revComment";
        public static final String DATE = "revDate";
        public static final String IP = "revIP";
        public static final String STATUS = "revStatus";
    }

    public static class ShippingCostNames {

        public static final String ID = "shipId";
        public static final String NAME = "shipName";
        public static final String COST = "shipCost";
    }

    public static class StatusDisplayNames {

        public static final String CODE = "staCode";
        public static final String NAME = "staName";
    }

    public static class SubThemeNames {

        public static final String ID = "subId";
        public static final String THEME_ID = "theId";
        public static final String NAME = "subName";
        public static final String DESCRIPTION = "subDescription";
        public static final String STATUS ="subStatus";
    }

    public static class ThemeNames {

        public static final String ID = "theId";
        public static final String NAME = "theName";
        public static final String DESCRIPTION = "theDescription";
        public static final String STATUS = "theStatus";
    }

    public static class VATNames {

        public static final String CODE = "vatCode";
        public static final String RATE = "vatRate";
        public static final String NAME = "vatName";
        public static final String STATUS = "vatStatus";
    }

}
