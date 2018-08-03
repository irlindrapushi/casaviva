/*
 * Copyright (C) 2017 Redjan Shabani
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

package com.redis.aza.stock.admin.sql;

import com.redis.aza.stock.admin.core.Warehouse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class SqlWarehouse {
	
	public static List<Warehouse> getWarehouses() {
		List<Warehouse> warehouses = new ArrayList<>();
		try(Connection connection = SqlServer.getConnection()) {
			String sql = ""
				   + "SELECT [code], [name], [address], [phone], [email] "
				   + "FROM [Warehouse] ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Warehouse warehouse = new Warehouse(
					resultSet.getString("code"), 
					resultSet.getString("name"), 
					resultSet.getString("address"), 
					resultSet.getString("phone"), 
					resultSet.getString("email")
				);
				
				warehouses.add(warehouse);
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(SqlWarehouse.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return warehouses;
	}
	
	
	
}
