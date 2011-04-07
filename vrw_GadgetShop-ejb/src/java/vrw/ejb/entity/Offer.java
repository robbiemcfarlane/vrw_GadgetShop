/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author viktor
 */

@Entity
public class Offer implements Serializable{

    @GeneratedValue
    @Id
    private int id;

    @Column(name="name",length=200, nullable=false)
    private String name;

    @Column(name="description", length=500, nullable=false)
    private String description;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @OneToMany(mappedBy="offer", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<OfferItem> items;

    @Version
    private java.sql.Timestamp version;

    public Offer()
    {
        items = new ArrayList<OfferItem>();
    }

    /**
     * Default constructor
     * @param name name to set
     * @param description description to set
     * @param price price to set
     */
    public Offer(String name, String description, BigDecimal price)
    {
        this();
        this.name = name;
        this.description = description;
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
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
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
     * @return the items
     */
    public List<OfferItem> getItems()
    {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<OfferItem> items)
    {
        this.items = items;
    }

    public void addOfferItem(OfferItem offerItem)
    {
        this.items.add(offerItem);
    }

    public BigDecimal getTotalItemPrice()
    {
        BigDecimal totalItemPrice = new BigDecimal(0);
        for (OfferItem offerItem : items)
        {
            totalItemPrice = totalItemPrice.add(offerItem.getPrice());
        }
        
        return totalItemPrice;
    }

    public BigDecimal getSaving()
    {
        return getTotalItemPrice().subtract(price);
    }

    public boolean isInStock()
    {
        for (OfferItem offerItem : items)
        {
            if (offerItem.getItem().getStockLevel() - offerItem.getQuantity() < 0)
            {
                return false;
            }
        }

        return true;
    }

}
