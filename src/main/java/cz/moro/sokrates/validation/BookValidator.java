package cz.moro.sokrates.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cz.moro.sokrates.model.Book;

public class BookValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (!StringUtils.hasLength(book.getTitle())) {
            errors.rejectValue("title", "required", "Field title is required");
        }
        
        if (!StringUtils.hasLength(book.getDescription())) {
            errors.rejectValue("description", "required", "Field description is required");
        }

	}

}
