package com.jdc.balance.core.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@Configuration
@PropertySource("/application.token.properties")
@ConfigurationProperties(prefix = "com.jdc.balance.token")
public class BalanceTokenProperties {
	
	private Life life = new Life();
	private String issuer;
	
	@Data
	public static class Life {
		private int access;
		private int refresh;
	}

}
