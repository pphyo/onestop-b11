package com.jdc.constructor;

class Person {

	private String name;
	private int age;
	private String phone;

	void setAge(int input) {
		if(input <= 0) {
			System.err.println("Age must be greater than zero.");
		}
		age = input;
	}

	// default constructor => constructor with no argument
	// to create class's instance
	Person(String name) {
		System.out.println("Person constructor...");
	}

}