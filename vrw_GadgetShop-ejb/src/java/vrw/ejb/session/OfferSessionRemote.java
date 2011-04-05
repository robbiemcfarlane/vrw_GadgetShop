/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import java.util.Collection;
import vrw.ejb.entity.Offer;

/**
 *
 * @author viktor
 */

public interface OfferSessionRemote {

    public Offer add(Offer o);
    public void update(Offer o);
    public Collection<Offer> findAll();
    public Offer find(int id);

}
