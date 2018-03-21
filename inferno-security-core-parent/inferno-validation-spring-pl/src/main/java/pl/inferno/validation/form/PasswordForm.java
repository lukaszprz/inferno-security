/**
 *
 */
package pl.inferno.validation.form;

import org.hibernate.validator.constraints.NotBlank;

import pl.inferno.validation.annotation.PasswordConfirmed;

/**
 * @author lukasz
 *
 */
@PasswordConfirmed
public class PasswordForm {

	@NotBlank
	private String password;

	@NotBlank
	private String confirmedPassword;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmedPassword
	 */
	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	/**
	 * @param confirmedPassword
	 *            the confirmedPassword to set
	 */
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
}
