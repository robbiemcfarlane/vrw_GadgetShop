/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import javax.ejb.*;
import javax.persistence.*;
import java.util.Collection;
import vrw.ejb.entity.Item;

/**
 *
 * @author Robbie
 */
@Stateless
@Remote(ItemSessionRemote.class)
public class ItemSession implements ItemSessionRemote, java.io.Serializable {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    private static final long serialVersionUID = 1;

    public Item add(Item item)
    {
        em.persist(item);
        return item;
    }

    public void update(Item item)
    {
        em.merge(item);
    }
    
    public Item find(int id)
    {
        return em.find(Item.class, id);
    }

    public Collection<Item> findAll()
    {
        return em.createQuery("SELECT i FROM Item i").getResultList();
    }

    public Collection<Item> findAllOrderBy(String field, String direction)
    {
        String query = "SELECT i FROM Item i ORDER BY i."+field+" "+direction;
        return em.createQuery(query).getResultList();
    }

    public Collection<Item> findAllInShopWindow()
    {
        return em.createQuery("SELECT i FROM Item i WHERE i.inShopWindow = TRUE").getResultList();
    }

    public Collection<Item> search(String searchString)
    {
        String query =
                "SELECT i FROM Item i WHERE i.id = :searchString "+
                "OR UPPER(i.name) LIKE :searchString "+
                "OR UPPER(i.desc) LIKE :searchString";

        return em.createQuery(query)
                .setParameter("searchString", searchString.toUpperCase())
                .getResultList();
    }
}
