/**
 * Project: inferno-security-interfaces-web-db
 * File: MyCustomMessageCodesResolver.java
 * Package: pl.inferno.security.errors.resolver
 * Location:
 * 14 mar 2018 11:37:12 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.errors.resolver;

import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

/**
 * Class MyCustomMessageCodesResolver
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class MyCustomMessageCodesResolver implements MessageCodesResolver {

	private DefaultMessageCodesResolver defaultMessageCodesResolver = new DefaultMessageCodesResolver();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.validation.MessageCodesResolver#resolveMessageCodes(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public String[] resolveMessageCodes(String errorCode, String objectName) {
		if (errorCode.contains("")) {

		}
		return defaultMessageCodesResolver.resolveMessageCodes(errorCode, objectName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.validation.MessageCodesResolver#resolveMessageCodes(java.
	 * lang.String, java.lang.String, java.lang.String, java.lang.Class)
	 */
	@Override
	public String[] resolveMessageCodes(String errorCode, String objectName, String field, Class<?> fieldType) {

		return defaultMessageCodesResolver.resolveMessageCodes(errorCode, objectName, field, fieldType);
	}

}
