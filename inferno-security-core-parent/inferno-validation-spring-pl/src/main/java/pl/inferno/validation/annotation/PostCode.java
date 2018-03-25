/**
 * 
 */
package pl.inferno.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.inferno.validation.validators.PostCodeValidator;

/**
 * Class PostCode
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
@Documented
@Constraint(validatedBy = PostCodeValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface PostCode {

    public abstract String message() default "{package.name.PostCode.message}";

    public abstract Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
