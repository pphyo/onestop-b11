package com.jdc.balance.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.balance.repository.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BalanceUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username)
					.map(userEntity -> User.builder()
										.username(username)
										.password(userEntity.getPassword())
										.authorities(userEntity.getAdmin() ? "ROLE_ADMIN" : "ROLE_USER")
										.build()).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
