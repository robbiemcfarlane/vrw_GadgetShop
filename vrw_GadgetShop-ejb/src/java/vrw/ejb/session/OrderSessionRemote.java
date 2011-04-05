/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import javax.naming.NamingException;
import vrw.ejb.entity.Order;

/**
 *
 * @author Robbie
 */
public interface OrderSessionRemote {

    public Order checkout(ShoppingBasketSessionRemote shoppingBasket) throws NamingException;

    public Order add(Order o);
    
}
