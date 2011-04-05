/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.session;

import java.util.Collection;
import vrw.ejb.entity.Customer;

/**
 *
 * @author Robbie
 */
public interface CustomerSessionRemote
{

    public Collection<Customer> findAll();

    public Customer find(String nickname);

    public Customer register(Customer c);

    public void update(Customer c);

    public void remove(Customer c);

    public boolean authenticate(String nickname, String password);
    
}
