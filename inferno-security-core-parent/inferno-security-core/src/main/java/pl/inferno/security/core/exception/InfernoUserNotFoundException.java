/**
 * Project: inferno-security-core
 * File: InfernoUserNotFoundException.java
 * Package: pl.inferno.security.core.exception
 * Location:
 *
 * 2 gru 2017 07:15:18 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2017
 */
package pl.inferno.security.core.exception;

/**
 * Class InfernoUserNotFoundException
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class InfernoUserNotFoundException extends InfernoException {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -7650180967506370492L;

	/**
	 *
	 */
	public InfernoUserNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public InfernoUserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InfernoUserNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InfernoUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InfernoUserNotFoundException(String message, Throwable cause, boolean enableSuppression,
	        boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
