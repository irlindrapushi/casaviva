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
	private String sector, category, name;
	private Double sellPrice, sellPriceTemp;
	private Instant instant;

	public Article(String code, String sector, String category, String name, Double sellPrice, Double sellPriceTemp, Instant instant) {
		this.code = code;
		this.sector = sector == null ? "" : sector;
		this.category = category == null ? "" : category;
		this.name = name;
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
	
	public String getName() {
		return name;
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
				+ "SELECT ARTIKUJ.KOD, ARTIKUJ.KLASIF, ARTIKUJ.KLASIF2, ARTIKUJ.PERSHKRIM, ARTIKUJ.CMSH2, ARTIKUJ.CMSH1, ARTIKUJ.KLASIF6 "
				+ "FROM ARTIKUJ "
				+ "ORDER BY ARTIKUJ.KOD ASC");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Article product = new Article(
					rs.getString("KOD"), 
					rs.getString("KLASIF"), 
					rs.getString("KLASIF2"),
					rs.getString("PERSHKRIM"), 
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
