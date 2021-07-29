package validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { CompanyEmailValidator.class })
public @interface CompanyEmail {
	String message() default "Company email does not contain {value}.";

	Class<?>[] groups() default {}; // required

	Class<? extends Payload>[] payload() default {}; // required

	String value();
}
