/**
 * Project: inferno-security-interfaces-web-html
 * File: InfernoProxyRestAuthenticationProvider.java
 * Package: pl.inferno.security.web.authentication.providers
 * Location:
 * 8 mar 2018 13:30:19 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.web.authentication.providers;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pl.inferno.security.proxy.dto.LoginTemplate;
import pl.inferno.security.proxy.dto.UserDTO;
import pl.inferno.security.web.service.UserService;

/**
 * Class InfernoProxyRestAuthenticationProvider
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Component
public class InfernoProxyRestAuthenticationProvider implements AuthenticationProvider {

    private final static Logger LOGGER = LoggerFactory.getLogger(InfernoProxyRestAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * (non-Javadoc)
     * @see org.springframework.security.authentication.AuthenticationProvider#
     * authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = (String) authentication.getCredentials();
        String encryptedPassword = userService.getEncryptedPassword(rawPassword);

        LoginTemplate userTemplate = new LoginTemplate();

        userTemplate.setUsername(username);
        userTemplate.setPassword(rawPassword);
        ResponseEntity<String> tokenEntity = userService.login(userTemplate);

        ResponseEntity<?> userEntity = userService.getCurrentUser(tokenEntity.getBody());
        UserDTO user = (UserDTO) userEntity.getBody();

        if (user == null) {
            throw new BadCredentialsException("Username not found");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        // LOGGER.debug("UserDTO as UserDetails: {}", user);
        // user.setNewPassword("test123");
        // userService.changePassword(user);
        // userEntity = userService.getCurrentUser(tokenEntity.getBody());
        // user = (UserDTO) userEntity.getBody();
        // LOGGER.debug("UserDTO as UserDetails: {}", user);
        // LOGGER.debug("new password: {}", user.getPassword());
        // LOGGER.debug("enc password: {}", encryptedPassword);
        // LOGGER.debug("password match: {}",
        // encryptedPassword.equals(user.getPassword()));
        // LOGGER.debug("encoder match: {}", passwordEncoder.matches(rawPassword,
        // user.getPassword()));

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        final UserDetails principal = new User(user.getUsername(), user.getPassword(), authorities);
        final Authentication auth = new UsernamePasswordAuthenticationToken(principal, user.getPassword(), authorities);
        return auth;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.authentication.AuthenticationProvider#supports(
     * java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
