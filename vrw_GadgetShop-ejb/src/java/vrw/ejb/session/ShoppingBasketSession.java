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

    @PostConstruct
    public void initialize()
    {
        items = new HashMap<Integer, BasketItem>();
    }

    public HashMap<Integer, BasketItem> getItems()
    {
        return items;
    }

    public Collection<BasketItem> getItemsAsCollection()
    {
        return items.values();
    }

    public void addItem(Item item, int quantity)
            throws StockException
    {
        if (item.getStockLevel() < quantity)
        {
            throw new StockException("Item out of stock");
        }

        int itemId = item.getId();

        // If item is already in basket
        if (items.containsKey(itemId))
        {
            // Increment quantity
            items.get(itemId).incrementQuantity(quantity);
        }
        // If item is not in basket
        else
        {
            // Put item in basket
            items.put(itemId, new BasketItem(item, quantity));
        }
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

    public BigDecimal getTotal()
    {
        BigDecimal total = new BigDecimal(0);
        for (BasketItem basketItem : items.values())
        {
            total = total.add(basketItem.getPrice());
        }

        return total;
    }

    public int getNumItems()
    {
        int numItems = 0;
        for (BasketItem basketItem : items.values())
        {
            numItems += basketItem.getQuantity();
        }

        return numItems;
    }

    @Remove
    public void terminate()
    {
        // TODO: Do we need to do anything here?
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
