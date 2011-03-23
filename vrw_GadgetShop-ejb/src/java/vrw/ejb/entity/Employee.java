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
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
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
     * @param firstName firstname to set
     * @param lastName lastname to set
     * @param password password to set
     */
    public Employee(String nickname, String firstName, String lastName, String password)
    {
        super.setNickname(nickname);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setPassword(password);
    }

}
