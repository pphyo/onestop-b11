package com.jdc.constructor;

class Person {

	// must "a-z||A-Z", must not "   ", "", "12"
	private String name;
	private int age = 1;
	// must "+959", "09", length 9
	private String phone;
//	name = ""; // compile error

	void setName(String input) {
		// ""
		char first = input.length() == 0 ? '\u0000' : input.charAt(0);

		boolean isSmall = first >= 65 && first <= 90;
		boolean isCapital = first >= 97 && first <= 122;

		if(!(isSmall || isCapital)) {
			System.out.println("Please enter correct name!");
		} else {
			name = input;
		}
	}

	String getName() {
		return name;
	}

	// setter or mutator
	void setAge(int input) {
		if(input <= 0) {
			System.err.println("Age must be greater than zero.");
		} else {
			age = input;
		}
	}

	// getter or accessor
	int getAge() {
		return age;
	}

	void setPhone(String input) {
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

		if(result) {
			phone = input;
		} else {
			System.out.println("Your mobile number is incorrect!");
		}

	}

	String getPhone() {
		return phone;
	}

	// default constructor => constructor with no argument
	// to create class's instance
	Person(String name) {
		System.out.println("Person constructor...");
	}

}