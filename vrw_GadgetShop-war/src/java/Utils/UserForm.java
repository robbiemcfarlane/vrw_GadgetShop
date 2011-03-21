/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import javax.servlet.http.HttpServletRequest;
import vrw.ejb.entity.Customer;

/**
 *
 * @author viktor
 */
public class UserForm extends Form {

    /**
     * Register customer
     * @param customer to register
     */
    public void registerCustomer(Customer customer)
    {
    
    }


    /**
     * Validates user name
     * @param username to validate
     * @throws GadgetShopValidationException if the username does not meat validation criteria
     */
    public void validateUserName(String username) throws GadgetShopValidationException
    {
        if(username == null)
        {
            throw new GadgetShopValidationException("Please enter a username");
        }
        else if(username.length() < 5)
        {
            throw new GadgetShopValidationException("Username must be between 5 and 16 characters long");
        }
    }
}
