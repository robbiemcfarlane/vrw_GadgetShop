package Utils;

import java.util.Map;
import vrw.ejb.entity.Customer;

/**
 *
 * @author
 */
public class AccountLoginForm extends AccountForm
{

    public AccountLoginForm()
    {
        super();
    }

    public Customer loginCustomer(Map<String, String[]> fields) throws ValidationException
    {
        String nickname = fields.get(FIELD_NICKNAME)[0].trim();
        validateNickname(nickname);

        String password = fields.get(FIELD_PASSWORD)[0].trim();
        validatePassword(password);

        // Failed validation
        if (!isSuccess())
        {
            throw new ValidationException("Login fields invalid");
        }
        else
        {
            return new Customer(nickname, password);
        }
    }
}
