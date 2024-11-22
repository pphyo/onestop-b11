package com.jdc.inheritance.model;

public class Book {

	private String title;
	private double price;
	private String author;
	private int stock;
	private String isbn;

	public Book(String title) {
		this.title = title;
		System.out.println("1 arg");
	}

	public Book(String title, double price) {
		this(title);
		this.price = price;
		System.out.println("2 args");
	}

	public Book(String title, double price, String author) {
		this(title, price);
		this.author = author;
		System.out.println("3 args");
	}

	public Book(String title, double price, String author, int stock) {
		this(title, price, author);
		this.stock = stock;
		System.out.println("4 args");
	}

	public Book(String title, double price, String author, int stock, String isbn) {
		this(title, price, author, stock);
		this.isbn = isbn;
		System.out.println("All args");
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public String getAuthor() {
		return author;
	}

	public int getStock() {
		return stock;
	}

	public String getIsbn() {
		return isbn;
	}
}