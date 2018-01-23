package com.pil.web;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MySQLConnectionTest {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/sys?useSSL=false";
	
	private static final String USER = "root";
	private static final String PW = "";

	@Test
	public void testConnection() throws ClassNotFoundException {
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con);
		} catch(SQLException se) {
			se.printStackTrace();
			fail("Not yet implemented");
		}
	}
}
