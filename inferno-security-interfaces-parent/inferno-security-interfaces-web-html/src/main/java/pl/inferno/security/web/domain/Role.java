/**
 * Project: inferno-security-interfaces-web-html
 * File: Role.java
 * Package: pl.inferno.security.web.domain
 * Location:
 * 9 mar 2018 05:25:47 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.web.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Class Role
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@JsonRootName(value = "role")
public class Role {

    private String name;

    private String description;

    private Timestamp validTo;

    /**
     * @param name
     * @param description
     */
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param name
     * @param description
     * @param validTo
     */
    public Role(String name, String description, Timestamp validTo) {
        this.name = name;
        this.description = description;
        this.validTo = validTo;
    }

    /**
     *
     */
    public Role() {
        // TODO Auto-generated constructor stub
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
        result = (prime * result) + ((description == null) ? 0 : description.hashCode());
        result = (prime * result) + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof Role)) {
            return false;
        }
        Role other = (Role) obj;
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
        builder.append("Role [");
        if (name != null) {
            builder.append("name=").append(name).append(", ");
        }
        if (description != null) {
            builder.append("description=").append(description).append(", ");
        }
        if (validTo != null) {
            builder.append("validTo=").append(validTo);
        }
        builder.append("]");
        return builder.toString();
    }

}
