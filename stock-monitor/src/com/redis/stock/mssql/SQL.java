/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 *
 * @author Redjan Shabani
 */
public final class SQL {	
	
	private static final Preferences preferences = Preferences.userNodeForPackage(SQL.class).node("L86724204J");
	
	public static String getUrl() {return preferences.get("url", "");}	
	public static String getUsername() {return preferences.get("username", "");}	
	public static String getPassword() {return preferences.get("password", "");}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(getUrl(), getUsername(), getPassword());
	}
}
