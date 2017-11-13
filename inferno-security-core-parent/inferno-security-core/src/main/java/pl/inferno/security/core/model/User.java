/**
 *
 */
package pl.inferno.security.core.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author lukasz-adm
 *
 */
@Entity
@Table(schema = "inferno_authorization_schema", name = "inferno_users")
public class User implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 6040407139493365688L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "expires", nullable = false)
	private Timestamp expires;

	@Column(name = "is_expired", nullable = false)
	private boolean accountExpired;

	@Column(name = "is_locked", nullable = false)
	private boolean accountLocked;

	@Column(name = "credentials_expired", nullable = false)
	private boolean credentialsExpired;

	@Column(name = "created", nullable = false, updatable = false)
	private Timestamp created = new Timestamp(System.currentTimeMillis());;

	// @ManyToMany(cascade = CascadeType.ALL)
	// @JoinTable(schema = "inferno_authorization_schema", name = "user_role",
	// joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns =
	// @JoinColumn(name = "role_id"))
	@Transient
	private Set<Role> roles;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<UserRoles> assignedAuthorities;

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
	 * @return the username
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the accountEnabled
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param accountEnabled
	 *            the accountEnabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the expires
	 */
	public Timestamp getExpires() {
		return expires;
	}

	/**
	 * @param expires
	 *            the expires to set
	 */
	public void setExpires(Timestamp expires) {
		this.expires = expires;
	}

	/**
	 * @return the accountExpired
	 */
	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @param accountExpired
	 *            the accountExpired to set
	 */
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	/**
	 * @return the accountLocked
	 */
	public boolean isAccountLocked() {
		return accountLocked;
	}

	/**
	 * @param accountLocked
	 *            the accountLocked to set
	 */
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	/**
	 * @return the credentialsExpired
	 */
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @param credentialsExpired
	 *            the credentialsExpired to set
	 */
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
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
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired
	 * ()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked(
	 * )
	 */
	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	/**
	 * @return the assignedAuthorities
	 */
	public Set<UserRoles> getAssignedAuthorities() {
		return assignedAuthorities;
	}

	/**
	 * @param assignedAuthorities
	 *            the assignedAuthorities to set
	 */
	public void setAssignedAuthorities(Set<UserRoles> assignedAuthorities) {
		this.assignedAuthorities = assignedAuthorities;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return assignedAuthorities;
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
		result = (prime * result) + (accountExpired ? 1231 : 1237);
		result = (prime * result) + (accountLocked ? 1231 : 1237);
		result = (prime * result) + ((assignedAuthorities == null) ? 0 : assignedAuthorities.hashCode());
		result = (prime * result) + ((created == null) ? 0 : created.hashCode());
		result = (prime * result) + (credentialsExpired ? 1231 : 1237);
		result = (prime * result) + (enabled ? 1231 : 1237);
		result = (prime * result) + ((expires == null) ? 0 : expires.hashCode());
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		result = (prime * result) + ((password == null) ? 0 : password.hashCode());
		result = (prime * result) + ((roles == null) ? 0 : roles.hashCode());
		result = (prime * result) + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (accountExpired != other.accountExpired) {
			return false;
		}
		if (accountLocked != other.accountLocked) {
			return false;
		}
		if (assignedAuthorities == null) {
			if (other.assignedAuthorities != null) {
				return false;
			}
		} else if (!assignedAuthorities.equals(other.assignedAuthorities)) {
			return false;
		}
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (credentialsExpired != other.credentialsExpired) {
			return false;
		}
		if (enabled != other.enabled) {
			return false;
		}
		if (expires == null) {
			if (other.expires != null) {
				return false;
			}
		} else if (!expires.equals(other.expires)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (roles == null) {
			if (other.roles != null) {
				return false;
			}
		} else if (!roles.equals(other.roles)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
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
		return String.format(
		        "User [id=%s, username=%s, password=%s, enabled=%s, expires=%s, accountExpired=%s, accountLocked=%s, credentialsExpired=%s, created=%s, roles=%s, assignedAuthorities=%s]",
		        id, username, password, enabled, expires, accountExpired, accountLocked, credentialsExpired, created,
		        roles, assignedAuthorities);
	}

}
