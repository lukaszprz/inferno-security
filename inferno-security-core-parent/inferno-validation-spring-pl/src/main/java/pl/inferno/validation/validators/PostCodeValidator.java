/**
 * 
 */
package pl.inferno.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import pl.inferno.validation.annotation.PostCode;

/**
 * Class PostCodeValidator
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
// @Component
public class PostCodeValidator implements ConstraintValidator<PostCode, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostCodeValidator.class);

    @Value("{validation.pattern.postcode}")
    private final String PATTERN = "\\d{2}-\\d{3}";

    private final Pattern POST_CODE_PATTERN = Pattern.compile(PATTERN);

    /*
     * (non-Javadoc)
     * 
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.
     * Annotation)
     */
    @Override
    public void initialize(PostCode constraintAnnotation) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
     * javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	boolean valid = true;
	if ((value == null) || value.isEmpty()) {
	    return valid;
	}
	valid = POST_CODE_PATTERN.matcher(value).matches();

	return valid;
    }
}
