/**
 *
 */
package pl.inferno.security.core.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author lukasz-adm
 */
@Entity
@Table(schema = "inferno_authorization_schema", name = "inferno_roles")
public class Role extends InfernoAbstractAuditableEntity implements GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = 7041003323536767788L;

    @Id
    @SequenceGenerator(name = "roles_seq_gen", sequenceName = "inferno_roles_seq", allocationSize = 1)
    @GeneratedValue(generator = "roles_seq_gen")
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "valid_to")
    private Timestamp validTo;

    @JsonManagedReference(value = "role-assign")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignedRole", cascade = CascadeType.ALL)
    private Set<UserRoles> usersAssignment;

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

    // /**
    // * @return the created
    // */
    // public Timestamp getCreated() {
    // return created;
    // }
    //
    // /**
    // * @param created
    // * the created to set
    // */
    // public void setCreated(Timestamp created) {
    // this.created = created;
    // }

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
     * @return the usersAssignment
     */
    public Set<UserRoles> getUsersAssignment() {
        return usersAssignment;
    }

    /**
     * @param usersAssignment
     *            the usersAssignment to set
     */
    public void setUsersAssignment(Set<UserRoles> usersAssignment) {
        this.usersAssignment = usersAssignment;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        // result = (prime * result) + ((created == null) ? 0 : created.hashCode());
        result = (prime * result) + ((description == null) ? 0 : description.hashCode());
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        Role other = (Role) obj;
        // if (created == null) {
        // if (other.created != null) {
        // return false;
        // }
        // } else if (!created.equals(other.created)) {
        // return false;
        // }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    public String getAuthority() {
        return name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Role [");
        if (id != null) {
            builder.append("id=").append(id).append(", ");
        }
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
