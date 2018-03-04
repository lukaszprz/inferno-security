/**
 * Project: inferno-security-interfaces-rest
 * File: AuthorizationController.java
 * Package: pl.inferno.security.interfaces.rest.controller
 * Location:
 * 4 mar 2018 06:21:30 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.interfaces.rest.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.inferno.security.interfaces.rest.service.InfernoTokenAuthenticationService;

/**
 * Class AuthorizationController
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@RestController
@RequestMapping(headers = { InfernoTokenAuthenticationService.AUTH_HEADER_NAME }, name = "Authorization Controller", path = "/auth", value = "/auth")
public class AuthorizationController {

    @RequestMapping(headers = { InfernoTokenAuthenticationService.AUTH_HEADER_NAME }, method = RequestMethod.GET, name = "Current Principal", path = "/current/principal/name")
    public String currentUserNameByPrincipal(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(path = "/current/authentication/name", method = RequestMethod.GET)
    public String currentUserNameByAuthentication(Authentication authentication) {
        return authentication.getName();
    }

    @RequestMapping(path = "/current/http-request/name", method = RequestMethod.GET)
    public String currentUserNameByHTTPRequest(HttpServletRequest request) {
        return request.getUserPrincipal().getName();

    }

    @RequestMapping(headers = { InfernoTokenAuthenticationService.AUTH_HEADER_NAME }, method = RequestMethod.GET, name = "Current Principal", path = "/current/principal")
    public ResponseEntity<Principal> currentUserByPrincipal(Principal principal) {
        HttpHeaders headers = new HttpHeaders();
        HttpServletRequest request = (HttpServletRequest) new AuthorizationController();
        String token = request.getHeader(InfernoTokenAuthenticationService.AUTH_HEADER_NAME);
        headers.add(InfernoTokenAuthenticationService.AUTH_HEADER_NAME, token);
        return new ResponseEntity<Principal>(principal, headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/current/authentication", method = RequestMethod.GET)
    public ResponseEntity<Authentication> currentUserByAuthentication(Authentication authentication) {
        return new ResponseEntity<>(authentication, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/current/http-request", method = RequestMethod.GET)
    public ResponseEntity<Principal> currentUserByHTTPRequest(HttpServletRequest request) {
        return new ResponseEntity<Principal>(HttpStatus.ACCEPTED);

    }
}
