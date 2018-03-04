/**
 *
 */
package pl.inferno.security.proxy.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lukasz-adm
 */

public class RoleDTO implements GrantedAuthority {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 2764450619220203330L;

    private String name;

    private String description;

    /**
     * @param name
     * @param description
     */
    public RoleDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((description == null) ? 0 : description.hashCode());
        result = (prime * result) + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof RoleDTO)) {
            return false;
        }
        RoleDTO other = (RoleDTO) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
