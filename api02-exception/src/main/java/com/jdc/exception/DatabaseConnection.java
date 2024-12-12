package com.jdc.exception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	@SuppressWarnings("deprecation")
	public static void create() {
		System.out.println("Create starts.");
		try {
			var object = Book.class.newInstance();
			System.out.println(object);
			
		} catch(Exception ex) {
			System.out.println("Object creation error.");
			ex.printStackTrace();
		}
		System.out.println("Create ends.");
	}

	public static Connection getDbConnection() throws SQLException {
		return DriverManager.getConnection("");
	}
	
}
