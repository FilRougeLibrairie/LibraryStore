/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import accessBD.AddressDAO;
import entity.Address;
import entity.Customer;
import java.io.Serializable;
import javax.naming.NamingException;
import utils.InputsControls;

/**
 *
 * @author PC
 */
public class beanAddress implements Serializable {

    Address address;
    AddressDAO addressDAO;

    public beanAddress() throws NamingException {
        address = new Address();
        addressDAO = new AddressDAO();
    }

    public void createAddress(Customer customer, String label, String firstName,
            String lastName, String company, String streetNumber, String streetType,
            String streetName, String complement, String zipCode, String city, String securityCode, String phone) throws AddressCreationException {
        if (InputsControls.isZipCodeOk(zipCode.trim())) {
            address.setAddZipCode(zipCode.trim());
            address.setCusResidId(customer);
            address.setCusChargeId(customer);
            address.setAddLabel(label.trim());
            address.setAddFirstName(firstName.trim());
            address.setAddLastName(lastName.trim());
            address.setAddCompany(company.trim());
            address.setAddNumber(streetNumber.trim());
            address.setAddStreetType(streetType.trim());
            address.setAddStreetName(streetName.trim());
            address.setAddComplement(complement.trim());
            if (!phone.isEmpty() && InputsControls.isPhoneOk(phone.trim())) {
                address.setAddPhone(phone.trim());
            }
            addressDAO.create(address);
        } else {
            throw new AddressCreationException("L'adresse n'a pu être créée car le code postal est erroné");
        }
    }

}
