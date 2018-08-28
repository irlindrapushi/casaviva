/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.remote;

import com.redis.casaviva.shop.dc.Warehouse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SQL {
		
	public static String getURL(){
		return Preferences.userNodeForPackage(SQL.class).get("url", "");
	}	
	
	
	public static Connection getConnection() throws SQLException{
		return  DriverManager.getConnection(SQL.getURL());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static final String SQL_LOGIN = "SELECT TOP 1 warehouse FROM User WHERE username = ? AND password = ?";
	
	public static Warehouse login(String username, String password) {
		
		
		
		
		Warehouse warehouse = null;
		
		String url = Preferences.userNodeForPackage(SQL.class).get("url", "");
		System.out.println(url);
		
		return warehouse;
	}
	
	public static void main(String[] args) {
		login("redjan", "bigbang");
	}
}
