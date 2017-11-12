package process;

import accessBD.AddressDAO;
import accessBD.CustomerDAO;
import entity.Address;
import entity.Customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import utils.Crypto;

/**
 *
 * @author CDI305
 */
public class BeanConnexionClient {

    CustomerDAO cusDAO;
    AddressDAO addDAO;
    Customer myCus;
    Address myCusAddress;
    Customer createCus;

    public BeanConnexionClient() {
        try {
            cusDAO = new CustomerDAO();
            addDAO = new AddressDAO();
        } catch (NamingException ex) {
            Logger.getLogger(BeanConnexionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Customer getMyCus() {
        return myCus;
    }

    public Customer getCreateCus() {
        return createCus;
    }

    public void setCreateCus(Customer createCus) {
        this.createCus = createCus;
    }

    public void setMyCus(Customer myCus) {
        this.myCus = myCus;
    }

    public Address getMyCusAddress() {
        return myCusAddress;
    }

    public void setMyCusAddress(Address myCusAddress) {
        this.myCusAddress = myCusAddress;
    }

    public boolean checkLogin(String adresseMail, String motDePasse) {
        if (adresseMail == null) {
            return false;
        }
        if (motDePasse == null) {
            return false;
        }
        if (adresseMail.trim().isEmpty()) {
            return false;
        }
        if (motDePasse.trim().isEmpty()) {
            return false;
        }
//
//        Customer myCus = cusDAO.find(adresseMail);
//        System.out.println("BEAN MAIL SAISI EST : " + adresseMail);
//        System.out.println("BEAN PASSWORD SAISI EST : " + motDePasse);
//        System.out.println("BEAN AFFICHAGE CLEARPASSWORD : " + cusDAO.find(adresseMail).getCusClearPassword());
//        System.out.println("BEAN AFFICHAGE CusID : " + cusDAO.find(adresseMail).getCusID());//ok
//        System.out.println("BEAN AFFICHAGE D'UNE ADRESSE : " + addDAO.findByCustomerId(cusDAO.find(adresseMail).getCusID()));//ok
        if (adresseMail.equals(cusDAO.find(adresseMail).getCusEmail())) {
            if (motDePasse.equals(cusDAO.find(adresseMail).getCusClearPassword())) {
                return true;
            }
        }
        return false;
    }

    public Object findCustomer(String adresseMail, String motDePasse) {
        if (adresseMail.equals(cusDAO.find(adresseMail).getCusEmail())) {
            if (motDePasse.equals(cusDAO.find(adresseMail).getCusClearPassword())) {
                myCus = cusDAO.find(adresseMail);
            }
        }
        return myCus;
    }

    public Customer createCustomer(String Civilité, String lastName,
            String firstName, String organisationName, String adresseMail,
            String phoneNumber, String dateN, String password, String confirmPassword, String ip) throws Exception {
        if (lastName.trim().isEmpty() || firstName.trim().isEmpty() || adresseMail.trim().isEmpty() || password.trim().isEmpty()) {
            throw new Exception("Des champs obligatoire ne sont pas remplis");
        } else {
            if (password.trim().equals(confirmPassword.trim())) {

                Customer cus = new Customer();
                cus.setCusGender(null);
                cus.setCusLastName(lastName.trim());
                cus.setCusFirstName(firstName.trim());
                cus.setCusOrganisationName(organisationName.trim());
                cus.setCusEmail(adresseMail.trim());
                cus.setCusPhoneNumber(phoneNumber.trim());
                cus.setCusDateOfBirth(dateN);
                String[] str = Crypto.createPassword(password);
                cus.setCusSalt(str[1]);
                cus.setCusIP(ip);
                cus.setCusPassword(str[0]);
                cus.setCusStatus(1);
                cus.setCusComment(null);
                cus.setCusClearPassword(confirmPassword.trim());
                cusDAO.create(cus);

                return cus;
            }

        }
        return null;
    }

    public Address createAddress(Customer currentCus, String addLabel, String addLastName, String addFirstName, String addCompany,
            String addNumber, String addStreetType, String addStreetName, String addComplement, String addZipCode,
            String addCity, String addSecurityCode, String addPhone) {

        System.out.println("BEAN ADDLABEL :"+addLabel);
        Address addCus = new Address();
        addCus.setCusChargeId(currentCus);
        addCus.setCusResidId(currentCus);
        addCus.setAddLabel(addLabel.trim());
        addCus.setAddLastName(addLastName.trim());
        addCus.setAddFirstName(addFirstName.trim());
        addCus.setAddCompany(addCompany.trim());
        addCus.setAddNumber(addNumber.trim());
        addCus.setAddStreetType(addStreetType.trim());
        addCus.setAddStreetName(addStreetName.trim());
        addCus.setAddComplement(addComplement.trim());
        addCus.setAddZipCode(addZipCode.trim());
        addCus.setAddCity(addCity.trim());
        addCus.setAddSecurityCode(addSecurityCode.trim());
        addCus.setAddPhone(addPhone.trim());
        addDAO.create(addCus);
        System.out.println("BEAN CREATE ADDRESS ADDCUS :"+addCus);
        return addCus;
    }

    public Customer updateCustomer(Customer currentCus,
            String Civilité, String lastName,
            String firstName, String organisationName, String adresseMail,
            String phoneNumber, String dateN, String password, String ip) throws Exception {
        Customer cus = new Customer();
        cus.setCusID(currentCus.getCusID());
        cus.setCusGender(Civilité);
        cus.setCusLastName(lastName.trim());
        cus.setCusFirstName(firstName.trim());
        cus.setCusOrganisationName(organisationName.trim());
        cus.setCusEmail(adresseMail.trim());
        cus.setCusPhoneNumber(phoneNumber.trim());
        cus.setCusDateOfBirth(dateN);
        cus.setCusComment(currentCus.getCusComment());
        cus.setCusIP(currentCus.getCusIP());
        cus.setCusStatus(currentCus.getCusStatus());
        if (password.isEmpty()) {
            cus.setCusPassword(currentCus.getCusPassword());
            cus.setCusSalt(currentCus.getCusSalt());
        } else {
            String[] str = Crypto.createPassword(password);
            cus.setCusPassword(str[0]);
            cus.setCusSalt(str[1]);
        }
        cusDAO.update(cus);
        return cus;
    }

    public Collection<Address> findAdressCustomer(int cusId) {
        Collection myCusAddress = new ArrayList();
        Collection<Address> adress = addDAO.findByCustomerId(cusId);
//        System.out.println("dans la collection"+ adress);
        for (Address addr : adress) {
            myCusAddress.add(addr);
//             System.out.println("!!!!!BEAN!!!! " +addr);
        }

        return myCusAddress;
    }

    //// DEBUT GUILLAUME
    public boolean checkCryptLogin(String adresseMail, String motDePasse) throws Exception {
        if (adresseMail == null) {
            return false;
        } else if (motDePasse == null) {
            return false;
        } else if (adresseMail.trim().isEmpty()) {
            return false;
        } else if (motDePasse.trim().isEmpty()) {
            return false;
        } else {
            Customer cus = cusDAO.find(adresseMail.trim());
            if (cus != null) {
                String hashedInput = Crypto.hashPassword(cus.getCusSalt(), motDePasse);
                this.myCus = cus;
                return hashedInput.equalsIgnoreCase(cus.getCusPassword());
            }
        }
        return false;
    }
    //// FIN GUILLAUME

}
