/*
 * Copyright (C) 2018 user
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.redis.casaviva.shop.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 *
 * @author user
 */
public class SQL {
	
	private static final Preferences PREFERENCES = Preferences.userNodeForPackage(SQL.class);
	
	public static String getUrl() {return PREFERENCES.get("url", "jdbc:sqlserver://192.168.1.14:3306;databaseName=REDIS_CA");}
	public static String getUsername() {return PREFERENCES.get("username", "sa");}
	public static String getPassword() {return PREFERENCES.get("password", "");}
	
	public static void setUrl(String url) {PREFERENCES.put("url", url);}
	public static void setUsername(String username) {PREFERENCES.put("username", username);}
	public static void setPassword(String password) {PREFERENCES.put("password", password);}
	
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(getUrl(), getUsername(), getPassword());
	}
}
