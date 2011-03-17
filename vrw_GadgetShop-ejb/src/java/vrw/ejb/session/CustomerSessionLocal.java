/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import javax.ejb.Local;
import java.util.Collection;
import vrw.ejb.entity.Customer;

/**
 *
 * @author Robbie
 */
@Local
public interface CustomerSessionLocal {

    public Collection<Customer> findAll();
    public Customer find(String nickname);
    public void register(Customer c);
    public void update(Customer c);
    public void remove(Customer c);

}
