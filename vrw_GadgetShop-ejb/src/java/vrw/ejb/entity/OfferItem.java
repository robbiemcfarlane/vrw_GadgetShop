/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 * @author viktor
 */

@Entity
public class OfferItem {

    @Id
    @GeneratedValue
    private int id;
        
    @ManyToOne
    private Item item;

    @ManyToOne
    private Offer offer;

    @Version
    private Timestamp version;

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
     * @return the version
     */
    public Timestamp getVersion()
    {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Timestamp version)
    {
        this.version = version;
    }



}
