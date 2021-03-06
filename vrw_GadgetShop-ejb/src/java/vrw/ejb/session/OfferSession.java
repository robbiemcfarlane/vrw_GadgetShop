/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import java.util.Collection;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vrw.ejb.entity.Item;
import vrw.ejb.entity.Offer;

/**
 *
 * @author viktor
 */
@Stateless
@Remote(OfferSessionRemote.class)
public class OfferSession implements OfferSessionRemote, java.io.Serializable {

    @PersistenceContext
    private EntityManager em;

    public Offer add(Offer o){
        em.persist(o);
        return o;
    }

    public void update(Offer o)
    {
        em.merge(o);
    }

    /**
     * Finds all offers
     * @return all offers in the system
     */
    public Collection findAll()
    {
        return em.createQuery("SELECT o FROM Offer o").getResultList();
    }

    /**
     * Finds offer by its id
     * @param id of an offer to find
     * @return
     */
    public Offer find(int id)
    {
        return em.find(Offer.class, id);
    }

}
