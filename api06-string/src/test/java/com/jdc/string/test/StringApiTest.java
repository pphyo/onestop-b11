package com.jdc.string.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.jdc.app.Course;

public class StringApiTest {
	
	@Test
	void test_for_equal_operator_and_dot_equals_method() {
		
		Course c1 = new Course("Java SE", 300000, 4);
		Course c2 = new Course("Java SE", 400000, 4);
		Course c3 = new Course("Java SE", 400000, 5);
		
		assertFalse(c1 == c2);
		assertTrue(c1.equals(c2));
		assertFalse(c2.equals(c3));
		
	}
	
	@Test
	void test_for_mutable_string() {
		StringBuilder sb = new StringBuilder();
		sb.append("I'm");
		sb.append(" so");
		sb.append(" lonely.");
		assertEquals("I'm so lonely.", sb.toString());
	}
	
	@Test
	void test_for_immutable_string() {
		String value = "Hello";
		value.concat(" String api");
		System.out.println(value);
		
		var lower = value.toLowerCase();
		System.out.println(lower);
		System.out.println(value);
		
		String val1 = "Boom";
		String val2 = "Boom";
		String val3 = new String("Boom");
		String val4 = new String("Boom");
		assertTrue(val1 == val2);
		assertFalse(val2 == val3);
		assertFalse(val3 == val4);
		assertTrue(val2.equals(val3));
		assertTrue(val3.equals(val4));
		
		System.out.println(System.identityHashCode(val2));
		System.out.println(System.identityHashCode(val3));
		System.out.println(System.identityHashCode(val4));
		
		System.out.println("Sum: " + (10 + 20));
		
		String emptyString = "";		
		assertNotNull(emptyString);
		assertTrue(emptyString.isEmpty());
		assertTrue(emptyString.isBlank());

		String blankString = "   ";
		assertNotNull(blankString);
		assertFalse(blankString.isEmpty());
		assertTrue(blankString.isBlank());
		
		String nullStr = null;
		assertNull(nullStr);

	}

}










