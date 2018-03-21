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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author lukasz
 *
 */
@Size(min = 4, max = 10)
@Pattern(regexp = ".*(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$")
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface StrongPassword {

	String message() default ""; // required

	Class<?>[] groups() default {}; // required

	Class<? extends Payload>[] payload() default {}; // required
}
