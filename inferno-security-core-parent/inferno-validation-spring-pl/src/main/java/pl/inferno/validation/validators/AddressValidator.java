/**
 * File AddressValidator.java created on 15.07.2016 20:02:38 by Łukasz Przesmycki (lukasz)
 */
package pl.inferno.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pl.inferno.validation.annotation.Address;

/**
 * <p>
 * inferno-validation-rulesets-pl
 * </p>
 * <p>
 * AddressValidator
 * </p>
 *
 * @author lukasz
 *
 */
@Component
public class AddressValidator implements ConstraintValidator<Address, Object> {

	private static final boolean disableValidation = true;

	private static boolean isThisMainAddress = true;

	/**
	 * Field logger of type AddressValidator
	 */
	private static final Logger logger = LoggerFactory.getLogger(AddressValidator.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Address constraintAnnotation) {
		isThisMainAddress = !constraintAnnotation.isThisCorrespondenceAddress();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (isThisMainAddress) {
			logger.debug("This is main address.");
			return true;
		}
		logger.debug("This is correspondence address.");
		return disableValidation;
	}

}
