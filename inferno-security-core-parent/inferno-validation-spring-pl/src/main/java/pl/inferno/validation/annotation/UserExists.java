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

import pl.inferno.validation.validators.UserExistsValidator;

/**
 * @author lukasz
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserExistsValidator.class)
public @interface UserExists {

	String message() default "user does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
