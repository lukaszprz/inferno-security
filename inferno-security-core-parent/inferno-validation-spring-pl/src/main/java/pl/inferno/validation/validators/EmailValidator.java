/**
 *
 */
package pl.inferno.validation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.inferno.validation.annotation.Email;

/**
 * @author lukasz
 *
 */
@Component
public class EmailValidator implements ConstraintValidator<Email, String> {

    private static final Logger logger = LoggerFactory.getLogger(EmailValidator.class);

    @Value("{validation.pattern.email}")
    private final String PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private final Pattern EMAIL_PATTERN = Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE);

    /*
     * (non-Javadoc)
     *
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.
     * Annotation)
     */
    @Override
    public void initialize(Email constraintAnnotation) {
	// nothing to initialize
	logger.info("===============================================");
	logger.info("PATTERN: {}", PATTERN);
	logger.info("===============================================");
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
     * javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	if ((value != null) && !value.isEmpty()) {
	    Matcher matcher = EMAIL_PATTERN.matcher(value);
	    return matcher.find();
	}
	return true;
    }

}
