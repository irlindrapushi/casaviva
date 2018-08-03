package com.redis.stock.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import com.redis.stock.core.Inventory;
import com.redis.stock.core.Item;
import com.redis.stock.core.Stock;
import com.redis.utils.sets.Set;

/*
 * Copyright (C) 2018 Redjan Shabani
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

/**
 *
 * @author Redjan Shabani
 */
public final class SQL {
	
	private static final Preferences PREFERENCES = Preferences.userNodeForPackage(SQL.class);
	
	private SQL() {}
	
	
	
	public static Connection getConnection() throws SQLException {		
		return DriverManager.getConnection(
			PREFERENCES.get("url", ""), 
			PREFERENCES.get("username", ""), 
			PREFERENCES.get("password", "")
		);
	}
	
	
	private static Connection connection;
	
	public static void connect() throws SQLException {
		System.out.print("Connection ...");
		
		if(connection == null || connection.isClosed()) {
			System.out.print("Opening SQL connection ...");
			connection = DriverManager.getConnection(
				PREFERENCES.get("url", ""), 
				PREFERENCES.get("username", ""), 
				PREFERENCES.get("password", "")
			);
		}
		
		System.out.println(" done!");
	}
	
	public static void disconnect() throws SQLException {
		System.out.print("Disconnection ...");
		
		if(connection != null && !connection.isClosed())
			connection.close();
		connection = null;
		
		System.out.println(" done!");
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Inventory getInventory() {
		Set<Stock> stocks = new StockSetImpl();
		Set<Item> items = new ItemSetImpl();
		
		
		return new Inventory() {
			@Override
			public Set<Stock> getStocks() {return stocks;}

			@Override
			public Set<Item> getItems() {return items;}
			
			
		};
	}
}
