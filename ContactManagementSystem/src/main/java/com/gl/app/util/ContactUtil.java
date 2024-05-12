package com.gl.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

import com.gl.app.exception.ContactNotFoundException;

public class ContactUtil {
	 private static final String URL = "jdbc:postgresql://localhost:5432/Capstone_Projects?current_schema=contactmanagement";
	    private static final String USER = "postgres";
	    private static final String PASSWORD = "root";
	   AtomicInteger counter = new AtomicInteger();

	    public static Connection getConnection() throws SQLException {
	      return DriverManager.getConnection(URL,USER,PASSWORD);
	    }
	    public int generateUniqueId(String columnName, String tableName, int initialValue) {
	       
			return 0;
	    }
	    
	    
}
