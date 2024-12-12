package com.jdc.exe.exception;

import static com.jdc.exe.exception.support.StringUtils.*;

import com.jdc.exe.exception.database.ProductDatabase;
import com.jdc.exe.exception.domain.Product;
import com.jdc.exe.exception.support.ProductInitializer;

import static com.jdc.exe.exception.support.InputUtils.*;

public class Runner {
	
	private static final String[] menus = {"Create", "Find All"};
	
	private ProductDatabase productDatabase;
	
	private static final String DATA = """
			Burn, 2500, 50
			Pwint Pyu, 500, 100
			Red Bull, 2500, 40
			Cloret, 1000, 10
			""";
	
	static {
		new ProductInitializer().init(DATA);
	}
	
	public Runner() {
		productDatabase = ProductDatabase.getInstance();
	}
	
	void run() {
		// show title
		showMessage("Product Management");
		
		do {
			System.out.println();
			// show menus
			showMenus();
			
			System.out.println();
			
			operate(readInt("Choose: "));
			
			System.out.println();
		} while(readString("Continue? (y/others): ").toLowerCase().equals("y"));
		
		System.out.println();
		showMessage("Bye Bye...");
		sc.close();
	}
	
	private void operate(int chosenMenu) {
		switch(chosenMenu) {
		case 1:
			createProduct();
			break;
		case 2:
			findAllProduct();
			break;
		default:
			System.out.println("Wrong menu number!");
		}
	}
	
	private void createProduct() {
		
		try {
			// get product name input
			String name = readString("Enter product name: ");
			if(isEmpty(name)) {
				// handling exception
				throw new ProductException("Name can't be empty!");
			}
			
			// get product price input
			int price = readInt("Enter product proice: ");
			if(price <= 0) {
				throw new ProductException("Price must be greater than zero!");
			}
			
			// get product stock input
			int stock = readInt("Enter stock number for product: ");
			if(stock <= 0) {
				throw new ProductException("Stock number must be greater than zero!");
			}
			
			// create product object
			Product p = new Product();
			p.setName(name);
			p.setPrice(price);
			p.setStock(stock);
			
			// save into database
			productDatabase.create(p);
			System.out.println("Product is saved in database.");
		} catch(ProductException e) {
			System.err.println(e.getMessage());
		} catch(NumberFormatException e) {
			System.err.println("Please enter digit only for price and stock!");
		}
		
	}
	
	private void findAllProduct() {
		String titleFormat = "%-4s %-20s %-8s %-5s";
		String tableFormat = "%04d %-20s %6.2f %5d";
		
		var products = productDatabase.findAll();
		
		if(products.length == 0) {
			System.out.println("No product in database!");
		} else {
			
			System.out.println(titleFormat.formatted("ID", "Name", "Price", "Stock"));
			for(var product : products) {
				System.out.println(tableFormat
						.formatted(product.getId(), 
								product.getName(),
								product.getPrice(),
								product.getStock())
						);
			}
			
		}
		
	}
	
	private void showMenus() {
		for(int i = 0; i < menus.length; i ++) {
			System.out.println("%d. %s".formatted(i + 1, menus[i]));
		}
	}

}
