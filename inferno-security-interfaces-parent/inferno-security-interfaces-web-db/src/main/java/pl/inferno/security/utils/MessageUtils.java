/**
 * Project: inferno-security-interfaces-web-db
 * File: MessageUtils.java
 * Package: pl.inferno.security.utils
 * Location:
 * 16 mar 2018 00:07:11 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.utils;

import pl.inferno.security.errors.handler.Severity;

/**
 * Class MessageUtils
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class MessageUtils {

	public static final String EMPTY = "EMPTY";

	public static final String NO_CHANGES = "NOT CHANGED";

	public static final String SEVERITY_MAP_KEY = "SEVERITY";

	public static final String VALUE_MAP_KEY = "VALUE";

	public static final String SEVERITY_MESSAGE_CODE = "error.severity.";

	public static String renderSeverityMessage(Severity severity) {

		return SEVERITY_MESSAGE_CODE + severity.getShortCode();
	}

}
