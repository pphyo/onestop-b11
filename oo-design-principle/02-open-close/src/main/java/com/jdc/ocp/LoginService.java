package com.jdc.ocp;

public class LoginService {
	
	public void loginWith(CanLogin login) {
		System.out.println("Validating authority...");
		login.login();
	}

}





