/**
 * Project: inferno-security-interfaces-rest
 * File: InfernoLoginFilter.java
 * Package: pl.inferno.security.interfaces.rest.configuration
 * Location:
 * 28 lis 2017 18:07:41 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.interfaces.rest.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.inferno.security.core.model.User;
import pl.inferno.security.core.service.UserService;
import pl.inferno.security.interfaces.rest.service.InfernoTokenAuthenticationService;
import pl.inferno.security.interfaces.rest.utils.UserAuthentication;

/**
 * Class InfernoLoginFilter
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
// @Component
public class InfernoLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(InfernoLoginFilter.class);

    private final InfernoTokenAuthenticationService tokenAuthenticationService;

    private final UserService userDetailsService;

    // @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * @param string
     * @param tokenAuthenticationService
     * @param userDetailsService
     * @param authenticationManager
     */
    protected InfernoLoginFilter(String urlMapping, InfernoTokenAuthenticationService tokenAuthenticationService, UserService userDetailsService,
            AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(urlMapping));
        this.userDetailsService = userDetailsService;
        this.tokenAuthenticationService = tokenAuthenticationService;
        setAuthenticationManager(authenticationManager);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.web.authentication.
     * AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.
     * servlet. http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        User user = mapper.readValue(request.getReader(), User.class);
        LOGGER.debug("User AS POJO object: {}", user);
        String username = user.getUsername();
        String password = user.getPassword();
        String encryptedPassword = password;
        if (passwordEncoder != null) {
            encryptedPassword = passwordEncoder.encode(password);
        }

        LOGGER.debug("username: {}, password: {}, encrypted password: {}", username, password, encryptedPassword);
        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(username, encryptedPassword);

        LOGGER.debug("LOGIN TOKEN: {}", loginToken);
        try {
            return getAuthenticationManager().authenticate(loginToken);
        } catch (Exception e) {
            LOGGER.error("ERROR: {}", e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * @return the mapper
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * @param mapper
     *            the mapper to set
     */
    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.web.authentication.
     * AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.
     * servlet .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * javax.servlet.FilterChain,
     * org.springframework.security.core.Authentication)
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        // Lookup the complete User object from the database and create an
        // Authentication for it
        final User authenticatedUser = userDetailsService.getUserByUserName(authResult.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

        // Add the custom token as HTTP header to the response
        tokenAuthenticationService.addAuthentication(response, userAuthentication);

        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }

    /**
     * @return the passwordEncoder
     */
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * @param passwordEncoder
     *            the passwordEncoder to set
     */
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
