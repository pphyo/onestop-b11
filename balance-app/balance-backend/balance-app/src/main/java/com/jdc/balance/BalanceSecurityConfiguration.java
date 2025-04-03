package com.jdc.balance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdc.balance.security.BalanceSecurityExceptionResolver;
import com.jdc.balance.security.JwtTokenAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class BalanceSecurityConfiguration {
	
	private final JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;
	private final BalanceSecurityExceptionResolver securityExceptionResolver;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// disable csrf config
		http.csrf(csrf -> csrf.disable());
		
		// default cors config
		http.cors(cors -> {});
		
		http.sessionManagement(
				session -> 
					session.sessionCreationPolicy((SessionCreationPolicy.STATELESS)));
		
		http.authorizeHttpRequests(
					request -> {
						request.requestMatchers("/balance/api/v1/auth/signup").permitAll();
						request.requestMatchers("/balance/api/v1/auth/login").permitAll();
						request.requestMatchers("/balance/api/v1/admin/**").hasAuthority("Admin");
						request.requestMatchers("/balance/api/v1/user/**").hasAuthority("User");
					});
		
		http.addFilterAfter(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.exceptionHandling(exception -> {
			exception.accessDeniedHandler(securityExceptionResolver);
			exception.authenticationEntryPoint(securityExceptionResolver);
		});
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
