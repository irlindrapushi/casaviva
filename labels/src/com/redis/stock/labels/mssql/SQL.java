/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.labels.mssql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SQL {	
	public static Connection getConnection() throws SQLException {
		Properties properties = new Properties();
		
		try {
			properties.loadFromXML(new FileInputStream("sql.cfg"));
		} 
		catch (IOException ex) {
			Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return DriverManager.getConnection(properties.getProperty("url"));
	}
}
