package com.jdc.constructor.model;

public class Person {

	// must "a-z||A-Z", must not "   ", "", "12"
	String name;
	int age = 1;
	// must "+959", "09", length 9
	String phone;
//	name = ""; // compile error

	// default constructor => constructor with no argument
	// to create class's instance
	public Person(String name) {
		System.out.println("Person constructor...");
	}

	public void setName(String name) {
		// ""
		char first = name.length() == 0 ? '\u0000' : name.charAt(0);

		boolean isSmall = first >= 65 && first <= 90;
		boolean isCapital = first >= 97 && first <= 122;

		if(!(isSmall || isCapital)) {
			System.err.println("Please enter correct name!");
		} else {
			// variable hiding
			this.name = name;
		}
	}

	public String getName() {
		return name;
	}

	// setter or mutator
	public void setAge(int age) {
		if(input <= 0) {
			System.err.println("Age must be greater than zero.");
		} else {
			this.age = age;
		}
	}

	// getter or accessor
	public int getAge() {
		return age;
	}

	public void setPhone(String phone) {
		if(isValidPhone(phone)) {
			this.phone = phone;
		} else {
			System.err.println("Your mobile number is incorrect!");
		}
	}

	public String getPhone() {
		return phone;
	}

	boolean isValidPhone(String input) {
		String prefixOne = "+959";
		String prefixTwo = "09";

		boolean result = false;

		if(input.startsWith(prefixOne) || input.startsWith(prefixTwo)) {
			if(input.startsWith(prefixOne)) {
				result = input.substring(4).length() == 9 ? true : false;
			} else {
				result = input.substring(2).length() == 9 ? true : false;
			}
		}

		return result;
	}

}