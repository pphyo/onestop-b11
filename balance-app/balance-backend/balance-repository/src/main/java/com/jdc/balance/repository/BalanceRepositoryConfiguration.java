package com.jdc.balance.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BalanceRepositoryConfiguration {

}
