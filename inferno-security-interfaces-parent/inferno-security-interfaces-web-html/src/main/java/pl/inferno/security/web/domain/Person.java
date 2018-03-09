/**
 * Project: inferno-security-interfaces-web-html
 * File: Person.java
 * Package: pl.inferno.security.web.domain
 * Location:
 * 9 mar 2018 05:12:54 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.web.domain;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Class Person
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@JsonRootName(value = "person")
public class Person {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private Set<Address> addresses;

    /**
     *
     */
    public Person() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
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
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     *            the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the addresses
     */
    public Set<Address> getAddresses() {
        return addresses;
    }

    /**
     * @param addresses
     *            the addresses to set
     */
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((addresses == null) ? 0 : addresses.hashCode());
        result = (prime * result) + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
        result = (prime * result) + ((firstName == null) ? 0 : firstName.hashCode());
        result = (prime * result) + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        if (addresses == null) {
            if (other.addresses != null) {
                return false;
            }
        } else if (!addresses.equals(other.addresses)) {
            return false;
        }
        if (dateOfBirth == null) {
            if (other.dateOfBirth != null) {
                return false;
            }
        } else if (!dateOfBirth.equals(other.dateOfBirth)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person [");
        if (firstName != null) {
            builder.append("firstName=").append(firstName).append(", ");
        }
        if (lastName != null) {
            builder.append("lastName=").append(lastName).append(", ");
        }
        if (dateOfBirth != null) {
            builder.append("dateOfBirth=").append(dateOfBirth).append(", ");
        }
        if (addresses != null) {
            builder.append("addresses=").append(addresses);
        }
        builder.append("]");
        return builder.toString();
    }

}
