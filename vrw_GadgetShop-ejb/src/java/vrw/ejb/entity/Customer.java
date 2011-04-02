/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Robbie McFarlane, Will Atterson, Viktor Peacock
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Customer extends Person implements Serializable {

    @Column(length=50,nullable=false)
    private String address1;
    @Column(length=50, nullable=false)
    private String address2;
    @Column(length=50, nullable=false)
    private String city;
    @Column(length=50, nullable=false)
    private String county;
    @Column(length=30, nullable=false)
    private String postcode;
    @Column(length=80, nullable=false)
    private String country;
    @Column(length=100, nullable=false)
    private String email;
    @Column(name="is_active", nullable=false)
    private boolean active;
    
    @Column(name="vip_expiry_date", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date vipExpiryDate;

    /**
     * Empty constructor
     */
    public Customer(){}

    /**
     * Default constructor. New customers are marked as active by default.
     * @param address1 address1 to set
     * @param address2 address2 to set
     * @param city city to set
     * @param county county to set
     * @param postcode postcode to set
     * @param country country to set
     * @param email email to set
     */
    public Customer(String nickname, String firstName, String lastName, String password, String address1, String address2, String city,
                        String county, String postcode, String country,
                        String email)
    {
        super.nickname = nickname;
        super.firstName = firstName;
        super.lastName = lastName;
        super.password = password;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
        this.country = country;
        this.email = email;
        this.active = true;
    }

    /**
     * Constructor for use in the login process.
     *
     * @param nickname  the nickname to set
     * @param password  the password to set
     */
    public Customer(String nickname, String password)
    {
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);

    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the vipExpiryDate
     */
    public Date getVipExpiryDate()
    {
        return vipExpiryDate;
    }

    /**
     * @param vipExpiryDate the vipExpiryDate to set
     */
    public void setVipExpiryDate(Date vipExpiryDate)
    {
        this.vipExpiryDate = vipExpiryDate;
    }

    public boolean isVIP()
    {
        // If a VIP expiry date has been set (i.e., it is not null) AND
        // the expiry date is greater than the current date, then the customer is a VIP
        if(vipExpiryDate != null && vipExpiryDate.compareTo(new Date()) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }



    /*public boolean isVIP()
    {
        InitialContext context = new InitialContext();

        OrderSessionRemote orderSession = (OrderSessionRemote) context.lookup("vrw_GadgetShop/OrderSession/remote");

        ArrayList<Order> pastYearOrders = orderSession.findPastYearOrdersForCustomer(this);

        BigDecimal total = new BigDecimal(0);
        for(Order order : pastYearOrders)
        {
            total = total.add(order.getTotal());
        }

        Order lastOrder = pastYearOrders.get(pastYearOrders.size()-1);

        if(total.compareTo(new BigDecimal(200)) >= 0 && (lastOrder.getDate() - NOW() < 6 MONTHS))
        {
            return true;
        }
        else
        {
            return false;
        }
    }*/
    
}
