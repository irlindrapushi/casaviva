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
package com.redis.stock.sql;

import com.redis.stock.core.Warehouse;
import com.redis.utils.sets.Set;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class SQL {	
	
	public static Set<Warehouse> getWarehouses() {
		return new Set<Warehouse>() {
			@Override
			public void forEach(Consumer<Warehouse> consumer) {
				WarehouseSQL.forEach(consumer);
			}
		};
	}
	
	public static Connection getConnection() throws SQLException, FileNotFoundException, IOException {		
		FileInputStream fileInputStream = new FileInputStream("database.xml");
		
		Properties properties = new Properties();
		properties.loadFromXML(fileInputStream);
		
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		fileInputStream.close();
		
		return DriverManager.getConnection(url, username, password);
	}
	
	
	
	public static void main(String[] args) {
		try(Connection c = SQL.getConnection()) {
			System.out.println(c.getCatalog());
		} 
		catch (SQLException | IOException ex) {
			Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}
