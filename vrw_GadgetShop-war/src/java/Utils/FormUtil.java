/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author viktor
 */
public final class FormUtil {

    private FormUtil(){}

    /**
     * Returns field value from a request.
     * @param request http request
     * @param fieldName fieldName to return
     * @return
     */
    public static String getFieldValue(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName).trim();
        return isEmpty(value) ? null : value;
    }

    /**
     * Returns true if the given value is null or is empty.
     * @param value The value to be checked on emptiness.
     * @return True if the given value is null or is empty.
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return ((String) value).trim().length() == 0;
        } else if (value instanceof Object[]) {
            return ((Object[]) value).length == 0;
        } else if (value instanceof Collection<?>) {
            return ((Collection<?>) value).isEmpty();
        } else if (value instanceof Map<?, ?>) {
            return ((Map<?, ?>) value).isEmpty();
        } else {
            return value.toString() == null || value.toString().trim().length() == 0;
        }
    }

    /**
     * Checks whether a given string is an email
     * @param email email address to check
     * @return returns true if the email string matches a format specified by a regular expression
     */
    public static boolean isEmail(String email)
    {
        return email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)");
    }

    /**
     * Checks whether a given string is a number
     * @param string to check
     * @return returns true if the given string is a number, false otherwise
     */
    public static boolean isNumber(String string) {
        return string.matches("^\\d+$");
    }


}
