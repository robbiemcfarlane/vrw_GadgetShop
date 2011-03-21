/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import vrw.ejb.entity.Customer;


import vrw.ejb.entity.Customer;
import vrw.ejb.session.CustomerSessionRemote;


/**
 *
 * @author viktor
 */
public class AccountForm extends Form {

    private InitialContext context = null;
    private CustomerSessionRemote customerSessionRemote = null;
    private Customer customer = null;

    // Fields on the account registration page
    private static final String FIELD_NICKNAME="nickname";
    private static final String FIELD_FIRSTNAME = "first-name";
    private static final String FIELD_LASTNAME = "last-name";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_EMAIL_CONFIRMATION = "email-confirmation";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_PASSWORD_CONFIRMATION = "password-confirmation";
    private static final String FIELD_ADDRESS1 = "address-1";
    private static final String FIELD_ADDRESS2 = "address-2";
    private static final String FIELD_CITY = "city";
    private static final String FIELD_COUNTY = "county";
    private static final String FIELD_POSTCODE = "postcode";
    private static final String FIELD_COUNTRY = "country";

    /**
     * Register a customer
     * @param customer to registerCustomer
     */
    public Customer registerCustomer(HttpServletRequest request) throws ServletException
    {
        // Account specific
        String nickname = null;
        String email = null;
        String emailConfirmation = null;
        String password = null;
        String passwordConfirmation = null;

        // Personal details, address
        String firstname = null;
        String lastname = null;
        String address1 = null;
        String address2 = null;
        String city = null;
        String county = null;
        String postcode = null;
        String country = null;

        try
        {
            // Read account specific parameters from request
            nickname =  FormUtil.getFieldValue(request, "nickname");
            email = FormUtil.getFieldValue(request,"email");
            emailConfirmation = FormUtil.getFieldValue(request,"email-confirmation");
            password = FormUtil.getFieldValue(request, "password");
            passwordConfirmation = FormUtil.getFieldValue(request,"password-confirmation");

            // Read personal details and address from request
            firstname = FormUtil.getFieldValue(request, "first-name");
            lastname = FormUtil.getFieldValue(request, "last-name");
            address1 = FormUtil.getFieldValue(request, "address1");
            address2 = FormUtil.getFieldValue(request, "address2");
            city = FormUtil.getFieldValue(request, "city");
            county = FormUtil.getFieldValue(request, "county");
            postcode = FormUtil.getFieldValue(request, "postcode");
            country = FormUtil.getFieldValue(request, "country");

            // Create a new customer
            customer = new Customer(nickname, firstname, lastname, password,
                    address1, address2, city, county, postcode, country, email);

            // Validate customer
            validateUsername(nickname);
            validateEmail(email, emailConfirmation);

            // Register customer if there are no validation errors
            if(isSuccess())
            {
                context = new InitialContext();
                customerSessionRemote = (CustomerSessionRemote) context.lookup(
                        "vrw_GadgetShop/CustomerSession/remote");

                customerSessionRemote.register(customer);
            }

            return customer;

        }
        catch(Exception e)
        {
            throw new ServletException("Can't register customer");
        }

    }


    /**
     * Validates user name
     * @param username to validate
     * @throws GadgetShopValidationException if the username does not meat validation criteria
     */
    public void validateUsername(String username) throws GadgetShopValidationException
    {
        if(username == null)
        {
            setError(FIELD_NICKNAME, "Please enter a username");
        }
        else if(username.length() < 5)
        {
            setError(FIELD_NICKNAME, "Username must be between 5 and 16 characters long");
        }
    }

    /**
     * Validates email address
     * @param email email address
     * @param emailConfirmation email address confirmation
     * @throws GadgetShopValidationException validation exception is validation criteria has not been met
     */
    public void validateEmail(String email, String emailConfirmation) throws GadgetShopValidationException
    {
        if (!FormUtil.isEmail(email))
        {
            throw new GadgetShopValidationException("Please enter a valid email address");
        }

        if(!FormUtil.isEmail(emailConfirmation))
        {
            throw new GadgetShopValidationException("Please enter a valid email confirmation");
        }

        if(!email.equals(emailConfirmation))
        {
            throw new GadgetShopValidationException("Emails must match");
        }
    }

    /**
     *
     * @param address1
     * @param address2
     * @param city
     * @param county
     * @param postcode
     * @param country
     */
    public void validateAddress(String address1, String address2, String city,
                                String county, String postcode, String country)
    {
        
    }
}
