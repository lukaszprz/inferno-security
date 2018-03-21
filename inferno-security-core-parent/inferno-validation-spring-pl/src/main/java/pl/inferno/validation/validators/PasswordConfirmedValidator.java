/**
 *
 */
package pl.inferno.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pl.inferno.validation.annotation.PasswordConfirmed;
import pl.inferno.validation.form.PasswordForm;

/**
 * @author lukasz
 *
 */
@Component
public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, PasswordForm> {

	private static final Logger logger = LoggerFactory.getLogger(PasswordConfirmedValidator.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(PasswordConfirmed constraintAnnotation) {
		// nothing to initialize

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(PasswordForm value, ConstraintValidatorContext context) {
		if (value.getConfirmedPassword() == null) {
			return true;
		}
		return value.getConfirmedPassword().equals(value.getPassword());
	}

}
