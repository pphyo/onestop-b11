package com.jdc.exception;

class Parent {
	public void doJob() throws IllegalAccessException {}
}

class Child extends Parent {
	@Override
	public void doJob() {}
}

public class ExceptionApplication {

	public static void main(String[] args) {
		launchForCallTask();
	}
	
	static void launchForCheckedException() {
		DatabaseConnection.create();
	}
	
	static void launchForCallTask() {
		CallTaskException.doOne();
	}
	
	static void launchForExceptionBasicConcept() {
		System.out.println("Program initializing...");
		
		try {
			System.out.println("Before error.");
			System.out.println(Integer.parseInt("qwer")); // cause runtime error
			System.out.println("After error.");
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
//			System.err.println("Enter number only to change number!");
		}
		
		System.out.println("Program terminating...");
	}

}
