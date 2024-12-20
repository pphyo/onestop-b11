package com.jdc.generic;

class ClzZ {}
class ClzA {}
interface ClzB {}
interface ClzC {}

class D<T extends ClzA & ClzB & ClzC> {}

@SuppressWarnings("all")
public class NaturalNumber<T extends Integer> {
	
	private T n;
	
	public NaturalNumber(T n) {
		this.n = n;
	}
	
	public boolean isEven() {
		return n % 2 == 0;
	}

}
