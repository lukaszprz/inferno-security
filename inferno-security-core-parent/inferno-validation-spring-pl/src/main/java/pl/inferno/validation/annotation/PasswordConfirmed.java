/**
 *
 */
package pl.inferno.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.inferno.validation.validators.PasswordConfirmedValidator;

/**
 * @author lukasz
 *
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmedValidator.class)
@Documented
public @interface PasswordConfirmed {

	String message() default "passwords do not match"; // default error message

	Class<?>[] groups() default {}; // required

	Class<? extends Payload>[] payload() default {}; // required
}
