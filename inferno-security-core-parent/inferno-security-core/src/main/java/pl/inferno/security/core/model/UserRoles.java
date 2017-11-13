/**
 *
 */
package pl.inferno.security.core.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author lukasz-adm
 *
 */
@Entity
@IdClass(UserRoles.class)
@Table(schema = "inferno_authorization_schema", name = "inferno_roles_assigment")
public class UserRoles implements GrantedAuthority {

	/**
	 *
	 */
	private static final long serialVersionUID = -7965012653644807073L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	@Id
	@Column(name = "authority", nullable = false)
	private String authority;

	@Column(name = "created", nullable = false)
	private Timestamp created;

	@Column(name = "valid_to")
	private Timestamp validTo;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return the created
	 */
	public Timestamp getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Timestamp created) {
		this.created = created;
	}

	/**
	 * @return the validTo
	 */
	public Timestamp getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo
	 *            the validTo to set
	 */
	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	/**
	 * @param authority
	 *            the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((authority == null) ? 0 : authority.hashCode());
		result = (prime * result) + ((created == null) ? 0 : created.hashCode());
		result = (prime * result) + ((user == null) ? 0 : user.hashCode());
		result = (prime * result) + ((validTo == null) ? 0 : validTo.hashCode());
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
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserRoles other = (UserRoles) obj;
		if (authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!authority.equals(other.authority)) {
			return false;
		}
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		if (validTo == null) {
			if (other.validTo != null) {
				return false;
			}
		} else if (!validTo.equals(other.validTo)) {
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
		return String.format("UserRoles [user=%s, authority=%s, created=%s, validTo=%s]", user, authority, created,
		        validTo);
	}

}
