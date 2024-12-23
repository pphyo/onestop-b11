package com.jdc.lambda.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class FunctionalInterfaceTest {
	
	record Payload(String name, LocalDate issueAt) {}
	
	@Test
	void test_for_supplier() {
		Supplier<String> stringSup = () -> "Hello supplier.";
		assertEquals("Hello supplier.", stringSup.get());
		
		Supplier<Payload> plSup = () -> new Payload("Request", LocalDate.now());
		var payload = plSup.get();
		assertEquals("Request", payload.name());
	}
	
	@Test
	void test_for_predicate() {
		Predicate<Integer> evenPred = i -> i % 2 == 0;
		Predicate<Integer> oddPred = evenPred.negate();
		Predicate<Integer> greaterThan50 = i -> i > 50;
		Predicate<Integer> pred = oddPred.and(greaterThan50);
		assertTrue(pred.test(51));
		assertFalse(pred.test(100));
	}
	
	@Test
	void test_for_function() {
		// Integer apply(String t);
		Function<String, Integer> strToInt = str -> Integer.parseInt(str);
		assertEquals(10, strToInt.apply("10"));
		
		Function<Integer, String> intToStr = i -> String.valueOf(i);
		assertEquals("10", intToStr.apply(10));
		
		Function<String, String> strToStr = str -> "Hello ".concat(str);
		assertEquals("Hello consumer", strToStr.apply("consumer"));
		
		Function<Integer, Integer> twice = i -> i * 2;
		Function<Integer, Integer> square = i -> i * i;
		
		int result1 = twice.andThen(twice).andThen(square).apply(2);
		assertEquals(64, result1);
		
		int result2 = twice.compose(twice).compose(square).apply(2);
		assertEquals(16, result2);
		
		int result3 = square.andThen(twice)
						.compose(twice).andThen(twice)
						.compose(square).apply(2);
		assertEquals(256, result3);
	}

	@Test
	void test_for_consumer() {
		// strCon = (String str) -> System.out.println(str);
		Consumer<String> strCon = System.out::println;
		strCon.accept("Hello from consumer");

		List<Integer> list = List.of(1, 2, 3, 4, 5);
		for (Integer i : list) {
			System.out.print(i + ", ");
		}
		System.out.println();
		list.forEach(i -> System.out.print(i + ", "));
		System.out.println();
		list.forEach(System.out::print);

		System.out.println();

		Consumer<String> c1 = str -> System.out.println("C1 -> " + str);
		Consumer<String> c2 = str -> System.out.println("C2 -> " + str);

		c1.andThen(c2).andThen(c1).accept("Consumer");

	}

}
