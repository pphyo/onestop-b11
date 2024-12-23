package com.jdc.lambda;

@FunctionalInterface
public interface Calculable {
	
	int calculate(int a, int b);
	
	@Override
	boolean equals(Object obj);

	@Override
	String toString();

	@Override
	int hashCode();

}
