/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.session;

import vrw.ejb.entity.StockException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Remove;
import vrw.ejb.entity.BasketItem;
import vrw.ejb.entity.Item;

/**
 *
 * @author Robbie
 */
public interface ShoppingBasketSessionRemote
{

    @javax.annotation.PostConstruct
    public void initialize();
    
    public HashMap<Integer, BasketItem> getItems();

    public Collection<BasketItem> getItemsAsCollection();

    public void addItem(Item item, int quantity) throws StockException;

    public int getNumItems();

    public BigDecimal getTotal();

    public BasketItem removeItem(int itemId);

    @Remove
    public void terminate();
}
