/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redis.stock.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class Stock {
	private final String code, name, location;
	private final Map<String, Double> quantities;

	public Stock(String code, String name, String location) {		
		this.code = code;
		this.name = name;
		this.location = location;
		this.quantities = new TreeMap<>();
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public Double getQuantity(Article product) {
		return quantities.getOrDefault(product.getCode(), 0.0d);
	}
	
	public Double getQuantity(String productCode) {
		return quantities.getOrDefault(productCode, 0.0d);
	}
	
	public void setQuanity(Article product, Double quantity) {
		this.quantities.put(product.getCode(), quantity);
	}
	
	public void setQuanity(String productCode, Double quantity) {
		this.quantities.put(productCode, quantity);
	}

	@Override
	public String toString() {
		return code;
	}
	
	public static List<Stock> getStockList() {
		List<Stock> stockList = new ArrayList();
		
		try(Connection cn = SqlServer.getConnection()) {
			String SQL = ""
				+ "SELECT KOD, PERSHKRIM, SHENIM1 FROM [MAGAZINA] ORDER BY NRRENDOR ASC";
			PreparedStatement ps = cn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Stock stock = new Stock(rs.getString("KOD"), rs.getString("PERSHKRIM"), rs.getString("SHENIM1"));
				
				
				String SQL1 = ""
					+ "SELECT KARTLLG, SUM(ISNULL(SASIH,0) - ISNULL(SASID, 0)) AS SASI  FROM LEVIZJEHD  GROUP BY KMAG, KARTLLG HAVING KARTLLG IS NOT NULL AND KMAG = ? ORDER BY KARTLLG ASC";
				PreparedStatement ps1 = cn.prepareStatement(SQL1);
				ps1.setString(1, stock.code);
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()) { stock.setQuanity(rs1.getString("KARTLLG"), rs1.getDouble("SASI"));}
				
				stockList.add(stock);
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return stockList;
	}
}
