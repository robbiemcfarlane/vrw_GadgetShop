/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import java.util.Map;
import vrw.ejb.entity.Customer;


/**
 *
 * @author viktor
 */
public class AccountForm extends Form
{

    // Fields on the account registration page
    private static final String FIELD_NICKNAME = "nickname";
    private static final String FIELD_FIRST_NAME = "first-name";
    private static final String FIELD_LAST_NAME = "last-name";
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

    public AccountForm()
    {
        super();
    }
    
    /**
     * Register a customer
     * @param customer to registerCustomer
     */
    public Customer registerCustomer(Map<String, String[]> fields) throws GadgetShopValidationException
    {
        // If any parameters are null, user has edited form, so a NullPointerException
        // will be generated and caught by the application error page
        String nickname = fields.get(FIELD_NICKNAME)[0].trim();
        validateNickname(nickname);
        
        String email = fields.get(FIELD_EMAIL)[0].trim();
        String emailConfirmation = fields.get(FIELD_EMAIL_CONFIRMATION)[0].trim();
        validateEmail(email, emailConfirmation);

        String password = fields.get(FIELD_PASSWORD)[0].trim();
        String passwordConfirmation = fields.get(FIELD_PASSWORD_CONFIRMATION)[0].trim();
        // TODO: Validate password

        String firstName = fields.get(FIELD_FIRST_NAME)[0].trim();
        // TODO: Validate firstName

        String lastName = fields.get(FIELD_LAST_NAME)[0].trim();
        // TODO: Validate lastName

        String address1 = fields.get(FIELD_ADDRESS1)[0].trim();
        String address2 = fields.get(FIELD_ADDRESS2)[0].trim();
        String city = fields.get(FIELD_CITY)[0].trim();
        String county = fields.get(FIELD_COUNTY)[0].trim();
        String postcode = fields.get(FIELD_POSTCODE)[0].trim();
        String country = fields.get(FIELD_COUNTRY)[0].trim();
        //this.validateAddress(address1, address2, city, county, postcode, country);
       
        // Failed validation
        if (!isSuccess())
        {
            throw new GadgetShopValidationException("Account fields invalid");
        }
        // Successful validation
        else
        {
            return new Customer(nickname, firstName, lastName, password, address1, address2, city, county, postcode, country, email);
        }
    }

    /**
     * Validates user name
     * @param username to validate
     */
    public void validateNickname(String nickname)
    {
        if (nickname.isEmpty())
        {
            setMessage(FIELD_NICKNAME, "Please enter a nickname");
        }
        else if (nickname.length() < 5 || nickname.length() > 16)
        {
            setMessage(FIELD_NICKNAME, "Nickname must be between 5 and 16 characters long");
        }
        // TODO: Check that contains() works in this case
        else if (nickname.contains(" "))
        {
            setMessage(FIELD_NICKNAME, "Nickname must not contain any spaces");
        }

        // TODO: In full release, add further validation for non-allowed characters,
        // e.g. anything other than letters, numbers, and underscore
    }

    /**
     * Validates email address
     * @param email email address
     * @param emailConfirmation email address confirmation
     */
    public void validateEmail(String email, String emailConfirmation)
    {
        if (!FormUtil.isEmail(email))
        {
            setMessage(FIELD_EMAIL, "Please enter a valid email address");
        }

        if (!FormUtil.isEmail(emailConfirmation))
        {
            setMessage(FIELD_EMAIL_CONFIRMATION, "Please enter a valid email address confirmation");
        }
        else if (!email.equals(emailConfirmation))
        {
            setMessage(FIELD_EMAIL_CONFIRMATION, "Emails must match");
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
