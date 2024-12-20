package com.jdc.collection.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ListInterfaceTest {

	@Test
	void test_for_linked_list() {
		var ll = new LinkedList<Integer>(Arrays.asList(20, 4, 51, 8, 30, 44, 12));
		assertEquals(7, ll.size());
		assertEquals(20, ll.element());
		assertEquals(20, ll.getFirst());
		assertEquals(20, ll.peek());
		assertEquals(7, ll.size());
		
		assertEquals(20, ll.poll());
		assertEquals(6, ll.size());
		
	}

	@Test
	@Disabled
	void test_for_array_list() {

		var al = new ArrayList<Integer>();
		Collections.addAll(al, 20, 4, 51, 8, 30, 44, 12);

		assertTrue(al.add(8));
		assertEquals(51, al.get(2));

		al.add(2, 44);

		assertEquals(44, al.get(2));
		assertEquals(20, al.getFirst());
		assertEquals(8, al.getLast());
		assertEquals(9, al.size());

		assertEquals(2, al.indexOf(44));
		assertEquals(6, al.lastIndexOf(44));

		assertEquals(51, al.remove(3));
		assertEquals(8, al.size());

		assertEquals(true, al.remove(Integer.valueOf(44)));

		System.out.println(al);

		al.set(4, 97);
		System.out.println(al);

	}

}
