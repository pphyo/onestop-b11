package com.jdc.calculator.features;

public class DivisionFeature extends AbstractCalculationFeature {
	
	public DivisionFeature() {
		super(4, "Division");
	}
	
	@Override
	public void calculate(int d1, int d2) {
		System.out.println("%d / %d is %.2f.".formatted(d1, d2, d1 / d2));
	}

}
