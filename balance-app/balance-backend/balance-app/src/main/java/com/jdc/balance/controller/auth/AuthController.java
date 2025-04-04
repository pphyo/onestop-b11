package com.jdc.balance.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.core.payload.input.LogInInput;
import com.jdc.balance.core.payload.input.SignUpInput;
import com.jdc.balance.core.payload.input.TokenRefreshInput;
import com.jdc.balance.core.payload.output.AuthOutput;
import com.jdc.balance.service.entity.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("login")
	public ResponseEntity<AuthOutput> login(
			@Validated @RequestBody LogInInput input, BindingResult result
			) {
		return new ResponseEntity<AuthOutput>(authService.logIn(input), HttpStatus.CREATED);
	}
	
	@PostMapping("signup")
	public ResponseEntity<Boolean> signup(
			@Validated @RequestBody SignUpInput input, BindingResult result
		) {
		return new ResponseEntity<Boolean>(authService.signUp(input), HttpStatus.OK);
	}
	
	@PostMapping("refresh")
	public ResponseEntity<AuthOutput> refresh(
			@Validated @RequestBody TokenRefreshInput input, BindingResult result
		) {
		return new ResponseEntity<AuthOutput>(authService.refresh(input), HttpStatus.OK);
	}

}
