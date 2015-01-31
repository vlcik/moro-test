package cz.moro.sokrates.validation;

import org.springframework.util.StringUtils;
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
        String name = user.getName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", "required", "Field name is required");
        }

	}

}
