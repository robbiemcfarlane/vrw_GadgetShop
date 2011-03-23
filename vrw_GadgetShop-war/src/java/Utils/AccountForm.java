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
public abstract class AccountForm extends Form
{

    // Fields on the account registration page
    protected static final String FIELD_NICKNAME = "nickname";
    protected static final String FIELD_PASSWORD = "password";

    public AccountForm()
    {
        super();
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

    public void validatePassword(String password)
    {
        // TODO: Implement validatePassword
    }

    

    
}
