package com.jdc.exe.exception.domain;

import com.jdc.exe.exception.ProductException;

public class Product {

	private int id;
	private String name;
	private double price;
	private int stock;

	public Product() {
		id = IdGenerator.next();
	}
	
	// "Burn, 2500, 50"
	public Product(String line) {
		this();
		var array = line.split("\\,");
		// ["Burn", " 2500", " 50"]
		try {
			name = array[0];
			price = Double.parseDouble(array[1].strip());
			stock = Integer.parseInt(array[2].strip());
		} catch(ArrayIndexOutOfBoundsException ex) {
			throw new ProductException("Data format is invalid.", ex);
		}
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
