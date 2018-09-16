/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redis.aza.stock.admin.sql;

import com.redis.aza.stock.admin.core.Catalog;
import com.redis.aza.stock.admin.core.State;
import com.redis.aza.stock.admin.core.Stock;
import com.redis.aza.stock.admin.core.Subject;
import com.redis.aza.stock.admin.core.Warehouse;
import com.redis.aza.stock.admin.sql.state.SqlState;
import com.redis.aza.stock.admin.utils.Dataset;
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
public class SQL {
	
	
	
	
	
	
	
	
	
	
	
	public static Stock getStock() {
		
		return new Stock() {
						
			@Override
			public Dataset<String, Subject> suppliers() {
				return new SupplierSQL();
			}			

			@Override
			public Dataset<String, Subject> customer() {
				return new CustomerSQL();
			}

			@Override
			public Dataset<String, ? extends Warehouse> warehouses() {
				return new WarehouseSQL();
			}
			
			@Override
			public Catalog getCatalog() {
				return new CatalogSQL();
			}
			

			@Override
			public State getState() {
				return SqlState.getState();
			}

			@Override
			public State getState(Warehouse warehouse) {
				
				return warehouse == null ? this.getState() : SqlState.getState(warehouse.getCode());
			}

			
		};
	}
	
	
	
	
	
	public static Connection getConnection() throws SQLException {
		Properties properties = new Properties();
		try {
			properties.loadFromXML(new FileInputStream("config.properties"));
		} 
		catch (IOException ex) {
			Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
		}		
		return DriverManager.getConnection(properties.getProperty("url"));
	}
}
