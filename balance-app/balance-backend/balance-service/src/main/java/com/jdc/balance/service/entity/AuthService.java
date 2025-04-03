package com.jdc.balance.service.entity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.exception.BalanceBusinessException;
import com.jdc.balance.core.payload.input.SignUpInput;
import com.jdc.balance.repository.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	public boolean signUp(SignUpInput input) {
		
		if(userRepo.findOneByUsername(input.username()).isPresent()) {
			throw new BalanceBusinessException("Username already registered!");
		}
		
		var user = userRepo.save(input.entity(passwordEncoder::encode));
		
		return user != null;
	}

}
