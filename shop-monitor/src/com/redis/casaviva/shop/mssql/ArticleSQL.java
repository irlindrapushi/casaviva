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

import com.redis.casaviva.shop.core.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ArticleSQL implements Article {
	private final String code;
	private final String barcode;
	private final String sector;
	private final String category;
	private final String description;
	private final String unit;
	private final Double quantity;
	private final Double sellPrice;
	private final Double sellPriceTemp;
	private final Instant sellPriceTime;

	public ArticleSQL(String code, String barcode, String sector, String category, String description, String unit, Double quantity, Double sellPrice, Double sellPriceTemp, Instant sellPriceTime) {
		this.code = code;
		this.barcode = barcode;
		this.sector = sector;
		this.category = category;
		this.description = description;
		this.unit = unit;
		this.quantity = quantity;
		this.sellPrice = sellPrice;
		this.sellPriceTemp = sellPriceTemp;
		this.sellPriceTime = sellPriceTime;
	}

	public String getCode() {
		return code;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getSector() {
		return sector;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getUnit() {
		return unit;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public Double getSellPriceTemp() {
		return sellPriceTemp;
	}

	public Instant getSellPriceTime() {
		return sellPriceTime;
	}
	
	
	
	
	@Override
	public String toString() {
		return "[" + this.code + "] " + this.description;
	}
	
	public static void forEach(Consumer<Article> consumer) {
		String query = "EXECUTE [readStockArticles] ?";
		try(Connection cn = SQL.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Article art = new ArticleSQL(
					rs.getString("code"), 
					rs.getString("barcode"), 
					rs.getString("sector"), 
					rs.getString("category"), 
					rs.getString("description"), 
					rs.getString("unit"), 
					rs.getDouble("quantity"),
					rs.getDouble("sellPrice"), 
					rs.getDouble("sellPriceTemp"), 
					rs.getTimestamp("instant").toInstant()
				);
				
				consumer.accept(art);
			}
		} 
		catch (SQLException ex) {
			Logger.getLogger(ArticleSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}
