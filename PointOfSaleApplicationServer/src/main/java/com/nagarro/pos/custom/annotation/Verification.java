package com.nagarro.pos.custom.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {VerifyUserByToken.class})
public @interface Verification {

	String token();
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
	
	
}
