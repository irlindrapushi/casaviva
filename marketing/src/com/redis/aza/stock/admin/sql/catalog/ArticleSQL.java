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
package com.redis.aza.stock.admin.sql.catalog;

import com.redis.aza.stock.admin.core.Warehouse;
import com.redis.aza.stock.admin.core.catalog.Article;
import com.redis.aza.stock.admin.sql.SQL;
import com.redis.aza.stock.admin.sql.state.SqlState;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class ArticleSQL implements Article {
	
	private final String sector;
	private final String category;
	private final String supplier;
	private final String barcode;
	private final String code;
	private final String name;
	private final String unit;
	private final Double buyPrice;
	private final Double costPrice;
	private final Double sellPrice;
	private final Double sellPriceDiscount;
	private final Instant sellPriceInstant;
	

	public ArticleSQL(String sector, String category, String supplier, String barcode, String code, String name, String unit, Double buyPrice, Double costPrice, Double sellPrice, Double sellPriceDiscount, Instant sellPriceInstant) {
		this.sector = sector;
		this.category = category;
		this.supplier = supplier;
		this.barcode = barcode;
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.buyPrice = buyPrice;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.sellPriceDiscount = sellPriceDiscount;
		this.sellPriceInstant = sellPriceInstant;
	}

	@Override
	public String getCode() {return code;}

	@Override
	public String getName() {return name;}

	@Override
	public String getUnit() {return unit;}

	@Override
	public String getSector() {return sector;}

	@Override
	public String getCategory() {return category;}

	@Override
	public String getSupplier() {return supplier;}

	@Override
	public String getBarcode() {return barcode;}

	@Override
	public Double getBuyPrice() {return buyPrice;}

	@Override
	public Double getCostPrice() {return costPrice;}

	@Override
	public Double getSellPrice() {return sellPrice;}

	@Override
	public Double getSellPriceDiscount() {return sellPriceDiscount;}

	@Override
	public Instant getSellPriceInstant() {return sellPriceInstant;}
	
	
	@Override
	public State getState() {
		StateSQL stateImpl = new StateSQL();
				
		String query = "SELECT [stock], SUM([weight]) AS [weight], SUM([value]) AS [value] FROM [Event] WHERE [item] = ? AND [instant] <= ? GROUP BY [stock]";
		try(Connection cn = SQL.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(query);

			ps.setTimestamp(1, java.sql.Timestamp.from(Instant.now()));

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				stateImpl.add(rs.getString("stock"), rs.getDouble("weight"), rs.getDouble("value"));
			}
		}
		catch (SQLException ex) {
			Logger.getLogger(SqlState.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return stateImpl;
	}
	
	private static class StateSQL implements Article.State {
		
		public Map<String, Double> weights = new HashMap<>();
		public Map<String, Double> values = new HashMap<>();
		
		public void add(String warehouse, Double weight, Double value) {
			weights.merge(warehouse, weight, Double::sum);
			values.merge(warehouse, value, Double::sum);
		}

		@Override
		public Double getWeight() {
			return weights.values().stream().mapToDouble(Double::doubleValue).sum();
		}

		@Override
		public Double getPrice() {
			return this.getValue() / this.getWeight();
		}

		@Override
		public Double getValue() {
			return values.values().stream().mapToDouble(Double::doubleValue).sum();
		}

		@Override
		public Double getWeight(Warehouse warehouse) {
			return weights.getOrDefault(warehouse.getCode(), 0.0d);
		}

		@Override
		public Double getPrice(Warehouse warehouse) {
			return this.getWeight(warehouse) == 0 ? Double.NaN : this.getValue(warehouse) / this.getWeight(warehouse);
		}

		@Override
		public Double getValue(Warehouse warehouse) {
			return values.getOrDefault(warehouse.getCode(), 0.0d);
		}
	}
	
	
	
	
	
	
	
	public static ArticleSQL select(String code) throws SQLException {
		
		ArticleSQL article = null;
		
		try(Connection cn = SQL.getConnection()){
			
			PreparedStatement ps = cn.prepareStatement(""
				+ "SELECT TOP 1 "
				+ "	[sector], "
				+ "	[category], "
				+ "	[supplier], "
				+ "	[barcode], "
				+ "	[code], "
				+ "	[name], "
				+ "	[unit],  "
				+ "	[buy_price], "
				+ "	[cost_price], "
				+ "	[sell_price], "
				+ "	[sell_price_discount], "
				+ "	[sell_price_instant] "
				+ "FROM "
				+ "	[articles] "
				+ "WHERE "
				+ "	[code] = ? "
				+ "ORDER BY "
				+ "	[sector], "
				+ "	[category], "
				+ "	[supplier], "
				+ "	[barcode]");
			
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			
			
			if(rs.next()) {
				
				article = new ArticleSQL(
					rs.getString("sector"),
					rs.getString("category"),
					rs.getString("supplier"),
					rs.getString("barcode"),
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("unit"),
					rs.getDouble("buy_price"),
					rs.getDouble("cost_price"),
					rs.getDouble("sell_price"),
					rs.getDouble("sell_price_discount"),
					rs.getTimestamp("sell_price_instant") == null ? null : rs.getTimestamp("sell_price_instant").toInstant()
				);
			}
		} 
		
		return article;
	}
	
	public static void forEach(Consumer<Article> consumer) throws SQLException {
		
		try(Connection cn = SQL.getConnection()){
			
			PreparedStatement ps = cn.prepareStatement(""
				+ "SELECT TOP 1 "
				+ "	[sector], "
				+ "	[category], "
				+ "	[supplier], "
				+ "	[barcode], "
				+ "	[code], "
				+ "	[name], "
				+ "	[unit],  "
				+ "	[buy_price], "
				+ "	[cost_price], "
				+ "	[sell_price], "
				+ "	[sell_price_discount], "
				+ "	[sell_price_instant] "
				+ "FROM "
				+ "	[articles] "
				+ "ORDER BY "
				+ "	[sector], "
				+ "	[category], "
				+ "	[supplier], "
				+ "	[barcode]");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				ArticleSQL article = new ArticleSQL(
					rs.getString("sector"),
					rs.getString("category"),
					rs.getString("supplier"),
					rs.getString("barcode"),
					rs.getString("code"), 
					rs.getString("name"), 
					rs.getString("unit"),
					rs.getDouble("buy_price"),
					rs.getDouble("cost_price"),
					rs.getDouble("sell_price"),
					rs.getDouble("sell_price_discount"),
					rs.getTimestamp("sell_price_instant") == null ? null : rs.getTimestamp("sell_price_instant").toInstant()
				);
				
				consumer.accept(article);
			}
		} 
	}

	
}
