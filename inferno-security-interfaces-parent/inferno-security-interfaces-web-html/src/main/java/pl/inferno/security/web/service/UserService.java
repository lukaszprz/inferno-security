/**
 * Project: inferno-security-interfaces-web-html
 * File: UserService.java
 * Package: pl.inferno.security.web.service
 * Location:
 * 8 mar 2018 13:13:33 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.inferno.security.proxy.dto.LoginTemplate;
import pl.inferno.security.proxy.dto.UserDTO;
import pl.inferno.security.proxy.service.UserProxyService;

/**
 * Class UserService
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserProxyService userProxyService;

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userProxyService.getUserByUsername(username);
        return user;
    }

    public ResponseEntity<String> login(LoginTemplate userTemplate) {
        ResponseEntity<String> token = userProxyService.login(userTemplate);
        return token;
    }

    public ResponseEntity<?> getCurrentUser(String authorizationToken) {
        return userProxyService.getCurrentUser(authorizationToken);
    }

    public ResponseEntity<String> changePassword(UserDTO user) {
        return userProxyService.changePassword(user);
    }

    public String getEncryptedPassword(String rawPassword) {
        HttpEntity<String> encryptedPasswordResponse = userProxyService.encryptPassword(rawPassword);
        return encryptedPasswordResponse.getBody();
    }

}
