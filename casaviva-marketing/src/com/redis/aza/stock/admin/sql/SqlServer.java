/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redis.aza.stock.admin.sql;

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
 * @author Redjan Shabani
 */
public class SqlServer {
	public static Connection getConnection() throws SQLException {
		Properties properties = new Properties();
		try {
			properties.loadFromXML(new FileInputStream("config.properties"));
		} 
		catch (IOException ex) {
			Logger.getLogger(SqlServer.class.getName()).log(Level.SEVERE, null, ex);
		}		
		return DriverManager.getConnection(properties.getProperty("url"));
	}
}
