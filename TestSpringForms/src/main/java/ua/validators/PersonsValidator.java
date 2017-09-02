package ua.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.Person;

public class PersonsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);

	}

	@Override
	public void validate(Object obj, Errors errors) {
		Person person = (Person) obj;
		if (person.getName().length()<2) {
			errors.rejectValue("name", "person.name", "there is a problem");
		}
	}

}
