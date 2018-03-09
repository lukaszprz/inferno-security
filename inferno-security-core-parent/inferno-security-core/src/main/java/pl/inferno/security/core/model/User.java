/**
 *
 */
package pl.inferno.security.core.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lukasz-adm
 */
@Entity
@Table(schema = "inferno_authorization_schema", name = "inferno_users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends InfernoAbstractAuditableEntity implements UserDetails, Serializable {

    @Value("${api.role.prefix}")
    private final static String ROLE_PREFIX = "ROLE_";

    /**
     *
     */
    private static final long serialVersionUID = 6040407139493365688L;

    @Id
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "inferno_users_seq", allocationSize = 1)
    @GeneratedValue(generator = "users_seq_gen")
    @Column(name = "user_id")
    @JsonIdentityReference
    private Long id;

    @JsonProperty
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonProperty
    @Column(name = "password", nullable = false)
    private String password;

    @JsonProperty
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @JsonProperty
    @Column(name = "expires", nullable = true)
    private Timestamp expires;

    @JsonProperty
    @Column(name = "is_expired", nullable = false)
    private boolean accountExpired;

    @JsonProperty
    @Column(name = "is_locked", nullable = false)
    private boolean accountLocked;

    @JsonProperty
    @Column(name = "credentials_expired", nullable = false)
    private boolean credentialsExpired;

    @JsonManagedReference(value = "person-user")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Person person;

    @JsonManagedReference(value = "roles-user")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    // @JoinTable(schema = "inferno_authorization_schema", name =
    // "inferno_roles_assigment", joinColumns = @JoinColumn(name = "user_id"),
    // inverseJoinColumns = @JoinColumn(name = "role_id"))
    // @JoinColumn(name = "role_id")
    private Set<UserRoles> roles;

    @JsonIgnoreProperties
    @Transient
    private String newPassword;

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
    @Override
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
    @Override
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
     * @return the accountEnabled
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param accountEnabled
     *            the accountEnabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the expires
     */
    public Timestamp getExpires() {
        return expires;
    }

    /**
     * @param expires
     *            the expires to set
     */
    public void setExpires(Timestamp expires) {
        this.expires = expires;
    }

    /**
     * @return the accountExpired
     */
    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * @param accountExpired
     *            the accountExpired to set
     */
    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    /**
     * @return the accountLocked
     */
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * @param accountLocked
     *            the accountLocked to set
     */
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * @return the credentialsExpired
     */
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @param credentialsExpired
     *            the credentialsExpired to set
     */
    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
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
     * @return the roles
     */
    public Set<UserRoles> getRoles() {
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired
     * ()
     */
    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked(
     * )
     */
    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#
     * isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    // /**
    // * @return the assignedAuthorities
    // */
    // public Set<UserRoles> getAssignedAuthorities() {
    // return assignedAuthorities;
    // }
    //
    // /**
    // * @param assignedAuthorities
    // * the assignedAuthorities to set
    // */
    // public void setAssignedAuthorities(Set<Role> assignedAuthorities) {
    // this.
    // this.assignedAuthorities = assignedAuthorities;
    // }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoles assignedRole : roles) {
            authorities.add(new SimpleGrantedAuthority(assignedRole.getAuthority()));
        }

        return authorities;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person
     *            the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword
     *            the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = (prime * result) + (accountExpired ? 1231 : 1237);
        result = (prime * result) + (accountLocked ? 1231 : 1237);
        result = (prime * result) + (credentialsExpired ? 1231 : 1237);
        result = (prime * result) + (enabled ? 1231 : 1237);
        result = (prime * result) + ((expires == null) ? 0 : expires.hashCode());
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());
        result = (prime * result) + ((password == null) ? 0 : password.hashCode());
        result = (prime * result) + ((person == null) ? 0 : person.hashCode());
        // result = (prime * result) + ((roles == null) ? 0 : roles.hashCode());
        result = (prime * result) + ((username == null) ? 0 : username.hashCode());
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
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        if (accountExpired != other.accountExpired) {
            return false;
        }
        if (accountLocked != other.accountLocked) {
            return false;
        }
        if (credentialsExpired != other.credentialsExpired) {
            return false;
        }
        if (enabled != other.enabled) {
            return false;
        }
        if (expires == null) {
            if (other.expires != null) {
                return false;
            }
        } else if (!expires.equals(other.expires)) {
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
        if (person == null) {
            if (other.person != null) {
                return false;
            }
        } else if (!person.equals(other.person)) {
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
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [");
        if (id != null) {
            builder.append("id=").append(id).append(", ");
        }
        if (username != null) {
            builder.append("username=").append(username).append(", ");
        }
        if (password != null) {
            builder.append("password=").append(password).append(", ");
        }
        builder.append("enabled=").append(enabled).append(", ");
        if (expires != null) {
            builder.append("expires=").append(expires).append(", ");
        }
        builder.append("accountExpired=").append(accountExpired).append(", accountLocked=").append(accountLocked).append(", credentialsExpired=").append(credentialsExpired)
                .append(", ");
        if (person != null) {
            builder.append("person=").append(person.getFirstName() + " " + person.getLastName()).append(", ");
        }
        if (roles != null) {
            builder.append("roles=").append(roles);
        }
        builder.append("]");
        return builder.toString();
    }

}
