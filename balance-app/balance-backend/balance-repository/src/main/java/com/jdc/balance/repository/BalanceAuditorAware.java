package com.jdc.balance.repository;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class BalanceAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Test user");
	}

}
