/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Robbie
 */
@Entity(name = "\"order\"") // "Order" is an SQL reserved word
public class Order implements java.io.Serializable
{

    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
    private List<OrderItem> items;

    public Order()
    {
        items = new ArrayList<OrderItem>();
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
     * @return the items
     */
    public List<OrderItem> getItems()
    {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<OrderItem> items)
    {
        this.items = items;
    }

    public void addItem(OrderItem orderItem)
    {
        items.add(orderItem);
        orderItem.setOrder(this);
    }

    @Override
    public String toString()
    {
        String str = "Thanks! Order ID: " + getId();
        return str;
    }
}
