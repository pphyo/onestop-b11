package com.jdc.balance.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.jdc.balance.core.exception.BalanceValidationException;

@Component
@Aspect
public class BalanceValidationAspect {
	
	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public void apiMethods() {}
	
	@Before(value = "apiMethods() and args(.., result)", argNames = "result")
	public void validate(BindingResult result) {
		if(result.hasErrors()) {
			throw new BalanceValidationException(
					result.getFieldErrors()
							.stream()
							.map(a -> a.getDefaultMessage())
							.toList()
			);
		}
	}

}










