package com.jdc.core;

public class ConsoleApplication {
	
	private String appName;
	private AbstractFeature[] features;
	
	public ConsoleApplication(String appName, AbstractFeature[] features) {
		this.appName = appName;
		this.features = features;
	}
	
	public void launch() {
		// show application name
		showMessage(appName, "=");
		
		// show menus
		for(var feature: features) {
			feature.showMenu();
		}
		
		// get menu id input
		// get first number
		// get second number
		// show result
		// ask to continue
		
		// bye bye
		showMessage("Thank you!", "+");
		
	}
	
	private void showMessage(String message, String sign) {
		String line = "";
		
		for(int i = 0, l = message.length(); i < l; i ++) {
			line = line.concat(sign);
		}
		
		System.out.println("%s%s%s%s%s".formatted(sign, sign, line, sign, sign));
		System.out.println("%s%s%s%s%s".formatted(sign, " ", message, " ", sign));
		System.out.println("%s%s%s%s%s".formatted(sign, sign, line, sign, sign));
		
	}

}









