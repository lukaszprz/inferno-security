/**
 *
 */
package pl.inferno.security.interfaces.rest.utils;

/**
 * @author lukasz-adm
 *
 */
public class CustomErrorType {

	private String errorMessage;

	public CustomErrorType(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
