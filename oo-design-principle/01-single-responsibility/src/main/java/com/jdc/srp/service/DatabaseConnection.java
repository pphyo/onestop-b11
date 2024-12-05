package com.jdc.srp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public static final String URL = "jdbc:mysql://localhost:3306/srp_db";
	public static final String USR = "admin";
	public static final String PWD = "root";
	
	public static Connection getDbConnection() throws SQLException {
		return DriverManager.getConnection(URL, USR, PWD);
	}

}
