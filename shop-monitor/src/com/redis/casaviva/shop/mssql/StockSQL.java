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

import com.redis.casaviva.shop.core.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class StockSQL {
		
	private static final String SQL_SELECT = "SELECT TOP 1 code, name, address, phone, email FROM Stock WHERE code = ?";
	public static Stock select(String code) {
		
		Stock stock = null;
		
		try(Connection cn = SQL.getConnection()) {
			
			PreparedStatement ps = cn.prepareStatement(SQL_SELECT);
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				stock = new Stock(
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("address"), 
					rs.getString("phone"), 
					rs.getString("email")
				);
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(StockSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return stock;
	}
	
	
	
	
}
