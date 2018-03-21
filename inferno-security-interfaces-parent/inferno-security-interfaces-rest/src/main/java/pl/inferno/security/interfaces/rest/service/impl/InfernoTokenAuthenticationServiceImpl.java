/**
 * Project: inferno-security-interfaces-rest
 * File: InfernoTokenAuthenticationServiceImpl.java
 * Package: pl.inferno.security.interfaces.rest.service.impl
 * Location:
 * 28 lis 2017 18:22:40 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.interfaces.rest.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import pl.inferno.security.core.model.User;
import pl.inferno.security.interfaces.rest.handler.InfernoTokenHandler;
import pl.inferno.security.interfaces.rest.service.InfernoTokenAuthenticationService;
import pl.inferno.security.interfaces.rest.utils.UserAuthentication;

/**
 * Class InfernoTokenAuthenticationServiceImpl
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Service
public class InfernoTokenAuthenticationServiceImpl implements InfernoTokenAuthenticationService {

    private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

    private final InfernoTokenHandler tokenHandler;

    /**
     *
     */
    @Autowired
    public InfernoTokenAuthenticationServiceImpl(@Value("${inferno.token.secret}") String secret) {
	tokenHandler = new InfernoTokenHandler(DatatypeConverter.parseBase64Binary(secret));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.interfaces.rest.service.InfernoTokenAuthenticationService
     * #getAuthentication(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
	final String token = request.getHeader(AUTH_HEADER_NAME);
	if (token != null) {
	    final User user = tokenHandler.parseUserFromToken(token);
	    if (user != null) {
		return new UserAuthentication(user);
	    }
	}
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.inferno.security.interfaces.rest.service.InfernoTokenAuthenticationService
     * #addAuthentication(javax.servlet.http.HttpServletResponse,
     * pl.inferno.security.interfaces.rest.utils.UserAuthentication)
     */
    @Override
    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
	final User user = authentication.getDetails();
	user.setExpires(LocalDateTime.now().plusDays(Long.valueOf(TEN_DAYS).intValue()));

	response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }

}
