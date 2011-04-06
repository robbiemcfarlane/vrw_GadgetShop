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
public class OfferItem implements java.io.Serializable {

    private static long serialVersionUID = 1;

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Item item;
    @ManyToOne
    private Offer offer;
    private int quantity;


    public OfferItem()
    {

    }

    public OfferItem(Item item, int quantity)
    {
        this.item = item;
        this.quantity = quantity;
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
     * @return the offer
     */
    public Offer getOffer()
    {
        return offer;
    }

    /**
     * @param offer the offer to set
     */
    public void setOffer(Offer offer)
    {
        this.offer = offer;
    }

    /**
     * Gets the price of the item in this OfferItem, without any discount.
     *
     * @return  the base price of the item
     */
    public BigDecimal getPrice()
    {
        return item.getPrice().multiply(new BigDecimal(quantity));
    }

}
