/**
 * Project: inferno-security-interfaces-rest
 * File: InfernoTokenAuthenticationService.java
 * Package: pl.inferno.security.interfaces.rest.service
 * Location:
 * 28 lis 2017 18:15:30 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.interfaces.rest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import pl.inferno.security.interfaces.rest.utils.UserAuthentication;

/**
 * Class InfernoTokenAuthenticationService
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public interface InfernoTokenAuthenticationService {

    public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    public Authentication getAuthentication(HttpServletRequest request);

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication);

}
