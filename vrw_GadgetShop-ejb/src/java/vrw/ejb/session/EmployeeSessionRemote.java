package vrw.ejb.session;

import vrw.ejb.entity.Employee;

/**
 *
 * @author viktor
 */


public interface EmployeeSessionRemote {

    public Employee register(Employee e);
    public void remove(Employee e);
    public boolean authenticate(String nickname, String password);

}
