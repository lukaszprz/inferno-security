/**
 * Project: inferno-security-interfaces-web-db
 * File: SecurityInterceptor.java
 * Package: pl.inferno.security.interceptor
 * Location:
 * 9 mar 2018 11:48:20 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Class SecurityInterceptor
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			ServletRequest req = request;
			ServletResponse resp = response;
			FilterInvocation filterInvocation = new FilterInvocation(req, resp, new FilterChain() {

				@Override
				public void doFilter(ServletRequest request, ServletResponse response)
						throws IOException, ServletException {
					throw new UnsupportedOperationException();

				}
			});

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				WebSecurityExpressionRoot sec = new WebSecurityExpressionRoot(authentication, filterInvocation);
				sec.setTrustResolver(new AuthenticationTrustResolverImpl());
				modelAndView.getModel().put("sec", sec);
			}
		}
	}

}
