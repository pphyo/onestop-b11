package com.jdc.anno;

//@Value(name = "Class value")
public class UsingAnnotation {

	@Value({ 1, 2, 3 })
	private int var1;
	@Value({ 10, 20, 30 })
	private String var2;

//	@Value(name = "Constructor value")
	public UsingAnnotation() {
	}

	public int getVar1() {
		return var1;
	}

	public void setVar1(int var1) {
		this.var1 = var1;
	}

	public String getVar2() {
		return var2;
	}

	public void setVar2(String var2) {
		this.var2 = var2;
	}

	@Value(value = { 4, 5, 6 }, name = "Method")
	public void use(int[] array, String name) {
		System.out.println("==========");
		System.out.println("= ".concat(name).concat(" ="));
		System.out.println("==========");
		System.out.println();
		
		for(var v : array) {
			System.out.println(v);
		}
	}

	public void notUse() {
	}

}
