/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.remote.sql;

import com.redis.casaviva.shop.dc.Product;
import com.redis.casaviva.shop.dc.Warehouse;
import com.redis.casaviva.shop.remote.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SqlWarehouse {
	
	public static Warehouse read(String code){
		Warehouse warehouse = null;
		
		String QUERY = "SELECT TOP 1 name FROM Warehouse WHERE code = ?";
		
		try(Connection connection = SQL.getConnection()){
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setString(1, code);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				
				List<Product> products = SqlProduct.read(connection, code);
				
				warehouse = new Warehouse(code, resultSet.getString("name")) {
					@Override
					public List<Product> getProducts() {return products;}
				};
			}
		}
		catch (SQLException ex) {
			Logger.getLogger(SqlWarehouse.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return warehouse;
	}
}
