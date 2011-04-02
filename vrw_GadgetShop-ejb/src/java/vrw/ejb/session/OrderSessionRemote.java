/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import vrw.ejb.entity.Order;

/**
 *
 * @author Robbie
 */
public interface OrderSessionRemote {

    public Order checkoutBasket(ShoppingBasketSessionRemote shoppingBasket);

    public void add(Order o);
    
}
