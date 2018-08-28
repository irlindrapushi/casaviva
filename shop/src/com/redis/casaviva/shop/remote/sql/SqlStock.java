/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.remote.sql;

import com.redis.casaviva.shop.dc.Stock;
import com.redis.casaviva.shop.remote.SQL;
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
 * @author Redjan Shabani info@redis.com.al
 */
public class SqlStock {
	
	private static final String SQL_SELECT = ""
		+ "SELECT code, barcode, description, unit, M01, M02, M01 + M02 AS M0 "
		+ "FROM ViewStocks "
		+ "ORDER BY code ASC";
	public static List<Stock> read(){
		List<Stock> stocks = new ArrayList<>();
		
		try(Connection conn = SQL.getConnection()){
			PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Stock stock = new Stock(rs.getString("code"), rs.getString("barcode"), rs.getString("description"), rs.getString("unit"));
				stock.put("M01", rs.getDouble("M01"));
				stock.put("M02", rs.getDouble("M02"));
				stocks.add(stock);
				
				System.out.println(stock.getCode());
			}
		}
		catch (SQLException ex) {
			Logger.getLogger(SqlStock.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		return stocks;
	}
}
