/**
 *
 */
package pl.inferno.security.proxy.dto;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author lukasz-adm
 */
@JsonIgnoreType
@JsonDeserialize(as = UserDTO.class)
public class UserDTO implements UserDetails, Authentication, Principal {

    private String username;

    private String password;

    private boolean active;

    private DateTime validTo;

    private DateTime created;

    private Set<RoleDTO> roles;

    private String token;

    /**
     *
     */
    public UserDTO() {
    }

    /**
     * @param username
     * @param password
     */
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @param username
     * @param password
     * @param roles
     */
    public UserDTO(String username, String password, Set<RoleDTO> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * @param username
     * @param password
     * @param active
     * @param roles
     */
    public UserDTO(String username, String password, boolean active, Set<RoleDTO> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    /**
     * @param username
     * @param password
     * @param active
     * @param validTo
     * @param created
     * @param roles
     */
    public UserDTO(String username, String password, boolean active, DateTime validTo, DateTime created, Set<RoleDTO> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.validTo = validTo;
        this.created = created;
        this.roles = roles;
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
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
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
     * @return the created
     */
    public DateTime getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    public void setCreated(DateTime created) {
        this.created = created;
    }

    /**
     * @return the roles
     */
    public Set<RoleDTO> getRoles() {
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + (active ? 1231 : 1237);
        result = (prime * result) + ((created == null) ? 0 : created.hashCode());
        result = (prime * result) + ((password == null) ? 0 : password.hashCode());
        result = (prime * result) + ((roles == null) ? 0 : roles.hashCode());
        result = (prime * result) + ((username == null) ? 0 : username.hashCode());
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
        UserDTO other = (UserDTO) obj;
        if (active != other.active) {
            return false;
        }
        if (created == null) {
            if (other.created != null) {
                return false;
            }
        } else if (!created.equals(other.created)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
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
     * @see java.security.Principal#getName()
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.Authentication#getCredentials()
     */
    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.Authentication#getDetails()
     */
    @Override
    public Object getDetails() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.Authentication#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.Authentication#isAuthenticated()
     */
    @Override
    public boolean isAuthenticated() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.core.Authentication#setAuthenticated(boolean)
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(roles.size());
        for (RoleDTO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired
     * ()
     */
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked(
     * )
     */
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#
     * isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
