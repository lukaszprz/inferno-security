/**
 *
 */
package pl.inferno.security.form;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.inferno.security.core.model.Role;

/**
 * Class AdminRoleForm
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class AdminRoleForm {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminRoleForm.class);

	private Long id;

	private List<Role> rolesList = new ArrayList<>();

	private List<Long> selectedRoles = new ArrayList<>();

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
	 * @return the rolesList
	 */
	public List<Role> getRolesList() {
		return rolesList;
	}

	/**
	 * @param rolesList
	 *            the rolesList to set
	 */
	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	/**
	 * @return the selectedRoles
	 */
	public List<Long> getSelectedRoles() {
		return selectedRoles;
	}

	/**
	 * @param selectedRoles
	 *            the selectedRoles to set
	 */
	public void setSelectedRoles(List<Long> selectedRoles) {
		this.selectedRoles = selectedRoles;
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
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		result = (prime * result) + ((rolesList == null) ? 0 : rolesList.hashCode());
		result = (prime * result) + ((selectedRoles == null) ? 0 : selectedRoles.hashCode());
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
		if (!(obj instanceof AdminRoleForm)) {
			return false;
		}
		AdminRoleForm other = (AdminRoleForm) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (rolesList == null) {
			if (other.rolesList != null) {
				return false;
			}
		} else if (!rolesList.equals(other.rolesList)) {
			return false;
		}
		if (selectedRoles == null) {
			if (other.selectedRoles != null) {
				return false;
			}
		} else if (!selectedRoles.equals(other.selectedRoles)) {
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
		builder.append("AdminRoleForm [");
		if (id != null) {
			builder.append("id=").append(id).append(", ");
		}
		if (rolesList != null) {
			builder.append("rolesList=").append(rolesList).append(", ");
		}
		if (selectedRoles != null) {
			builder.append("selectedRoles=").append(selectedRoles);
		}
		builder.append("]");
		return builder.toString();
	}
}
