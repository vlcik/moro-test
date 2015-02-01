package cz.moro.sokrates.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cz.moro.sokrates.model.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		if (!user.getPassword().equals(user.getVerifiedPassword())) {
			errors.rejectValue("verifiedPassword", "required",
					"Passwords must match");
		}

	}

}
