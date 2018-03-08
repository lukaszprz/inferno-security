/**
 * Project: inferno-security-rest-proxy
 * File: UserRolesDTO.java
 * Package: pl.inferno.security.proxy.dto
 * Location:
 * 7 mar 2018 21:17:33 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.proxy.dto;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;

/**
 * Class UserRolesDTO
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class UserRolesDTO implements GrantedAuthority {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 195972871452831075L;

    private UserDTO user;

    private RoleDTO role;

    private String authority;

    private DateTime validTo;

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * @return the role
     */
    public RoleDTO getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(RoleDTO role) {
        this.role = role;
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
     * @param authority
     *            the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((authority == null) ? 0 : authority.hashCode());
        result = (prime * result) + ((role == null) ? 0 : role.hashCode());
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
        if (!(obj instanceof UserRolesDTO)) {
            return false;
        }
        UserRolesDTO other = (UserRolesDTO) obj;
        if (authority == null) {
            if (other.authority != null) {
                return false;
            }
        } else if (!authority.equals(other.authority)) {
            return false;
        }
        if (role == null) {
            if (other.role != null) {
                return false;
            }
        } else if (!role.equals(other.role)) {
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
        builder.append("UserRolesDTO [");
        if (user != null) {
            builder.append("user=").append(user).append(", ");
        }
        if (role != null) {
            builder.append("role=").append(role).append(", ");
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
