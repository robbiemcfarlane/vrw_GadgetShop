/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.session;

import java.math.BigDecimal;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import vrw.ejb.entity.Basket;
import vrw.ejb.entity.BasketItem;
import vrw.ejb.entity.Item;
import vrw.ejb.entity.Order;
import vrw.ejb.entity.OrderItem;
import vrw.ejb.entity.StockException;

/**
 *
 * @author Robbie
 */
@Stateless
@Remote(OrderSessionRemote.class)
public class OrderSession implements OrderSessionRemote
{

    @PersistenceContext
    private EntityManager em;

    public void add(Order o)
    {
        em.persist(o);
    }

    public Order checkout(ShoppingBasketSessionRemote shoppingBasket)
            throws NamingException
    {
        // Create a new order
        Order order = new Order();

        InitialContext context = new InitialContext();
        ItemSessionRemote itemSession = (ItemSessionRemote) context.lookup("vrw_GadgetShop/ItemSession/remote");

        // Create an order item from each basket item and add to the order
        for (BasketItem basketItem : shoppingBasket.getItems().values())
        {
            OrderItem orderItem = new OrderItem(basketItem);
            order.addItem(orderItem);

            // "Refreshes" item from the database
            orderItem.setItem(itemSession.find(orderItem.getItem().getId()));
            Item item = orderItem.getItem();
            
            try
            {
                // Reduces the stock level of the item
                item.decrementStockLevel(orderItem.getQuantity());
                itemSession.update(item);
            }
            catch (StockException se)
            {
                // TODO: Add to a map to be shoved into a StockException which will
                // then be thrown out after this "for" loop?
            }
        }

        // Save the order to the database
        add(order);

        return order;
    }
}
