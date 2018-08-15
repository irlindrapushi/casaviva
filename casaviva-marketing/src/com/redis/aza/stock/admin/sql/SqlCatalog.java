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

import com.redis.aza.stock.admin.core.Catalog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public final class SqlCatalog {
	
	public static Catalog getCatalog(){
		return new CatalogImpl();
	}
	
	
	private SqlCatalog() {}
	
	public static boolean updateMinStock(String item, Float value) {
		boolean success = false;
		
		try(Connection cn = SqlServer.getConnection()){
			PreparedStatement ps = cn.prepareStatement("UPDATE StockMin SET value = ? WHERE item = ?");
			ps.setFloat(1, value);
			ps.setString(2, item);
			
			if(ps.executeUpdate() == 0){
				ps = cn.prepareStatement("INSERT INTO StockMin (item, value) VALUES (?,?)");
				ps.setString(1, item);
				ps.setFloat(2, value);
				ps.execute();
			}
			
			success = true;
		}
		catch (SQLException ex) {
			Logger.getLogger(SqlCatalog.class.getName()).log(Level.SEVERE, null, ex);
		}		
		
		return success;
	}
	
	public static Catalog.Item selectItem(String code) {
		Catalog.Item item = null;
		
		try(Connection cn = SqlServer.getConnection()){
			PreparedStatement ps = cn.prepareStatement(""
				+ "SELECT TOP 1 "
				+ "	code, "
				+ "	category, "
				+ "	description, "
				+ "	supplier, "
				+ "	minStock, "
				+ "	stock, "
				+ "	cost, "
				+ "	buyin, "
				+ "	wholsale, "
				+ "	retail, "
				+ "	special, "
				+ "	lastSale "
				+ "FROM Catalog "
				+ "WHERE code = ?");
			
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				item = new Catalog.Item(
					rs.getString("code"), 
					rs.getString("category"),
					rs.getString("description"),
					rs.getString("supplier"),
					rs.getFloat("minStock"), 
					rs.getFloat("stock"), 
					rs.getFloat("cost"), 
					rs.getFloat("buyin"),
					rs.getFloat("wholsale"),
					rs.getFloat("retail"), 
					rs.getFloat("special"), 
					rs.getDate("lastSale") == null ? null : rs.getDate("lastSale")
				);
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(SqlCatalog.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return item;
	}
	
	public static Catalog.Item selectItem(Connection cn, String code) {
		Catalog.Item item = null;
		
		try(PreparedStatement ps = cn.prepareStatement(""
				+ "SELECT TOP 1 "
				+ "	code, "
				+ "	category, "
				+ "	description, "
				+ "	supplier, "
				+ "	minStock, "
				+ "	stock, "
				+ "	cost, "
				+ "	buyin, "
				+ "	wholsale, "
				+ "	retail, "
				+ "	special, "
				+ "	lastSale "
				+ "FROM Catalog "
				+ "WHERE code = ?")){
			
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				item = new Catalog.Item(
					rs.getString("code"), 
					rs.getString("category"),
					rs.getString("description"),
					rs.getString("supplier"),
					rs.getFloat("minStock"), 
					rs.getFloat("stock"), 
					rs.getFloat("cost"), 
					rs.getFloat("buyin"),
					rs.getFloat("wholsale"),
					rs.getFloat("retail"), 
					rs.getFloat("special"), 
					rs.getDate("lastSale") == null ? null : rs.getDate("lastSale")
				);
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(SqlCatalog.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return item;
	}
	
	
	
	private static boolean forEach(Consumer<Catalog.Item> consumer) {
		boolean success = false;
		
		try(Connection cn = SqlServer.getConnection()){
			PreparedStatement ps = cn.prepareStatement(""
				+ "SELECT "
				+ "	code, "
				+ "	category, "
				+ "	description, "
				+ "	supplier, "
				+ "	minStock, "
				+ "	stock, "
				+ "	cost, "
				+ "	buyin, "
				+ "	wholsale, "
				+ "	retail, "
				+ "	special, "
				+ "	lastSale "
				+ "FROM Catalog "
				+ "ORDER BY code ASC");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Catalog.Item item = new Catalog.Item(
					rs.getString("code"), 
					rs.getString("category"),
					rs.getString("description"),
					rs.getString("supplier"),
					rs.getFloat("minStock"), 
					rs.getFloat("stock"), 
					rs.getFloat("cost"), 
					rs.getFloat("buyin"),
					rs.getFloat("wholsale"),
					rs.getFloat("retail"), 
					rs.getFloat("special"), 
					rs.getDate("lastSale") == null ? null : rs.getDate("lastSale")
				);
				
				consumer.accept(item);
			}
			
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(SqlCatalog.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return success;
	}
	
	private static class CatalogImpl implements Catalog {
		@Override
		public boolean forEach(Consumer<Item> consumer) {
			return SqlCatalog.forEach(consumer);
		}		
	}	
}


