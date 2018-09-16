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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class Article {
	
	private final String code;
	private String sector, category, supplier, barcode, name;
	private final Double buyPrice, costPrice;
	private Double sellPrice, sellPriceTemp;
	private Instant instant;

	public Article(String code, String sector, String category, String supplier, String barcode, String name, Double buyPrice, Double costPrice, Double sellPrice, Double sellPriceTemp, Instant instant) {
		this.code = code;
		this.sector = sector;
		this.category = category;
		this.supplier = supplier;
		this.barcode = barcode;
		this.name = name;
		this.buyPrice = buyPrice;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.sellPriceTemp = sellPriceTemp;
		this.instant = instant;
	}

	public String getCode() {
		return code;
	}

	public String getSector() {
		return sector;
	}

	public String getCategory() {
		return category;
	}

	public String getSupplier() {
		return supplier;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getName() {
		return name;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public Double getSellPriceTemp() {
		return sellPriceTemp;
	}

	public Instant getInstant() {
		return instant;
	}
	
	
	
	

	
	

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + Objects.hashCode(this.code);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Article other = (Article) obj;
		return Objects.equals(this.code, other.code);
	}

	@Override
	public String toString() {
		return "[" + code + "] " + name;
	}
	
	public boolean setSector(String sector) {
		boolean success = false;
		
		try(Connection cn = SqlServer.getConnection()) {
			PreparedStatement ps = cn.prepareStatement("UPDATE [ARTIKUJ] SET [KLASIF] = ? WHERE [KOD] = ?");
			ps.setString(2, this.code);
			ps.setString(1, sector);
			
			ps.execute();
			
			this.sector = sector;
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
		}	
		
		return success;
	}

	public boolean setCategory(String category) {
		boolean success = false;
		
		try(Connection cn = SqlServer.getConnection()) {
			PreparedStatement ps = cn.prepareStatement("UPDATE [ARTIKUJ] SET [KLASIF2] = ? WHERE [KOD] = ?");
			ps.setString(2, this.code);
			ps.setString(1, category);
			
			ps.execute();
			
			this.category = category;
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
		}	
		
		return success;
	}

	public boolean setSupplier(String supplier) {
		boolean success = false;
		
		try(Connection cn = SqlServer.getConnection()) {
			PreparedStatement ps = cn.prepareStatement("UPDATE [ARTIKUJ] SET [KLASIF3] = ? WHERE [KOD] = ?");
			ps.setString(2, this.code);
			ps.setString(1, supplier);
			
			ps.execute();
			
			this.supplier = supplier;
			
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
		}	
		
		return success;
		
	}

	public boolean setBarcode(String barcode) {
		boolean success = false;
		
		try(Connection cn = SqlServer.getConnection()) {
			PreparedStatement ps = cn.prepareStatement("UPDATE [ARTIKUJ] SET [BC] = ? WHERE [KOD] = ?");
			ps.setString(2, this.code);
			ps.setString(1, barcode);
			
			ps.execute();
			
			this.supplier = supplier;
			
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
		}	
		
		return success;
	}
	
	
	
	public boolean setName(String name) {
		boolean success = false;
		
		try(Connection cn = SqlServer.getConnection()) {
			PreparedStatement ps = cn.prepareStatement("UPDATE [ARTIKUJ] SET [PERSHKRIM] = ? WHERE [KOD] = ?");
			ps.setString(2, this.code);
			ps.setString(1, name);
			
			ps.execute();
			
			this.name = name;
			
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
		}	
		
		return success;
	}

	public boolean setSellPrice(Double sellPrice) {
		boolean success = false;
		
		Instant instant = Instant.now().truncatedTo(ChronoUnit.HOURS);
		
		try(Connection cn = SqlServer.getConnection()) {
			PreparedStatement ps = cn.prepareStatement("UPDATE [ARTIKUJ] SET [CMSH2] = ?, [CMSH1] = ?, [KLASIF6] = ? WHERE [KOD] = ?");
			ps.setString(4, this.code);
			ps.setDouble(1, sellPrice);
			ps.setDouble(2, sellPriceTemp);
			ps.setTimestamp(3, Timestamp.from(instant));
			
			ps.execute();
			
			this.sellPrice = sellPrice;
			this.instant = instant;
			
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
		}	
		
		return success;
	}

	public boolean setSellPriceTemp(Double sellPriceTemp) {
		boolean success = false;
		
		Instant instant = Instant.now().truncatedTo(ChronoUnit.HOURS);
		
		try(Connection cn = SqlServer.getConnection()) {
			PreparedStatement ps = cn.prepareStatement("UPDATE [ARTIKUJ] SET [CMSH2] = ?, [CMSH1] = ?, [KLASIF6] = ? WHERE [KOD] = ?");
			ps.setString(4, this.code);
			ps.setDouble(1, sellPrice);
			ps.setDouble(2, sellPriceTemp);
			ps.setTimestamp(3, Timestamp.from(instant));
			
			ps.execute();
			
			this.sellPriceTemp = sellPriceTemp;
			this.instant = instant;
			
			success = true;
		} 
		catch (SQLException ex) {
			Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
		}	
		
		return success;
	}
	
	public static List<Article> geProductList() {
		List<Article> productList = new ArrayList<>();
		
		try(Connection cn = SqlServer.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(""
				+ "SELECT KOD, ISNULL(KLASIF, '') AS KLASIF, ISNULL(KLASIF2, '') AS KLASIF2, ISNULL(KLASIF3, '') AS KLASIF3, BC, PERSHKRIM, CMB, KOSTMES, CMSH2, CMSH1, ARTIKUJ.KLASIF6 "
				+ "FROM ARTIKUJ "
				+ "ORDER BY ARTIKUJ.KOD ASC");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Article product = new Article(
					rs.getString("KOD"), 
					rs.getString("KLASIF"), 
					rs.getString("KLASIF2"), 
					rs.getString("KLASIF3"), 
					rs.getString("BC"),
					rs.getString("PERSHKRIM"), 
					rs.getDouble("CMB"),  
					rs.getDouble("KOSTMES"),  
					rs.getDouble("CMSH2"), 
					rs.getDouble("CMSH1"), 
					rs.getTimestamp("KLASIF6") == null ? null : rs.getTimestamp("KLASIF6").toInstant()
				);
				
				productList.add(product);
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
		}		
		
		return productList;
	}
}
