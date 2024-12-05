package com.jdc.ocp;

public class OcpApplication {
	
	public static void main(String[] args) {
		LoginController controller = new LoginController(new LoginService());
		controller.login(LoginType.GITHUB);
	}

}
