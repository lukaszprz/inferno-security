/**
 *
 */
package pl.inferno.security.core.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Size(min = 3, max = 255, message = "{person.firstName.empty}")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 3, max = 255, message = "{person.lastName.empty}")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "email", nullable = true, insertable = true, unique = true, updatable = true)
    // @Email(message = "{person.email.invalid}")
    private String email;

    @Column(name = "home_phone_number")
    private String homePhoneNumber;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @JsonBackReference(value = "person-user")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "fk_person")
    private List<Address> addresses;

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
    public List<Address> getAddresses() {
	return addresses;
    }

    /**
     * @param addresses
     *            the addresses to set
     */
    public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
    }

    /**
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * @return the homePhoneNumber
     */
    public String getHomePhoneNumber() {
	return homePhoneNumber;
    }

    /**
     * @param homePhoneNumber
     *            the homePhoneNumber to set
     */
    public void setHomePhoneNumber(String homePhoneNumber) {
	this.homePhoneNumber = homePhoneNumber;
    }

    /**
     * @return the mobilePhoneNumber
     */
    public String getMobilePhoneNumber() {
	return mobilePhoneNumber;
    }

    /**
     * @param mobilePhoneNumber
     *            the mobilePhoneNumber to set
     */
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
	this.mobilePhoneNumber = mobilePhoneNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = (prime * result) + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
	result = (prime * result) + ((email == null) ? 0 : email.hashCode());
	result = (prime * result) + ((firstName == null) ? 0 : firstName.hashCode());
	result = (prime * result) + ((homePhoneNumber == null) ? 0 : homePhoneNumber.hashCode());
	result = (prime * result) + ((id == null) ? 0 : id.hashCode());
	result = (prime * result) + ((lastName == null) ? 0 : lastName.hashCode());
	result = (prime * result) + ((mobilePhoneNumber == null) ? 0 : mobilePhoneNumber.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!super.equals(obj)) {
	    return false;
	}
	if (!(obj instanceof Person)) {
	    return false;
	}
	Person other = (Person) obj;
	if (dateOfBirth == null) {
	    if (other.dateOfBirth != null) {
		return false;
	    }
	} else if (!dateOfBirth.equals(other.dateOfBirth)) {
	    return false;
	}
	if (email == null) {
	    if (other.email != null) {
		return false;
	    }
	} else if (!email.equals(other.email)) {
	    return false;
	}
	if (firstName == null) {
	    if (other.firstName != null) {
		return false;
	    }
	} else if (!firstName.equals(other.firstName)) {
	    return false;
	}
	if (homePhoneNumber == null) {
	    if (other.homePhoneNumber != null) {
		return false;
	    }
	} else if (!homePhoneNumber.equals(other.homePhoneNumber)) {
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
	if (mobilePhoneNumber == null) {
	    if (other.mobilePhoneNumber != null) {
		return false;
	    }
	} else if (!mobilePhoneNumber.equals(other.mobilePhoneNumber)) {
	    return false;
	}
	return true;
    }

    /*
     * (non-Javadoc)
     * 
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
	if (email != null) {
	    builder.append("email=").append(email).append(", ");
	}
	if (homePhoneNumber != null) {
	    builder.append("homePhoneNumber=").append(homePhoneNumber).append(", ");
	}
	if (mobilePhoneNumber != null) {
	    builder.append("mobilePhoneNumber=").append(mobilePhoneNumber);
	}
	builder.append("]");
	return builder.toString();
    }

}
