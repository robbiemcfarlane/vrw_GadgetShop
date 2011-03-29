/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Robbie
 */
public class BasketItem implements Serializable
{
    private Item item;
    private int quantity;

    public BasketItem()
    {

    }

    public BasketItem(Item item, int quantity)
    {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * @return the item
     */
    public Item getItem()
    {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item)
    {
        this.item = item;
    }

    /**
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void incrementQuantity(int increment)
    {
        quantity += increment;
    }
    
    public void decrementQuantity(int decrement)
    {
        quantity -= decrement;
    }

    /**
     * Convenience method to calculate the price of the basket item.
     *
     * @return  the basket item's price
     */
    public BigDecimal getPrice()
    {
        return item.getPrice().multiply(new BigDecimal(quantity));
    }
}
