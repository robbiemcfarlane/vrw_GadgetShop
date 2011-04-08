/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import java.util.Calendar;
import java.util.Collection;
import javax.naming.NamingException;
import vrw.ejb.entity.Customer;
import vrw.ejb.entity.Order;

/**
 *
 * @author Robbie
 */
public interface OrderSessionRemote {

    public Order checkout(ShoppingBasketSessionRemote shoppingBasket) throws NamingException;
    public Order add(Order o);

    
}
