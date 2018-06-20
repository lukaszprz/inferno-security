/**
 *
 */
package pl.inferno.security.form;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.inferno.security.core.model.User;

/**
 * Class AdminUserForm
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class AdminUserForm {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserForm.class);

	private List<User> usersList = new ArrayList<>();

	private List<Long> selectedUsers = new ArrayList<>();

	private Long id;

	/**
	 * @return the usersList
	 */
	public List<User> getUsersList() {
		return usersList;
	}

	/**
	 * @param usersList
	 *            the usersList to set
	 */
	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	/**
	 * @return the selectedUsers
	 */
	public List<Long> getSelectedUsers() {
		return selectedUsers;
	}

	/**
	 * @param selectedUsers
	 *            the selectedUsers to set
	 */
	public void setSelectedUsers(List<Long> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

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
		result = (prime * result) + ((selectedUsers == null) ? 0 : selectedUsers.hashCode());
		result = (prime * result) + ((usersList == null) ? 0 : usersList.hashCode());
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
		if (!(obj instanceof AdminUserForm)) {
			return false;
		}
		AdminUserForm other = (AdminUserForm) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (selectedUsers == null) {
			if (other.selectedUsers != null) {
				return false;
			}
		} else if (!selectedUsers.equals(other.selectedUsers)) {
			return false;
		}
		if (usersList == null) {
			if (other.usersList != null) {
				return false;
			}
		} else if (!usersList.equals(other.usersList)) {
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
		builder.append("AdminUserForm [");
		if (usersList != null) {
			builder.append("usersList=").append(usersList).append(", ");
		}
		if (selectedUsers != null) {
			builder.append("selectedUsers=").append(selectedUsers).append(", ");
		}
		if (id != null) {
			builder.append("id=").append(id);
		}
		builder.append("]");
		return builder.toString();
	}

}
