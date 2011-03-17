/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Collection;
import vrw.ejb.entity.Customer;

/**
 *
 * @author Robbie
 */
@Stateless
public class CustomerSession implements CustomerSessionRemote, CustomerSessionLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    public Collection<Customer> findAll() {
        return em.createQuery("SELECT c FROM Customer c").getResultList();
    }

    public Customer find(String nickname) {
        return em.find(Customer.class, nickname);
    }

    public void register(Customer c) {
        em.persist(c);
    }

    public void update(Customer c) {
        em.persist(c);
    }

    public void remove(Customer c) {
        c.setActive(false);
        em.persist(c);
    }
}
