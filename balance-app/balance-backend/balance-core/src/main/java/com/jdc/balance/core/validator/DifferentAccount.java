package com.jdc.balance.core.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DifferentAccountValidator.class)
public @interface DifferentAccount {
	
	String message() default "From account and To account must be different.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
