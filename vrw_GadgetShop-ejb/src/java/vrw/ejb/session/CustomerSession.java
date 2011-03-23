/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import javax.ejb.*;
import javax.persistence.*;
import java.util.Collection;
import vrw.ejb.entity.Customer;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Robbie, Viktor, Will
 */
@Stateless
@Remote(CustomerSessionRemote.class)
public class CustomerSession implements CustomerSessionRemote, java.io.Serializable  {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    private static final long serialVersionUID = 1;

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

    public boolean login(HttpSession session, String nickname, String password)
    {
        boolean authenticated = authenticate(nickname,password);
        if(authenticated)
        {
            session.setAttribute("customer",find(nickname));
        }
        return authenticated;   
    }

    /**
     * Authenticates customer
     *
     * @param nickname customer's nickname
     * @param password customer's password
     * @return true if nickname and password match, false otherwise.
     */
    public boolean authenticate(String nickname, String password){
        Customer c = find(nickname);
        if (c!=null)
        {
            return c.getPassword().equals(password);
        }
        else
        {
            return false;
        }
    }
}
