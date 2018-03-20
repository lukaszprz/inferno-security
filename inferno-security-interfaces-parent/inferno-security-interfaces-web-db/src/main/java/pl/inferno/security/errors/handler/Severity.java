/**
 * Project: inferno-security-interfaces-web-db
 * File: Severity.java
 * Package: pl.inferno.security.errors.handler
 * Location:
 * 14 mar 2018 13:17:23 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.errors.handler;

/**
 * Class Severity
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public enum Severity {
	// @formatter:off

	ERROR(5, "error"), WARNING(4, "warn"), INFO(3, "info"), DEBUG(2, "debug"), TRACE(1, "trace"), ALL(0, "all");

	// @formatter:on

	/**
	 * @param levelCode
	 * @param shortCode
	 */
	Severity(int levelCode, String shortCode) {
		this.levelCode = levelCode;
		this.shortCode = shortCode;
	}

	private final int levelCode;

	private final String shortCode;

	/**
	 * @return the levelCode
	 */
	public int getLevelCode() {
		return levelCode;
	}

	/**
	 * @return the shortCode
	 */
	public String getShortCode() {
		return shortCode;
	}
}
