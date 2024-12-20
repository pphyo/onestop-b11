package com.jdc.generic.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.generic.GenericUtil;
import com.jdc.generic.KeyStringPair;
import com.jdc.generic.KeyValueStringPair;
import com.jdc.generic.MonthsOfYear;
import static com.jdc.generic.MonthsOfYear.*;
import com.jdc.generic.OrderPair;
import com.jdc.generic.Pair;
import com.jdc.generic.ValueStringPair;

@TestMethodOrder(OrderAnnotation.class)
public class GenericIntroductionTest {
	
	@Test
	@Order(6)
	void testForLowerBoundWildcard() {
		List<Number> numList = new ArrayList<>();
		List<Integer> intList = new ArrayList<>();
		List<Object> objList = new ArrayList<>();
//		List<Double> doubleList = new ArrayList<>();
//		List<String> stringList = new ArrayList<>();
		System.out.println(numList instanceof ArrayList);
		
		GenericUtil.addNumbers(numList);
		GenericUtil.addNumbers(intList);
		GenericUtil.addNumbers(objList);
//		GenericUtil.addNumbers(stringList); // error bcuz of lower bound
		
		GenericUtil.printList(numList, "Number");
		GenericUtil.printList(intList, "Integer");
		GenericUtil.printList(objList, "Object");
	}
	
	@Test
	@Order(5)
	void testForMethodBound() {
		String[] strArray = {"ABC", "ACBZE", "BAC", "CCA", "CAB"};
		int strResult = GenericUtil.countGreaterThan(strArray, "ABC");
		assertEquals(4, strResult);
		
		Integer[] intArray = {1, 2, 3, 4, 5, 6};
		int intResult = GenericUtil.countGreaterThan(intArray, 3);
		assertEquals(3, intResult);
		
		MonthsOfYear[] moyArray = {JAN, FEB, MAR, APR, MAY};
		int moyResult = GenericUtil.countGreaterThan(moyArray, MAR);
		assertEquals(2, moyResult);
		
//		Thread[] tArray = {new Thread(), new Thread(), new Thread()};
//		GenericUtil.countGreaterThan(tArray, null); // error bcuz thread is not type of comparable
	}
	
	@Test
	@Order(4)
	void testForMultipleTypeParamemter() {
		Pair<String, Integer> siPair = new OrderPair<>("Google", 4);
		Pair<Integer, String> isPair =  new OrderPair<>(1, "NVIDIA");
		
		Pair<String, Integer> ksPair = new KeyStringPair<>("Apple", 2);
		siPair = ksPair;
		ksPair = siPair;
		
		Pair<Integer, String> vsPair = new ValueStringPair<>(3, "Microsoft");
//		isPair = vsPair;
//		vsPair = isPair;
		
		Pair<String, String> kvsPair = new KeyValueStringPair("Google", "Youtube");
		assertEquals("Google", kvsPair.getKey());
		var result = GenericUtil.compair(isPair, vsPair);
		assertEquals(false, result);
		
//		siPair = isPair; // illegal
		
	}
	
	@Test
	@Order(3)
	void testForGenericClass() {
		
		com.jdc.generic.Box<Integer> intBox = new com.jdc.generic.Box<>();
		intBox.setData(200);
		assertEquals(200, intBox.getData());
		
		com.jdc.generic.Box<String> strBox = new com.jdc.generic.Box<>();
		strBox.setData("String Box");
		strBox.<Number>inspect(new BigDecimal(10));
		
		assertEquals("String Box", strBox.getData());
		
	}
	
	@Test
	@Order(2)
	void testForGenericCode() {
		java.util.List<String> list = new java.util.ArrayList<String>();
		list.add("Hello Generic");
//		list.add(200); // illegal in generic
		assertEquals("Hello Generic", list.get(0));
	}

	@Test
	@Order(1)
	@SuppressWarnings({"unchecked", "rawtypes"})
	void testForNonGenericCode() {
		java.util.List list = new java.util.ArrayList();
		list.add("Hello Non Generic");
		list.add(200);
		list.add(true);
		list.add(new java.util.Date());

		String strData = (String) list.get(0);
		int intData = (int) list.get(1);
		boolean boolData = (boolean) list.get(2);

		assertEquals(strData, "Hello Non Generic");
		assertEquals(200, intData);
		assertTrue(boolData);

		assertThrows(ClassCastException.class, () -> {
			var date = (String) list.get(3);
			System.out.println(date);
		});
	}

}
