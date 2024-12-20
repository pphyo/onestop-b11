package com.jdc.generic;

public class Box<T> {

	private T data;
	// private T[] array = new T[0]; // cannot create array of parameterized type

	public <U extends Number> void inspect(U u) {
		System.out.println("T: " + data.getClass().getName());
		System.out.println("U: " + u.getClass().getName());
	}
	
	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

}
