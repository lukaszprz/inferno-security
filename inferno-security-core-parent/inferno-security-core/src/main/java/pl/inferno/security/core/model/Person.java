/**
 *
 */
package pl.inferno.security.core.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author lukasz-adm
 */
@Entity
@Table(schema = "inferno_authorization_schema", name = "inferno_person")
public class Person extends InfernoAbstractAuditableEntity implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 3728079909935704665L;

    @Id
    @SequenceGenerator(name = "person_seq_gen", sequenceName = "inferno_person_seq", allocationSize = 1)
    @GeneratedValue(generator = "person_seq_gen")
    @Column(name = "person_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @JsonBackReference(value = "person-user")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonManagedReference(value = "address-person")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "person")
    private Set<Address> addresses;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
        // result = (prime * result) + ((addresses == null) ? 0 : addresses.hashCode());
        result = (prime * result) + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
        result = (prime * result) + ((firstName == null) ? 0 : firstName.hashCode());
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());
        result = (prime * result) + ((lastName == null) ? 0 : lastName.hashCode());
        // result = (prime * result) + ((user == null) ? 0 : user.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        // if (addresses == null) {
        // if (other.addresses != null) {
        // return false;
        // }
        // } else if (!addresses.equals(other.addresses)) {
        // return false;
        // }
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
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        // if (user == null) {
        // if (other.user != null) {
        // return false;
        // }
        // } else if (!user.equals(other.user)) {
        // return false;
        // }
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
        if (id != null) {
            builder.append("id=").append(id).append(", ");
        }
        if (firstName != null) {
            builder.append("firstName=").append(firstName).append(", ");
        }
        if (lastName != null) {
            builder.append("lastName=").append(lastName).append(", ");
        }
        if (dateOfBirth != null) {
            builder.append("dateOfBirth=").append(dateOfBirth).append(", ");
        }
        // if (user != null) {
        // builder.append("user=").append(user).append(", ");
        // }
        // if (addresses != null) {
        // builder.append("addresses=").append(addresses);
        // }
        builder.append("]");
        return builder.toString();
    }

}
