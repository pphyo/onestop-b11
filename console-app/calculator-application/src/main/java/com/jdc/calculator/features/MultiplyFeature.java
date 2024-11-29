package com.jdc.calculator.features;

public class MultiplyFeature extends AbstractCalculationFeature {

	public MultiplyFeature() {
		super(3, "Multiply");
	}
	
	@Override
	public void calculate(int d1, int d2) {
		System.out.println("%d * %d is %d.".formatted(d1, d2, d1 * d2));
	}

}
