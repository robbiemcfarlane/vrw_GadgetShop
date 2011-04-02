/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Robbie
 */
public class Basket implements java.io.Serializable
{

    private HashMap<Integer, BasketItem> items;

    private static final long serialVersionUID = 1;

    public Basket()
    {
        items = new HashMap<Integer, BasketItem>();
    }

    /**
     * @return the items
     */
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
}
