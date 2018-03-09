/**
 * Project: inferno-security-rest-proxy
 * File: InfernoAccessDeniedExceptionHandler.java
 * Package: pl.inferno.security.proxy.errors.handlers
 * Location:
 * 8 mar 2018 13:58:37 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.proxy.errors.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Class InfernoAccessDeniedExceptionHandler
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
@Component
public class InfernoAccessDeniedExceptionHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfernoAccessDeniedExceptionHandler.class);

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.security.web.access.AccessDeniedHandler#handle(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.access.AccessDeniedException)
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            LOGGER.warn("User: {} attempted to access the protected URL: {}", authentication.getName(), request.getRequestURI());
        }

        response.sendRedirect(request.getContextPath() + "/403");

    }

}
