/**
 * Project: inferno-security-interfaces-rest
 * File: UserAuthentication.java
 * Package: pl.inferno.security.interfaces.rest.utils
 * Location:
 *
 * 28 lis 2017 11:46:25 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.interfaces.rest.utils;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import pl.inferno.security.core.model.User;

/**
 * Class UserAuthentication
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class UserAuthentication implements Authentication {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -3759342225426317209L;

	private final User user;

	private boolean authenticated = true;

	/**
	 * @param user
	 */
	public UserAuthentication(User user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		return user.getUsername();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.Authentication#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.Authentication#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.Authentication#getDetails()
	 */
	@Override
	public User getDetails() {
		return user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.Authentication#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return user.getUsername();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.Authentication#isAuthenticated()
	 */
	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.core.Authentication#setAuthenticated(boolean)
	 */
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;

	}

}
