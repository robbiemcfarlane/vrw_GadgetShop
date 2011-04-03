/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author viktor
 */

@Entity()
public class Offer implements Serializable{

    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Id
    private int id;

    @Column(name="name",length=50, nullable=false)
    private String name;

    @Column(name="description", length=500, nullable=false)
    private String description;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @Version
    private java.sql.Timestamp version;

    public Offer()
    {

    }

    /**
     * Default constructor
     * @param name name to set
     * @param description description to set
     * @param price price to set
     */
    public Offer(String name, String description, BigDecimal price)
    {
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

    

}
