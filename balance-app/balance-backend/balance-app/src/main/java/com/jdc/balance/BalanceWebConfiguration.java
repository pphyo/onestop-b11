package com.jdc.balance;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BalanceWebConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedMethods("POST", "PUT", "GET", "DELETE", "PATCH")
				.allowedOrigins("https://localhost:5137")
				.exposedHeaders("*");
	}

}
