package com.jdc.exe.exception.support;

import com.jdc.exe.exception.database.ProductDatabase;
import com.jdc.exe.exception.domain.Product;

public class ProductInitializer {
	
	public void init(String data) {
		var database = ProductDatabase.getInstance();
		
		var lines = data.split("\n");
		
		for(var line : lines) {
			database.create(new Product(line));
		}
		
//		data.lines().forEach(line -> {
//			database.create(new Product(line));
//		});
	}

}
