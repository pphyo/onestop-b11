package com.jdc.calculator.features;

import com.jdc.core.AbstractFeature;
import com.jdc.core.InputUtil;

public abstract class AbstractCalculationFeature extends AbstractFeature {

	public AbstractCalculationFeature(int id, String featureName) {
		super(id, featureName);
	}

	@Override
	public void doBusiness() {
		// get digit one
		var d1 = getDigitOne();
		
		// get digit two
		var d2 = getDigitTwo();
		
		// show result
		calculate(d1, d2);
	}
	
	public abstract void calculate(int d1, int d2);
	
	private int getDigitOne() {
		return InputUtil.getInt("Digit one: ");
	}
	
	private int getDigitTwo() {
		return InputUtil.getInt("Digit two: ");
	}

}




