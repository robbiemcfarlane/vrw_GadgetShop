package Utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Stores all error messages of a form
 *
 * @author viktor
 */
public class Form {

    private Map<String, String> messages = new LinkedHashMap<String, String>();
    private boolean hasError;

    /**
     * Returns all messages for the form.
     * @return all messages for the form.
     */
    public Map<String, String> getMessages()
    {
        return messages;
    }

    /**
     * Returns true if there are no error messages, false otherwise.
     * @return true if there are no error messages, false otherwise.
     */
    public boolean isSuccess()
    {
        return !hasError;
    }

    /**
     * Sets message for a specified field.
     * @param key field to set.
     * @param value value to set.
     */
    public void setMessage(String key, String value)
    {
        messages.put(key, value);
    }

    /**
     * Sets error for a specified field.
     * @param key field to set.
     * @param value value to set.
     */
    public void setError(String key, String value)
    {
        hasError = true;
        setMessage(key, value);
    }

}
