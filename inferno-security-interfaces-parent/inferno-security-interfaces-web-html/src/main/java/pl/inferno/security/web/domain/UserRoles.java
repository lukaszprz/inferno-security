/**
 * Project: inferno-security-interfaces-web-html
 * File: UserRoles.java
 * Package: pl.inferno.security.web.domain
 * Location:
 * 9 mar 2018 05:24:36 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.web.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Class UserRoles
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@JsonRootName(value = "userRoles")
public class UserRoles {

    private UserDetails user;

    private Role assignedRole;

    private String authority;

    private Timestamp validTo;

    /**
     * @param user
     * @param assignedRole
     * @param authority
     * @param validTo
     */
    public UserRoles(UserDetails user, Role assignedRole, String authority, Timestamp validTo) {
        this.user = user;
        this.assignedRole = assignedRole;
        this.authority = authority;
        this.validTo = validTo;
    }

    /**
     *
     */
    public UserRoles() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the user
     */
    public UserDetails getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserDetails user) {
        this.user = user;
    }

    /**
     * @return the assignedRole
     */
    public Role getAssignedRole() {
        return assignedRole;
    }

    /**
     * @param assignedRole
     *            the assignedRole to set
     */
    public void setAssignedRole(Role assignedRole) {
        this.assignedRole = assignedRole;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((assignedRole == null) ? 0 : assignedRole.hashCode());
        result = (prime * result) + ((authority == null) ? 0 : authority.hashCode());
        result = (prime * result) + ((user == null) ? 0 : user.hashCode());
        result = (prime * result) + ((validTo == null) ? 0 : validTo.hashCode());
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
        if (!(obj instanceof UserRoles)) {
            return false;
        }
        UserRoles other = (UserRoles) obj;
        if (assignedRole == null) {
            if (other.assignedRole != null) {
                return false;
            }
        } else if (!assignedRole.equals(other.assignedRole)) {
            return false;
        }
        if (authority == null) {
            if (other.authority != null) {
                return false;
            }
        } else if (!authority.equals(other.authority)) {
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
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserRoles [");
        if (user != null) {
            builder.append("user=").append(user).append(", ");
        }
        if (assignedRole != null) {
            builder.append("assignedRole=").append(assignedRole).append(", ");
        }
        if (authority != null) {
            builder.append("authority=").append(authority).append(", ");
        }
        if (validTo != null) {
            builder.append("validTo=").append(validTo);
        }
        builder.append("]");
        return builder.toString();
    }
}
