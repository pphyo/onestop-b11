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
		
		do {
			System.out.println();
			// show menus and get menu id input
			AbstractFeature feature = getSelectedFeature();
			
			System.out.println();
			// show feature name
			showMessage(feature.getFeatureName(), "*");	
			System.out.println();

			// execute selected feature or do business
			feature.doBusiness();
			
			// ask to continue
		} while(askToContinue());
		
		// bye bye
		showMessage("Thank you!", "+");
		InputUtil.SC.close();
	}
	
	private boolean askToContinue() {
		System.out.println();
		var result = InputUtil.getString("Do you want to continue?(y/others): ");

		System.out.println();
		return result.toLowerCase().equals("y");
	}
	
	private AbstractFeature getSelectedFeature() {
		for(var feature: features) {
			feature.showMenu();
		}
		
		System.out.println();
		var selectedId = InputUtil.getInt("Choose: ");
		return features[selectedId - 1];
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









