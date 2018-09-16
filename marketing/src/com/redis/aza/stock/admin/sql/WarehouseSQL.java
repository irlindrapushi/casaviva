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

import com.redis.aza.stock.admin.core.State;
import com.redis.aza.stock.admin.core.Subject;
import com.redis.aza.stock.admin.core.Warehouse;
import com.redis.aza.stock.admin.sql.state.SqlState;
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
public class WarehouseSQL implements Dataset<String, Warehouse> {
	
	private static class WarehouseImpl implements Warehouse {
		
		private final String code, name, address, phone, email;

		public WarehouseImpl(String code, String name, String address, String phone, String email) {
			this.code = code;
			this.name = name;
			this.address = address;
			this.phone = phone;
			this.email = email;
		}

		@Override
		public String getCode() {
			return code;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getAddress() {
			return address;
		}

		@Override
		public String getPhone() {
			return phone;
		}

		@Override
		public String getEmail() {
			return email;
		}

		@Override
		public State getState() {
			return SqlState.getState(this.code);
		}

		@Override
		public String toString() {
			return "[" + code + "] " + name;
		}
		
		
	}
	
	
	
	

	@Override
	public void forEach(Consumer<Warehouse> consumer) {
		String query = "SELECT [code], [name], [address], [phone], [email] FROM [dbo].[Warehouse] ORDER BY [name] ASC";
		try {
			Connection cn = SQL.getConnection();
			
			PreparedStatement ps = cn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				consumer.accept(
					new WarehouseImpl(
						rs.getString("code"), 
						rs.getString("name"), 
						rs.getString("address"), 
						rs.getString("phone"), 
						rs.getString("email")
					)
				);
			}
			
			cn.close();
		}
		catch (SQLException ex) {
			Logger.getLogger(WarehouseSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	
	@Override
	public void forEach(BiConsumer<String, Warehouse> consumer) {
		String query = "SELECT [key], [code], [name], [address], [phone], [email] FROM [dbo].[Warehouse] ORDER BY [name] ASC";
		try {
			Connection cn = SQL.getConnection();
			
			PreparedStatement ps = cn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				consumer.accept(
					rs.getString("key"),
					new WarehouseImpl(
						rs.getString("code"), 
						rs.getString("name"), 
						rs.getString("address"), 
						rs.getString("phone"), 
						rs.getString("email")
					)
				);
			}
			
			cn.close();
		}
		catch (SQLException ex) {
			Logger.getLogger(WarehouseSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	
	
	public static void main(String[] args) {
		new WarehouseSQL().forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
	}
	
}
