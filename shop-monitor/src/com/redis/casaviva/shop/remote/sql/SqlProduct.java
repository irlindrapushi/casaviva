/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.remote.sql;

import com.redis.casaviva.shop.dc.Product;
import com.redis.casaviva.shop.remote.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SqlProduct{

	private static final String SQL_SELECT = ""
		+ "SELECT p.code, p.barcode, p.category, p.type, p.description, p.unit, ISNULL(p.priceNewValue, 0) AS priceNewValue, ISNULL(p.priceOldValue, 0) AS priceOldValue, p.priceInstant, ISNULL(s.quantity, 0) AS stock "
		+ "FROM dbo.Product p LEFT JOIN Stock s ON p.code = s.item AND s.warehouse = ? "
		+ "ORDER BY s.warehouse ASC, p.code ASC, p.priceInstant ASC;";
	
	public static List<Product> read(String warehouse){
		List<Product> products = new ArrayList<>();
		
		try(Connection conn = SQL.getInstance().getConnection()){
			PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
			ps.setString(1, warehouse);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Double priceNewValue = rs.getDouble("priceNewValue");
				Double priceOldValue = rs.getDouble("priceOldValue");
				Instant priceInstant = rs.getTimestamp("priceInstant").toInstant();
				Double stock = rs.getDouble("stock");
				
				Product product = new Product(rs.getString("code"), rs.getString("barcode"), rs.getString("category"), rs.getString("type"), rs.getString("description"), rs.getString("unit")) {
					@Override
					public Double getNewPrice() {return priceNewValue;}
					
					@Override
					public Double getOldPrice() {return priceOldValue;}
					
					@Override
					public Instant getPriceInstant() {return priceInstant;}
					
					@Override
					public Double getStock() {return stock;}
				};
				
				products.add(product);
			}
		} 
		catch (SQLException ex) {		
			Logger.getLogger(SqlProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return products;
	}
	
	public static List<Product> read(Connection conn, String warehouse){
		List<Product> products = new ArrayList<>();
		
		try(PreparedStatement ps = conn.prepareStatement(SQL_SELECT)){
			ps.setString(1, warehouse);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Double priceNewValue = rs.getDouble("priceNewValue");
				Double priceOldValue = rs.getDouble("priceOldValue");
				Instant priceInstant = rs.getTimestamp("priceInstant").toInstant();
				Double stock = rs.getDouble("stock");
				
				Product product = new Product(rs.getString("code"), rs.getString("barcode"), rs.getString("category"), rs.getString("type"), rs.getString("description"), rs.getString("unit")) {
					@Override
					public Double getNewPrice() {return priceNewValue;}
					
					@Override
					public Double getOldPrice() {return priceOldValue;}
					
					@Override
					public Instant getPriceInstant() {return priceInstant;}
					
					@Override
					public Double getStock() {return stock;}
				};
				
				products.add(product);
			}
		} 
		catch (SQLException ex) {		
			Logger.getLogger(SqlProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		return products;
	}
}
