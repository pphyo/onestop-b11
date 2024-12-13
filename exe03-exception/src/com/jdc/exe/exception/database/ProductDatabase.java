package com.jdc.exe.exception.database;

import java.util.Arrays;

import com.jdc.exe.exception.domain.Product;

public class ProductDatabase {
	
	private Product[] database = new Product[0];
	private static ProductDatabase INSTANCE;
	
	private ProductDatabase() {
	}
	
	/**
	 * @author pphyo	
	 * @return this
	 */
	public static ProductDatabase getInstance() {
		if(null == INSTANCE) {
			INSTANCE = new ProductDatabase();
		}
		
		return INSTANCE;
	}
	
	/**
	 * 
	 * @param product
	 */
	public void create(Product product) {
		var temp = Arrays.copyOf(database, database.length + 1);
		temp[temp.length - 1] = product;
		database = temp;
	}
	
	public Product[] findAll() {
		return database;
	}

}
