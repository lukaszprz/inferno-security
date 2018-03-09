/**
 * Project: inferno-security-interfaces-web-html
 * File: InfernoCustomUserDetails.java
 * Package: pl.inferno.security.web.service
 * Location: 
 *
 * 9 mar 2018 04:16:14 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.web.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Class InfernoCustomUserDetails
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class InfernoCustomUserDetails extends User {

    /**
     * @param username
     * @param password
     * @param authorities
     */
    public InfernoCustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */
    public InfernoCustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        // TODO Auto-generated constructor stub
    }

}
