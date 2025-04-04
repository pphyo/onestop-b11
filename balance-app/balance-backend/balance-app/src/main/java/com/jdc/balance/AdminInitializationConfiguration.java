package com.jdc.balance;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.balance.core.model.entity.UserEntity;
import com.jdc.balance.repository.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdminInitializationConfiguration {
	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		if(userRepo.count() == 0L) {
			var admin = new UserEntity();
			admin.setName("Admin User");
			admin.setUsername("admin@gmail.com");
			admin.setAdmin(true);
			admin.setPassword(passwordEncoder.encode("admin@123"));
			userRepo.save(admin);
		}
	}

}
