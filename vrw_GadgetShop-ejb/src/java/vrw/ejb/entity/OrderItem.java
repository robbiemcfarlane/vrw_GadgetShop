/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.entity;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Robbie
 */
@Entity
public class OrderItem implements java.io.Serializable
{
    private static final long serialVersionUID = 1;
    
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Item item;
    private int quantity;
    private BigDecimal price;
    @ManyToOne
    private Order order;

    public OrderItem()
    {
    }

    public OrderItem(BasketItem basketItem)
    {
        item = basketItem.getItem();
        quantity = basketItem.getQuantity();
        price = basketItem.getPrice();
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

    /**
     * @return the price
     */
    public BigDecimal getPrice()
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the order
     */
    public Order getOrder()
    {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order)
    {
        this.order = order;
    }
}
