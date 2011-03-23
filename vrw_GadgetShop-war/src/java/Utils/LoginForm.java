package Utils;

import java.util.Map;



/**
 *
 * @author
 */
public class LoginForm extends Form {

    // Fields on the account registration page
    private static final String FIELD_NICKNAME = "nickname";
    private static final String FIELD_PASSWORD = "password";

    public LoginForm()
    {
        super();
    }

    public void loginCustomer(Map<String, String[]> fields) throws GadgetShopValidationException
    {

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

}
