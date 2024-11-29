package com.jdc.calculator.features;

public class AddFeature extends AbstractCalculationFeature {

	public AddFeature() {
		super(1, "Add");
	}

	@Override
	public void calculate(int d1, int d2) {
		System.out.println("%d + %d is %d.".formatted(d1, d2, d1 + d2));		
	}

}
