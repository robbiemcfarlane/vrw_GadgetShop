/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.session;

import javax.ejb.*;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import vrw.ejb.entity.*;
import javax.persistence.*;

/**
 *
 * @author Robbie
 */
@Stateful
@Remote(ShoppingBasketSessionRemote.class)
public class ShoppingBasketSession implements ShoppingBasketSessionRemote, java.io.Serializable
{

    private HashMap<Integer, BasketItem> items;
    private static final long serialVersionUID = 1;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void initialize()
    {
        items = new HashMap<Integer, BasketItem>();
    }
    
    @Remove
    public void terminate()
    {
        // TODO: Do we need to do anything here?
    }

    public HashMap<Integer, BasketItem> getItems()
    {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(HashMap<Integer, BasketItem> items)
    {
        this.items = items;
    }

    public BasketItem removeItem(int itemId)
    {
        BasketItem basketItem = items.get(itemId);

        // If there is only one of that item
        if (basketItem.getQuantity() == 1)
        {
            // Remove the whole item
            items.remove(itemId);
        }
        // If there is more than one of that item
        else
        {
            // Remove one of those items
            basketItem.decrementQuantity(1);
        }

        return basketItem;
    }

    public void addItem(Item item, int quantity)
            throws StockException
    {
        int itemId = item.getId();

        // If item is already in basket
        if (items.containsKey(itemId))
        {
            BasketItem basketItem = items.get(itemId);
            // If the quantity requested is greater than the quantity available (considers
            // item quantities already in this user's shopping basket)
            if (quantity > item.getStockLevel() - basketItem.getQuantity())
            {
                throw new StockException("Requested quantity unavailable");
            }
            // Increment quantity
            else
            {
                basketItem.incrementQuantity(quantity);
            }
        }
        // If item is not in basket
        else
        {
            // If the quantity requested is greater than the quantity available
            if (quantity > item.getStockLevel())
            {
                throw new StockException("Requested quantity unavailable");
            }
            // Put item in basket
            else
            {
                items.put(itemId, new BasketItem(item, quantity));
            }
        }
    }


    public Collection<BasketItem> getItemsAsCollection()
    {
        return items.values();
    }

    public BigDecimal getTotal()
    {
        BigDecimal total = new BigDecimal(0);
        for (BasketItem basketItem : getItemsAsCollection())
        {
            total = total.add(basketItem.getPrice());
        }

        return total;
    }

    public int getNumItems()
    {
        int numItems = 0;
        for (BasketItem basketItem : getItemsAsCollection())
        {
            numItems += basketItem.getQuantity();
        }

        return numItems;
    }

//    public static void main(String[] args)
//    {
//        ShoppingBasketSession s = new ShoppingBasketSession();
//        Item item1 = new Item("16GB USB Memory Stick", "A long description here...", "A short description here...", new BigDecimal(15.00), new BigDecimal(11.00), 99, true);
//        try
//        {
//            s.addItem(item1, 1);
//        }
//        catch (StockException e)
//        {
//            e.printStackTrace();
//        }
//
//        System.out.println(s.getTotal());
//    }
}
