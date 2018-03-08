/**
 *
 */
package pl.inferno.security.core.model;

import java.sql.Timestamp;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author lukasz-adm
 */
@Entity
@Table(schema = "inferno_authorization_schema", name = "inferno_roles_assigment")
@AssociationOverrides({ @AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "user_user_id")),
        @AssociationOverride(name = "role", joinColumns = @JoinColumn(name = "assigned_role_id")) })
public class UserRoles extends InfernoAbstractAuditableEntity implements GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = -7965012653644807073L;

    @Id
    @SequenceGenerator(name = "user_roles_assigment_seq_gen", sequenceName = "inferno_user_roles_assigment_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_roles_assigment_seq_gen")
    @Column(name = "user_role_id")
    private Long id;

    @JsonBackReference(value = "roles-user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;

    @JsonBackReference(value = "role-assign")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_role_id", nullable = false)
    private Role assignedRole;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "valid_to", nullable = true)
    private Timestamp validTo;

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
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
     * @param authority
     *            the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        // result = (prime * result) + ((assignedRole == null) ? 0 :
        // assignedRole.hashCode());
        result = (prime * result) + ((authority == null) ? 0 : authority.hashCode());
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());
        // result = (prime * result) + ((user == null) ? 0 : user.hashCode());
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
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
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
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
        if (id != null) {
            builder.append("id=").append(id).append(", ");
        }
        // if (user != null) {
        // builder.append("user=").append(user.getUsername()).append(", ");
        // }
        // if (assignedRole != null) {
        // builder.append("assignedRole=").append(assignedRole).append(", ");
        // }
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
