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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class WarehouseSQL implements Warehouse {
	
	private final String code, name, address, phone, email;

	public WarehouseSQL(String code, String name, String address, String phone, String email) {
		this.code = code;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	@Override
	public String getCode() {return code;}

	@Override
	public String getName() {return name;}

	@Override
	public String getAddress() {return address;}

	@Override
	public String getPhone() {return phone;}

	@Override
	public String getEmail() {return email;}
	
	
	
	
	
	
	
	
	
	
	private static final String SQL_GET = "SELECT [code], [name], [address], [phone], [email] FROM [Warehouse] WHERE [code] = ?";
	public static Warehouse get(String code) {
		Warehouse warehouse = null;
		try(Connection cn = SQL.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(SQL_GET);
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				warehouse = new WarehouseSQL(
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("address"), 
					rs.getString("phone"), 
					rs.getString("email")
				);
			}
		} 
		catch (SQLException | IOException ex) {
			Logger.getLogger(WarehouseSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return warehouse;
	}
	
	private static final String SQL_FOR_EACH = "SELECT [code], [name], [address], [phone], [email] FROM [Warehouse] WHERE [code] = ?";
	public static void forEach(Consumer<Warehouse> consumer) {
		try(Connection cn = SQL.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(SQL_FOR_EACH);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Warehouse warehouse = new WarehouseSQL(
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("address"), 
					rs.getString("phone"), 
					rs.getString("email")
				);
				
				consumer.accept(warehouse);
			}
		} 
		catch (SQLException | IOException ex) {
			Logger.getLogger(WarehouseSQL.class.getName()).log(Level.SEVERE, null, ex);
		} 
	}

}
