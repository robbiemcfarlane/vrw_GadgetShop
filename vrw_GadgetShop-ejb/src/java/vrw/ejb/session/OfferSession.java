/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import java.io.Serializable;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vrw.ejb.entity.Offer;

/**
 *
 * @author viktor
 */
@Stateless
@Remote(OfferSessionRemote.class)
public class OfferSession{

    @PersistenceContext
    private EntityManager em;

    public void add(Offer o){
        em.persist(o);
    }

    public void update(Offer o)
    {
        em.persist(o);
    }


}
