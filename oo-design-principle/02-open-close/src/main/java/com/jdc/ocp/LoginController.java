package com.jdc.ocp;

public class LoginController {
	
	private LoginService service;
	
	public LoginController(LoginService service) {
		this.service = service;
	}

	public void login(LoginType type) {
		System.out.println("Handling request...");
		
		switch (type) {
			case GOOGLE -> service.loginWith(new GoogleLogin());
			case FACEBOOK -> service.loginWith(new FacebookLogin());
			case GITHUB -> service.loginWith(new GithubLogin());
			default -> throw new IllegalArgumentException("No login type found");
		}
		
	}

}
