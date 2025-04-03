package com.jdc.balance.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.input.SignUpInput;
import com.jdc.balance.core.payload.output.AuthOutput;
import com.jdc.balance.service.entity.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("login")
	public ResponseEntity<AuthOutput> login() {
		return null;
	}
	
	@PostMapping("signup")
	public ResponseEntity<Boolean> signup(
			@Validated @RequestBody SignUpInput input
		) {
		return new ResponseEntity<Boolean>(authService.signUp(input), HttpStatus.CREATED);
	}
	
	// refresh

}
