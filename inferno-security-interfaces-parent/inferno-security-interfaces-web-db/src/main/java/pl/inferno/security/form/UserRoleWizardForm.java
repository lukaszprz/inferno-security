/**
 *
 */
package pl.inferno.security.form;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.inferno.security.core.model.Role;
import pl.inferno.security.core.model.User;
import pl.inferno.security.core.model.UserRoles;

/**
 * Class UserRoleWizardForm
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class UserRoleWizardForm {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleWizardForm.class);

	private String pageName;

	private int step;

	private Long id;

	private User selectedUser;

	private Role role;

	private UserRoles roleAssigment;

	private String authority;

	private DateTime validFrom;

	private DateTime validTo;

	private String roleName;

	private List<User> allUsers = new ArrayList<>();

	private List<Role> allRoles = new ArrayList<>();

	private List<UserRoles> assignedRoles = new ArrayList<>();

	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 *            the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}

	/**
	 * @param step
	 *            the step to set
	 */
	public void setStep(int step) {
		this.step = step;
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

	/**
	 * @return the selectedUser
	 */
	public User getSelectedUser() {
		return selectedUser;
	}

	/**
	 * @param selectedUser
	 *            the user to set
	 */
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the roleAssigment
	 */
	public UserRoles getRoleAssigment() {
		return roleAssigment;
	}

	/**
	 * @param roleAssigment
	 *            the roleAssigment to set
	 */
	public void setRoleAssigment(UserRoles roleAssigment) {
		this.roleAssigment = roleAssigment;
	}

	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority
	 *            the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * @return the validFrom
	 */
	public DateTime getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom
	 *            the validFrom to set
	 */
	public void setValidFrom(DateTime validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return the validTo
	 */
	public DateTime getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo
	 *            the validTo to set
	 */
	public void setValidTo(DateTime validTo) {
		this.validTo = validTo;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the allUsers
	 */
	public List<User> getAllUsers() {
		return allUsers;
	}

	/**
	 * @param allUsers
	 *            the allUsers to set
	 */
	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	/**
	 * @return the allRoles
	 */
	public List<Role> getAllRoles() {
		return allRoles;
	}

	/**
	 * @param allRoles
	 *            the allRoles to set
	 */
	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	/**
	 * @return the assignedRoles
	 */
	public List<UserRoles> getAssignedRoles() {
		return assignedRoles;
	}

	/**
	 * @param assignedRoles
	 *            the assignedRoles to set
	 */
	public void setAssignedRoles(List<UserRoles> assignedRoles) {
		this.assignedRoles = assignedRoles;
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
		result = (prime * result) + ((allRoles == null) ? 0 : allRoles.hashCode());
		result = (prime * result) + ((allUsers == null) ? 0 : allUsers.hashCode());
		result = (prime * result) + ((assignedRoles == null) ? 0 : assignedRoles.hashCode());
		result = (prime * result) + ((authority == null) ? 0 : authority.hashCode());
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		result = (prime * result) + ((pageName == null) ? 0 : pageName.hashCode());
		result = (prime * result) + ((role == null) ? 0 : role.hashCode());
		result = (prime * result) + ((roleAssigment == null) ? 0 : roleAssigment.hashCode());
		result = (prime * result) + ((roleName == null) ? 0 : roleName.hashCode());
		result = (prime * result) + step;
		result = (prime * result) + ((selectedUser == null) ? 0 : selectedUser.hashCode());
		result = (prime * result) + ((validFrom == null) ? 0 : validFrom.hashCode());
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
		if (!(obj instanceof UserRoleWizardForm)) {
			return false;
		}
		UserRoleWizardForm other = (UserRoleWizardForm) obj;
		if (allRoles == null) {
			if (other.allRoles != null) {
				return false;
			}
		} else if (!allRoles.equals(other.allRoles)) {
			return false;
		}
		if (allUsers == null) {
			if (other.allUsers != null) {
				return false;
			}
		} else if (!allUsers.equals(other.allUsers)) {
			return false;
		}
		if (assignedRoles == null) {
			if (other.assignedRoles != null) {
				return false;
			}
		} else if (!assignedRoles.equals(other.assignedRoles)) {
			return false;
		}
		if (authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!authority.equals(other.authority)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (pageName == null) {
			if (other.pageName != null) {
				return false;
			}
		} else if (!pageName.equals(other.pageName)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (roleAssigment == null) {
			if (other.roleAssigment != null) {
				return false;
			}
		} else if (!roleAssigment.equals(other.roleAssigment)) {
			return false;
		}
		if (roleName == null) {
			if (other.roleName != null) {
				return false;
			}
		} else if (!roleName.equals(other.roleName)) {
			return false;
		}
		if (step != other.step) {
			return false;
		}
		if (selectedUser == null) {
			if (other.selectedUser != null) {
				return false;
			}
		} else if (!selectedUser.equals(other.selectedUser)) {
			return false;
		}
		if (validFrom == null) {
			if (other.validFrom != null) {
				return false;
			}
		} else if (!validFrom.equals(other.validFrom)) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("UserRoleWizardForm [");
		if (pageName != null) {
			builder.append("pageName=").append(pageName).append(", ");
		}
		builder.append("step=").append(step).append(", ");
		if (id != null) {
			builder.append("id=").append(id).append(", ");
		}
		if (selectedUser != null) {
			builder.append("user=").append(selectedUser).append(", ");
		}
		if (role != null) {
			builder.append("role=").append(role).append(", ");
		}
		if (roleAssigment != null) {
			builder.append("roleAssigment=").append(roleAssigment).append(", ");
		}
		if (authority != null) {
			builder.append("authority=").append(authority).append(", ");
		}
		if (validFrom != null) {
			builder.append("validFrom=").append(validFrom).append(", ");
		}
		if (validTo != null) {
			builder.append("validTo=").append(validTo).append(", ");
		}
		if (roleName != null) {
			builder.append("roleName=").append(roleName).append(", ");
		}
		if (allUsers != null) {
			builder.append("allUsers=").append(allUsers).append(", ");
		}
		if (allRoles != null) {
			builder.append("allRoles=").append(allRoles).append(", ");
		}
		if (assignedRoles != null) {
			builder.append("assignedRoles=").append(assignedRoles);
		}
		builder.append("]");
		return builder.toString();
	}

}
