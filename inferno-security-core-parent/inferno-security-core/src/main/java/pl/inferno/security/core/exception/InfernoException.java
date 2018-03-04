/**
 * Project: inferno-security-core
 * File: InfernoException.java
 * Package: pl.inferno.security.core.exception
 * Location:
 *
 * 2 gru 2017 06:41:52 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.core.exception;

/**
 * Class InfernoException
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class InfernoException extends RuntimeException {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -4556723290270787390L;

	/**
	 *
	 */
	public InfernoException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public InfernoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InfernoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InfernoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InfernoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
