/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Shop employee. 
 * 
 * 
 * @author viktor
 */
@Entity
public class Employee extends Person implements Serializable {

    /**
     * Default constructor
     */
    public Employee()
    {
        super();
    }

    /**
     *
     * @param nickname nickname to set
     * @param firstname firstname to set
     * @param lastname lastname to set
     * @param password password to set
     */
    public Employee(String nickname, String firstname, String lastname, String password)
    {
        super.setNickname(nickname);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setPassword(password);
    }

}
