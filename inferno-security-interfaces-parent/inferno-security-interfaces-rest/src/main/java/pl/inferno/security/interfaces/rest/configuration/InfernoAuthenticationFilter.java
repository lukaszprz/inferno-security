/**
 * Project: inferno-security-interfaces-rest
 * File: InfernoAuthenticationFilter.java
 * Package: pl.inferno.security.interfaces.rest.configuration
 * Location:
 *
 * 28 lis 2017 19:11:56 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.interfaces.rest.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import pl.inferno.security.interfaces.rest.service.InfernoTokenAuthenticationService;

/**
 * Class InfernoAuthenticationFilter
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class InfernoAuthenticationFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfernoAuthenticationFilter.class);

    private final InfernoTokenAuthenticationService tokenAuthenticationService;

    /**
     * @param tokenAuthenticationService
     */
    public InfernoAuthenticationFilter(InfernoTokenAuthenticationService tokenAuthenticationService) {
	this.tokenAuthenticationService = tokenAuthenticationService;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {

	SecurityContextHolder.getContext()
		.setAuthentication(tokenAuthenticationService.getAuthentication((HttpServletRequest) request));

	chain.doFilter(request, response); // always continue

    }

}
