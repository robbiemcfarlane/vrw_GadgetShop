/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vrw.ejb.entity.Employee;

/**
 *
 * @author viktor
 */
@Stateless
@Remote(EmployeeSessionRemote.class)
public class EmployeeService implements EmployeeSessionRemote {

    @PersistenceContext
    private EntityManager em;
    private static final long serialVersionUID = 1;

    /**
     * Register employee
     * @param e employee to register
     */
    public void register(Employee e)
    {
        em.persist(e);
    }

    /**
     * Remove employee
     * @param e employee to remove
     */
    public void remove(Employee e)
    {
        em.remove(e);
    }

    /**
     * Authenticate employee
     * @param nickname employee's nickname
     * @param password employee's password
     * @return
     */
    public boolean authenticate(String nickname, String password)
    {
        Employee employee = em.find(Employee.class, "nickname");
        if (employee != null)
        {
            return employee.getPassword().equals(password);
        }
        else
        {
            return false;
        }
    }




}
