/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vrw.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 *
 * @author viktor
 *
 * ToDo: Should email be here? 21032011-VP
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

    @Id
    @Column(length = 16)
    protected String nickname;
    @Column(name = "first_name", length = 50, nullable = false)
    protected String firstName;
    @Column(name = "last_name", length = 50, nullable = false)
    protected String lastName;
    @Column(name = "password", length = 32, nullable = false)
    protected String password;

    @Version
    private Timestamp version;

    /**
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nickname != null ? nickname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.nickname == null && other.nickname != null) || (this.nickname != null && !this.nickname.equals(other.nickname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nickname;
    }

}
