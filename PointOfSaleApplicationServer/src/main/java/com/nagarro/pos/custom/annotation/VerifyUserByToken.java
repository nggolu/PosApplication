package com.nagarro.pos.custom.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerifyUserByToken implements ConstraintValidator<Verification, String> {

	@Override
	public void initialize(Verification constraintAnnotation) {
		//System.out.println(" hell");
	}

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return true;
	}

}
