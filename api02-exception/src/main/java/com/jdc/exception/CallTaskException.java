package com.jdc.exception;

public class CallTaskException {
	
	static void doOne() {
		System.out.println("doOne() starts.");
		doTwo();
		System.out.println("doOne() ends.");
	}
	
	static void doTwo() {
		System.out.println("doTwo() starts.");
		// multi-handler
		// down child to parent
		try {
			doThree();
		} catch(NumberFormatException ex) {
			System.err.println("NumberFormatException occurs.");
		} catch(ArithmeticException ex) {
			System.err.println("ArithmeticException occurs.");
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.err.println("ArrayIndexOutOfBoundsException occurs");
		} catch(IndexOutOfBoundsException ex) {
			System.err.println("IndexOutOfBoundsException occurs");
		} catch(Exception ex) {
			System.err.println("OtherException occurs");
		}
		
		// multi-catch
		// avoid same hierarchy
		try {
			
		} catch(ArithmeticException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
		
		System.out.println("doTwo() ends.");
	}
	
	static void doThree() {
		System.out.println("doThree() starts.");
		doFour();
		System.out.println("doThree() ends.");
	}
	
	static void doFour() {
		System.out.println("doFour() starts.");
		System.out.println(new int[] {1, 2, 3}[3]);
		System.out.println(3 / 0);
		System.out.println(Integer.parseInt("were"));
		System.out.println("doFour() ends.");
	}

}









