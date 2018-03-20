/**
 * Project: inferno-security-interfaces-web-db
 * File: HandlerExceptionResolver.java
 * Package: pl.inferno.security.errors.resolver
 * Location:
 * 14 mar 2018 08:23:09 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.errors.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * Class HandlerExceptionResolver
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public interface HandlerExceptionResolver {

	ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex);

}
