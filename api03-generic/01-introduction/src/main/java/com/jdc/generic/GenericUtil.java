package com.jdc.generic;

import java.util.List;

public class GenericUtil {
	
	public static void printList(List<?> list, String listType) {
		System.out.println(listType);
		for(Object obj : list) {
			System.out.println(obj);
		}
		System.out.println();
	}
	
	public static void addNumbers(List<? super Integer> list) {
		for(int i = 0; i <= 10; i ++) {
			list.add(i);
		}
	}
	
	public static <A, B, X extends Exception> boolean compair(Pair<A, B> p1, Pair<A, B> p2) throws X {
		return p1.getKey() == p2.getKey() &&
				p1.getValue() == p2.getValue();
	}
	
	public static <T extends Comparable<T>> int countGreaterThan(T[] array, T ele) {
		int count = 0;
		
		for(T t : array) {
			if(t.compareTo(ele) > 0) {
				count ++;
			}
		}
		
		return count;
	}

}
