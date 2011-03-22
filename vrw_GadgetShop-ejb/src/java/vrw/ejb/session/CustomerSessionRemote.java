/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import java.util.Collection;
import vrw.ejb.entity.Customer;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Robbie
 */
public interface CustomerSessionRemote {

    public Collection<Customer> findAll();
    public Customer find(String nickname);
    public void register(Customer c);
    public void update(Customer c);
    public void remove(Customer c);
    public boolean login(HttpSession session, String nickname, String password);
    public boolean authenticate(String nickname, String password);

}
