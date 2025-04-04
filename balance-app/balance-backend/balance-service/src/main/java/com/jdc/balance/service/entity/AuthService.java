package com.jdc.balance.service.entity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.core.exception.BalanceBusinessException;
import com.jdc.balance.core.model.entity.UserEntity;
import com.jdc.balance.core.payload.input.LogInInput;
import com.jdc.balance.core.payload.input.SignUpInput;
import com.jdc.balance.core.payload.input.TokenRefreshInput;
import com.jdc.balance.core.payload.output.AuthOutput;
import com.jdc.balance.repository.entity.UserRepository;
import com.jdc.balance.security.JwtTokenProvider;
import com.jdc.balance.security.JwtTokenProvider.TokenType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider tokenProvider;
	private final AuthenticationManager authenticationManager;
	
	public boolean signUp(SignUpInput input) {
		if(userRepo.findByUsername(input.username()).isPresent()) {
			throw new BalanceBusinessException("Email already registered!");
		}
		
		var user = userRepo.save(input.entity(passwordEncoder::encode));
		
		return user != null;
	}

	public AuthOutput logIn(LogInInput input) {
		var user = userRepo.findByUsername(input.username())
						.orElseThrow(() -> new BalanceBusinessException("Email not registered yet!"));
		
		var authentication = authenticationManager.authenticate(input.authentication());
		
		if(null != authentication) {
			SecurityContextHolder.getContext().setAuthentication(authentication);;
		}
		
		return getAuthOutput(user, authentication);
	}

	public AuthOutput refresh(TokenRefreshInput input) {
		var authentication = tokenProvider.parse(input.token(), TokenType.Refresh);
		if(null != authentication)
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
		var user = userRepo.findByUsername(authentication.getName())
							.orElseThrow(() -> new BalanceBusinessException("Invalid username!"));
		
		return getAuthOutput(user, authentication);
	}
	
	private AuthOutput getAuthOutput(UserEntity user, Authentication authentication) {
		return AuthOutput.builder()
					.name(user.getName())
					.username(user.getUsername())
					.admin(user.getAdmin())
					.accessToken(tokenProvider.generate(authentication, TokenType.Access))
					.refreshToken(tokenProvider.generate(authentication, TokenType.Refresh))
					.build();
	}

}
