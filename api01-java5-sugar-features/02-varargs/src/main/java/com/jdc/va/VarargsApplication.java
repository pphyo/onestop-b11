package com.jdc.va;

public class VarargsApplication {

	public static void main(String... doYourself) {
		int[] array = {1, 2, 3, 4, 5};
		launchForArray(array);
		launchForVarargs("");
	}
	
	static void launchForArray(int[] intArray) {
		for(int i : intArray) {
			System.out.println(i);
		}
	}
	
	static void launchForVarargs(String value, int... intVarargs) {
		for(int i : intVarargs) {
			System.out.println(i);
		}
	}

}