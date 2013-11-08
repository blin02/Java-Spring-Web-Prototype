package com.mycompany.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mycompany.web.model.UserModel;

/***
 * This class contains functions to validate the input for user form. 
 *
 */
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return UserModel.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "name.required", "Username is required");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required", "First name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required", "Last name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "Email address is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "phoneNumber.required", "Phone number is required");
		
		//TO-DO: Validate email address?
		
	}
	
	

}
