/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import java.math.BigDecimal;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import vrw.ejb.entity.Basket;
import vrw.ejb.entity.BasketItem;
import vrw.ejb.entity.Order;
import vrw.ejb.entity.OrderItem;

/**
 *
 * @author Robbie
 */
@Stateless
@Remote(OrderSessionRemote.class)
public class OrderSession implements OrderSessionRemote {
    
    @PersistenceContext
    private EntityManager em;

    public void add(Order o)
    {
        em.persist(o);
    }

    public Order checkoutBasket(ShoppingBasketSessionRemote shoppingBasket)
    {
        // Create a new order
        Order order = new Order();

        // Create an order item from each basket item and add to the order
        for(BasketItem basketItem : shoppingBasket.getItems().values())
        {
            OrderItem orderItem = new OrderItem(basketItem);

            order.addItem(orderItem);
        }

        // Save the order to the database
        em.persist(order);
        em.flush();

        // Update this object from the database to get the auto-generated id
        // (doesn't seem to be working)
        em.refresh(order);
        
        return order;
    }
}
