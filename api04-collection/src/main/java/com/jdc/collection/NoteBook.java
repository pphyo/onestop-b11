package com.jdc.collection;

public class NoteBook implements Comparable<NoteBook> {

	private int id;
	private String name;
	private String model;
	private int ram;
	private double price;

	@Override
	public int compareTo(NoteBook o) {
		return id - o.getId();
	}

	public NoteBook(int id, String name, String model, int ram, double price) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.ram = ram;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public int getRam() {
		return ram;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "NoteBook [id=" + id + ", name=" + name + ", model=" + model + ", ram=" + ram + ", price=" + price + "]";
	}

}
