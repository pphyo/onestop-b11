package com.jdc.exe.exception.support;

import com.jdc.exe.exception.database.ProductDatabase;
import com.jdc.exe.exception.domain.Product;

public class ProductInitializer {
	
	public void init(String data) {
		var database = ProductDatabase.getInstance();
		data.lines().forEach(line -> {
			database.create(new Product(line));
		});
	}

}
