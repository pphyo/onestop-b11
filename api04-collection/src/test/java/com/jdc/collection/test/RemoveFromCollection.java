package com.jdc.collection.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class RemoveFromCollection {

	@Test
	void test() {
		var nameSet = new HashSet<String>();
		Collections.addAll(nameSet, "Toby Maguire", "Chris Evan", "Nicolas Cage", "Tom Hardy", "Tom Holland");

		nameSet.remove("Nicolas Cage");
		System.out.println(nameSet);

//		for(String name : nameSet) {
//			if(name.startsWith("Tom")) {
//				nameSet.remove(name);
//			}
//		}

		Iterator<String> itr = nameSet.iterator();

		for (;itr.hasNext();) {
			String ele = itr.next();
			if (ele.startsWith("Tom"))
				itr.remove();
		}

		System.out.println(nameSet);
	}

}
