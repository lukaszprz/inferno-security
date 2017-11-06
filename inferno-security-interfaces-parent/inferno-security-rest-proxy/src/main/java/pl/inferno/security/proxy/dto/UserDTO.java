/**
 *
 */
package pl.inferno.security.proxy.dto;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @author lukasz-adm
 *
 */
public class UserDTO {

	/**
	 *
	 */
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	private Long id;

	private String username;

	private String password;

	private boolean active;

	private Timestamp validTo;

	private Timestamp created = new Timestamp(System.currentTimeMillis());;

	private Set<RoleDTO> roles;

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
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	public Set<RoleDTO> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
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
		result = (prime * result) + (active ? 1231 : 1237);
		result = (prime * result) + ((created == null) ? 0 : created.hashCode());
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		result = (prime * result) + ((password == null) ? 0 : password.hashCode());
		result = (prime * result) + ((roles == null) ? 0 : roles.hashCode());
		result = (prime * result) + ((username == null) ? 0 : username.hashCode());
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
		UserDTO other = (UserDTO) obj;
		if (active != other.active) {
			return false;
		}
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
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
		return String.format("UserDTO [id=%s, username=%s, password=%s, active=%s, validTo=%s, created=%s, roles=%s]",
		        id, username, password, active, validTo, created, roles);
	}

}
