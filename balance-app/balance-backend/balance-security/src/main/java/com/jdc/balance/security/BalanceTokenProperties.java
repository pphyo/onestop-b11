package com.jdc.balance.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
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
