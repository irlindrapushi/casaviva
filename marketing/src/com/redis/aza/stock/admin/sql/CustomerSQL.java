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
package com.redis.aza.stock.admin.sql;

import com.redis.aza.stock.admin.core.Subject;
import com.redis.aza.stock.admin.utils.Dataset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Redjan Shabani
 */
public class CustomerSQL implements Dataset<String, Subject> {

	@Override
	public void forEach(Consumer<Subject> consumer) {
		String query = "SELECT [code], [name], [address], [phone], [email] FROM [dbo].[Customer] ORDER BY [name] ASC";
		try {
			Connection cn = SQL.getConnection();
			
			PreparedStatement ps = cn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Subject value = new Subject(
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("address"), 
					rs.getString("phone"), 
					rs.getString("email")
				);
				consumer.accept(value);
			}
			
			cn.close();
		}
		catch (SQLException ex) {
			Logger.getLogger(CustomerSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	
	@Override
	public void forEach(BiConsumer<String, Subject> consumer) {
		String query = "SELECT [key], [code], [name], [address], [phone], [email] FROM [dbo].[Customer] ORDER BY [name] ASC";
		try {
			Connection cn = SQL.getConnection();
			
			PreparedStatement ps = cn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String key = rs.getString("key");
				Subject value = new Subject(
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("address"), 
					rs.getString("phone"), 
					rs.getString("email")
				);
				consumer.accept(key, value);
			}
			
			cn.close();
		}
		catch (SQLException ex) {
			Logger.getLogger(CustomerSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	
	
	public static void main(String[] args) {
		new CustomerSQL().forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
	}
	
}
