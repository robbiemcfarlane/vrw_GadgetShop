/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import vrw.ejb.entity.Offer;

/**
 *
 * @author viktor
 */

public interface OfferSessionRemote {

    public void add(Offer o);
    public void update(Offer o);   

}
