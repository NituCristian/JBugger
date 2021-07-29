package validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyEmailValidator implements ConstraintValidator<CompanyEmail, String> {

	protected String companyEmailSuffix;

	@Override
	public void initialize(CompanyEmail email) {
		companyEmailSuffix = email.value();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email != null && email.endsWith(companyEmailSuffix)) {
			return true;
		}

		return false;
	}

}
