package vrw.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author Robbie
 */
@Entity
public class Item implements Serializable
{
    private static final long serialVersionUID = 1;
    
    @Id
    @GeneratedValue
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(name="long_desc", length = 1000, nullable = false)
    private String longDesc;
    @Column(name="short_desc", length = 50, nullable = true)
    private String shortDesc;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name="vip_price", nullable = false)
    private BigDecimal vipPrice;
    @Column(name="stock_level", nullable = false)
    private int stockLevel;
    @Column(name="in_shop_window", nullable = false)
    private boolean inShopWindow;

    @Version
    private Timestamp version;

    public Item() { }

    public Item(String name, String longDesc, String shortDesc, BigDecimal price, BigDecimal vipPrice, int stockLevel, boolean inShopWindow)
    {
        this.name = name;
        this.longDesc = longDesc;
        this.shortDesc = shortDesc;
        this.price = price;
        this.vipPrice = vipPrice;
        this.stockLevel = stockLevel;
        this.inShopWindow = inShopWindow;
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
     * @return the longDesc
     */
    public String getLongDesc()
    {
        return longDesc;
    }

    /**
     * @param longDesc the longDesc to set
     */
    public void setLongDesc(String longDesc)
    {
        this.longDesc = longDesc;
    }

    /**
     * @return the shortDesc
     */
    public String getShortDesc()
    {
        return shortDesc;
    }

    /**
     * @param shortDesc the shortDesc to set
     */
    public void setShortDesc(String shortDesc)
    {
        this.shortDesc = shortDesc;
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
     * @return the vipPrice
     */
    public BigDecimal getVipPrice()
    {
        return vipPrice;
    }

    /**
     * @param vipPrice the vipPrice to set
     */
    public void setVipPrice(BigDecimal vipPrice)
    {
        this.vipPrice = vipPrice;
    }

    /**
     * @return the stockLevel
     */
    public int getStockLevel()
    {
        return stockLevel;
    }

    /**
     * @param stockLevel the stockLevel to set
     */
    public void setStockLevel(int stockLevel)
    {
        this.stockLevel = stockLevel;
    }

    /**
     * @return the inShopWindow
     */
    public boolean isInShopWindow()
    {
        return inShopWindow;
    }

    /**
     * @param inShopWindow the inShopWindow to set
     */
    public void setInShopWindow(boolean inShopWindow)
    {
        this.inShopWindow = inShopWindow;
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

    public String getMiniDesc()
    {
        return (this.shortDesc != null) ? this.shortDesc : this.longDesc.substring(0, Math.min(20, this.longDesc.length())) + " ...";
    }
    
    public void incrementStockLevel(int increment)
    {
        stockLevel += increment;
    }

    public void decrementStockLevel(int decrement)
            throws StockException
    {
        if (decrement > stockLevel)
        {
            throw new StockException("Decrement greater than stock level");
        }
        stockLevel -= decrement;
    }

}
