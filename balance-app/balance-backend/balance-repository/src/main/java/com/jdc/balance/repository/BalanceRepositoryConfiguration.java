package com.jdc.balance.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableJpaAuditing
public class BalanceRepositoryConfiguration {
	
	@Bean
	BalanceAuditorAware auditorAware() {
		return new BalanceAuditorAware();
	}

}
