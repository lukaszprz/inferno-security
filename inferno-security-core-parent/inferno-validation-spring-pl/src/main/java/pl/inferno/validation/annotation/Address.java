/**
 * File Address.java created on 15.07.2016 19:59:27 by Łukasz Przesmycki (lukasz)
 */
package pl.inferno.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.inferno.validation.validators.AddressValidator;

/**
 * <p>
 * inferno-validation-rulesets-pl
 * </p>
 * <p>
 * Address
 * </p>
 *
 * @author lukasz
 *
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Constraint(validatedBy = AddressValidator.class)
public @interface Address {

	public abstract String message() default "{package.name.Email.message}";

	public abstract Class<?>[] groups() default {};

	public abstract Class<? extends Payload>[] payload() default {};

	public abstract boolean isThisCorrespondenceAddress() default false;
}
