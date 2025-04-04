package com.jdc.balance.repository;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

public class BalanceAuditorAware implements AuditorAware<String> {
	
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(SecurityContextHolder.getContext())
					.map(ctx -> ctx.getAuthentication())
					.map(auth -> auth.getName())
					.filter(name -> !StringUtils.hasLength(name))
					.or(() -> Optional.of("Register User"));
	}

}
