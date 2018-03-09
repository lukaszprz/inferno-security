/**
 * Project: inferno-security-interfaces-web-html
 * File: User.java
 * Package: pl.inferno.security.web.domain
 * Location:
 * 9 mar 2018 04:22:43 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.web.domain;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Class User
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@JsonRootName(value = "user")
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -8648880344716912129L;

    private String username;

    private String password;

    private boolean enabled;

    private Timestamp expires;

    private boolean accountExpired;

    private boolean accountLocked;

    private boolean credentialsExpired;

    private Person person;

    private Set<UserRoles> roles;

    @JsonIgnoreProperties
    private String newPassword;

    /**
     *
     */
    public UserDetails() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param username
     * @param password
     * @param enabled
     * @param expires
     * @param accountExpired
     * @param accountLocked
     * @param credentialsExpired
     * @param person
     * @param roles
     * @param newPassword
     * @param authorities
     */
    public UserDetails(String username, String password, boolean enabled, Timestamp expires, boolean accountExpired, boolean accountLocked, boolean credentialsExpired,
            Person person, Set<UserRoles> roles, String newPassword, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.expires = expires;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.person = person;
        this.roles = roles;
        this.newPassword = newPassword;
        this.authorities = authorities;
    }

    private Collection<? extends GrantedAuthority> authorities;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
     */
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
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

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @param authorities
     *            the authorities to set
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + (accountExpired ? 1231 : 1237);
        result = (prime * result) + (accountLocked ? 1231 : 1237);
        result = (prime * result) + ((authorities == null) ? 0 : authorities.hashCode());
        result = (prime * result) + (credentialsExpired ? 1231 : 1237);
        result = (prime * result) + (enabled ? 1231 : 1237);
        result = (prime * result) + ((expires == null) ? 0 : expires.hashCode());
        result = (prime * result) + ((newPassword == null) ? 0 : newPassword.hashCode());
        result = (prime * result) + ((password == null) ? 0 : password.hashCode());
        result = (prime * result) + ((person == null) ? 0 : person.hashCode());
        result = (prime * result) + ((roles == null) ? 0 : roles.hashCode());
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
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserDetails)) {
            return false;
        }
        UserDetails other = (UserDetails) obj;
        if (accountExpired != other.accountExpired) {
            return false;
        }
        if (accountLocked != other.accountLocked) {
            return false;
        }
        if (authorities == null) {
            if (other.authorities != null) {
                return false;
            }
        } else if (!authorities.equals(other.authorities)) {
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
        if (newPassword == null) {
            if (other.newPassword != null) {
                return false;
            }
        } else if (!newPassword.equals(other.newPassword)) {
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
        builder.append("UserDetails [");
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
            builder.append("person=").append(person).append(", ");
        }
        if (roles != null) {
            builder.append("roles=").append(roles).append(", ");
        }
        if (newPassword != null) {
            builder.append("newPassword=").append(newPassword).append(", ");
        }
        if (authorities != null) {
            builder.append("authorities=").append(authorities);
        }
        builder.append("]");
        return builder.toString();
    }

}
