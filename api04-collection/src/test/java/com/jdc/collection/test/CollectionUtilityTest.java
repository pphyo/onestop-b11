package com.jdc.collection.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jdc.collection.NoteBook;

public class CollectionUtilityTest {
	
	@Test
	void test_for_immutable_collection() {
		var list = new ArrayList<Integer>(); // mutable type
		Collections.addAll(list, 1, 2, 3, 4, 5);
		
		var ofList = List.of(1, 2, 3, 4, 5); // immutable type
		var umList = Collections.unmodifiableList(list); // immutable type
		var asList = Arrays.asList(1, 2, 3, 4, 5);
		
		asList.set(2, 10);
		System.out.println(ofList);
		System.out.println(umList);
		System.out.println(asList);
		
	}
	
	@Test
	void test_for_sort_notebook() {
		var nb1 = new NoteBook(1, "Zen Book", "Asus", 16, 1000000);
		var nb2 = new NoteBook(2, "Lattitude", "Dell", 8, 700000);
		var nb3 = new NoteBook(3, "Pro Book", "HP", 16, 900000);
		var nb4 = new NoteBook(4, "Surface Pro", "Microsoft", 32, 1500000);
		var nb5 = new NoteBook(5, "Mac Book Pro", "Apple", 8, 3000000);
		
		var list = new ArrayList<NoteBook>(List.of(nb1, nb2, nb3, nb4, nb5));
		Collections.sort(list);
		System.out.println(list);
		
		Collections.sort(list, (obj1, obj2) -> (int)(obj1.getPrice() - obj2.getPrice()));
		System.out.println(list);
	}

	@Test
	@Disabled
	void test() {

		List<String> list = new ArrayList<String>();
		list.add("1 of Heart");
		list.add("9 of Heart");
		list.add("K of Heart");
		list.add("5 of Heart");
		list.add("10 of Heart");
		list.add("3 of Heart");
		list.add("4 of Heart");
		list.add("J of Heart");
		list.add("6 of Heart");
		list.add("2 of Heart");
		list.add("7 of Heart");
		list.add("8 of Heart");
		list.add("Q of Heart");

		System.out.println(list);
		Collections.shuffle(list);
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
	}

}
