package com.jdc.ocp;

public class FacebookLogin implements CanLogin {
	
	@Override
	public void login() {
		System.out.println("Login with Facebook.");		
	}

}
