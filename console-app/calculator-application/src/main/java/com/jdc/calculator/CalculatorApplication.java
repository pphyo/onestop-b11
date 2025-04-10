package com.jdc.calculator;

import com.jdc.calculator.features.AddFeature;
import com.jdc.calculator.features.DivisionFeature;
import com.jdc.calculator.features.MultiplyFeature;
import com.jdc.calculator.features.TakeawayFeature;
import com.jdc.core.AbstractFeature;
import com.jdc.core.ConsoleApplication;

public class CalculatorApplication {

	public static void main(String[] args) {
		
		AbstractFeature[] features = {
									new AddFeature(), 
									new TakeawayFeature(),
									new MultiplyFeature(),
									new DivisionFeature()
								};
		
		var app = new ConsoleApplication("Calculator App", features);
		app.launch();
		
	}
	
}
