package com.jdc.ocp;

public class GithubLogin implements CanLogin {
	
	@Override
	public void login() {
		System.out.println("Login with Github.");		
	}

}
